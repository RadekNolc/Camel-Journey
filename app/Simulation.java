import java.util.ArrayList;

public class Simulation {

    /**
     * Function to run the simulation
     * @throws Exception if there was an error while processing the requests
     */
    public void run() throws Exception {
        Map.render();
        
        while (Request.getRequests().peek() != null) {
            Request request = Request.getRequests().poll();
            receiveRequest(request);
            prepareAll(request);
            processRequest(request);
        }

        System.out.println("Simulation finished.");
    }

    /**
     * Function to inform the user that the request arrived
     * @param request which request arrived
     */
    private void receiveRequest(Request request) {
        System.out.printf("Cas: %.0f, %s, %s, Pocet kosu: %d, Deadline: %.0f\n", request.getArrivalTime(), request, request.getOasis(), request.getNeededStretchers(), request.getArrivalTime() + request.getDeadlineTime());
    }
    
    /**
     * Function to choose best camel and storage, load stretchers on camel and inform user about that
     * @param request which request to prepare
     * @throws Exception if there was an error while creating camel or calculating finish time of request
     */
	private void prepareAll(Request request) throws Exception { //TODO: Needs refactoring
        /* Needed variables */
        Camel camel = null;
        boolean isPossibleToProcess = false;
        double bestTime = Double.MAX_VALUE;
        Storage bestStorage = null;
		double storageExitTime = 0;

        for (Camel currentCamel : Camel.getCamels()) { /* Getting if there is already existing camel that can proceed the way. */
            if (currentCamel.getArriveTime() > request.getArrivalTime()) {
                continue;
            }
            
            double localFinishTime = Double.MAX_VALUE;
            double localExitTime = 0.0;         
            if (!currentCamel.getHomeStorage().hasAvailableStretcher(request.getNeededStretchers())) { /* If stretchers are not available */
                if (currentCamel.getHomeStorage().getLastFillTime() + currentCamel.getHomeStorage().getFillTime() > request.getCurrentTime()) { /* Checking if it is possible to refill storage */
                    localExitTime += currentCamel.getHomeStorage().getFillTime();
                } else {
                    currentCamel.getHomeStorage().refillStretchers();
                    currentCamel.getHomeStorage().setLastFillTime(request.getCurrentTime());
                }
            }
            localExitTime += request.getNeededStretchers() * currentCamel.getHomeStorage().getLoadTime(); //Nakládka

            localFinishTime = testFinishTime(currentCamel.getHomeStorage(), request.getOasis(), currentCamel.getMaxStamina(), currentCamel.getSpeed(), currentCamel.getDrinkTime(), request.getNeededStretchers(), request.getArrivalTime(), request.getDeadlineTime(), localExitTime);
            if (localFinishTime < 0) {
                continue;
            }

            if (localFinishTime < bestTime) {
                storageExitTime = localExitTime;
                bestTime = localFinishTime;
                bestStorage = currentCamel.getHomeStorage();
                camel = currentCamel;
            }
        }
        
        if (camel == null) { /* When there is no already possible camel */
            ArrayList<Camel> createdCamels = new ArrayList<Camel>();

            for (Storage storage : Storage.getStorages()) {
                double localFinishTime = Double.MAX_VALUE;
                double localExitTime = 0.0;
                if (!storage.hasAvailableStretcher(request.getNeededStretchers())) { /* If stretchers are not available */
                    if (storage.getLastFillTime() + storage.getFillTime() > request.getCurrentTime()) { /* Checking if it is possible to refill storage */
                        localExitTime += storage.getFillTime();
                    } else {
                        storage.refillStretchers();
                        storage.setLastFillTime(request.getCurrentTime());
                    }
                }
                localExitTime += request.getNeededStretchers() * storage.getLoadTime(); //Nakládka

                while (!isPossibleToProcess) {
                    camel = Factory.camel();
                    createdCamels.add(camel);
                    localFinishTime = testFinishTime(storage, request.getOasis(), camel.getMaxStamina(), camel.getSpeed(), camel.getDrinkTime(), request.getNeededStretchers(), request.getArrivalTime(), request.getDeadlineTime(), localExitTime);
                    isPossibleToProcess = (localFinishTime >= 0) ? true : false;
                }

                if (localFinishTime == Double.MAX_VALUE) {
                    localFinishTime = testFinishTime(storage, request.getOasis(), camel.getMaxStamina(), camel.getSpeed(), camel.getDrinkTime(), request.getNeededStretchers(), request.getArrivalTime(), request.getDeadlineTime(), localExitTime);
                }

                if (localFinishTime >= 0 && localFinishTime < bestTime) {
                    storageExitTime = localExitTime;
                    bestTime = localFinishTime;
                    bestStorage = storage;
                }
                
            }   
            
            /* Connecting all generated camels to the storage */
            for (Camel createdCamel : createdCamels) {
                createdCamel.setHomeStorage(bestStorage);
                createdCamel.setLocation(bestStorage);
            }
        }

        camel.setStretchers(request.getNeededStretchers()); /* Loading needed stretchers */
        camel.getHomeStorage().removeStretchers(request.getNeededStretchers()); /* Removing stretchers from storage */

        request.setCamel(camel); /* Connecting camel to request */
        request.increaseCurrentTime(storageExitTime);

        System.out.printf("Cas: %.0f, %s, %s, Nalozeno kosu: %d, Odchod v: %.0f\n", request.getArrivalTime(), camel, bestStorage, request.getNeededStretchers(), request.getCurrentTime());
	}

    /**
     * Function to process request - camel journey to destination oasis and back to home storage and inform user about that
     * @param request which request to process
     * @throws Exception if there was an error processing the request
     */
    private void processRequest(Request request) throws Exception {
        Camel camel = request.getCamel();
        ArrayList<Location> locations = Map.getLocationsBetween(camel.getLocation(), request.getOasis());

        /* Travelling to the destination from the origin storage */
        for (Location step : locations) {
            Location cameFrom = camel.getLocation();
            Location cameTo = step;
            double distance = Map.getTotalDistance(camel.getLocation(), step);

            if (cameFrom.equals(cameTo)) { /*When camel wants to go to the same location*/
                continue;
            }

            camel.move(step);
            request.increaseCurrentTime(Calculator.timeToTravel(distance, camel.getSpeed()));

            if (!cameTo.equals(request.getOasis())) { /* When camel next step is not destination, output text, check for stamina and eventually drink */
                if (camel.getStamina() < distance) {
                    System.out.printf("Cas: %.0f, %s, %s, Ziznivy %s, Pokracovani mozne v: %.0f\n", request.getCurrentTime(), camel, cameTo, camel.getName(), request.getCurrentTime() + camel.getDrinkTime());
                    request.increaseCurrentTime(camel.getDrinkTime());
                    camel.drink();
                } else {
                    System.out.printf("Cas: %.0f, %s, %s, Kuk na velblouda\n", request.getCurrentTime(), camel, cameTo);
                }
            }
        }

        /* Checking if camel delivered stretchers on time, if not, exits simulation */
        if (request.getArrivalTime() + request.getDeadlineTime() - (request.getCurrentTime() + (request.getNeededStretchers() * camel.getHomeStorage().getLoadTime())) < 0) {
            System.out.printf("Cas: %.0f, %s, Vsichni vymreli, Harpagon zkrachoval, Konec simulace\n", request.getCurrentTime(), camel.getLocation());
            System.exit(0);
        }

        camel.setStretchers(0); /* Unload stretchers */
        System.out.printf("Cas: %.0f, %s, %s, Vylozeno kosu: %d, Vylozeno v: %.0f, Casova rezerva: %.0f\n", request.getCurrentTime(), camel, camel.getLocation(), request.getNeededStretchers(), request.getCurrentTime() + (request.getNeededStretchers() * camel.getHomeStorage().getLoadTime()), request.getArrivalTime() + request.getDeadlineTime() - (request.getCurrentTime() + (request.getNeededStretchers() * camel.getHomeStorage().getLoadTime())));
        request.increaseCurrentTime(request.getNeededStretchers() * camel.getHomeStorage().getLoadTime());

        /* Travelling back from the destination oasis to origin storage */
        locations = Map.getLocationsBetween(camel.getLocation(), camel.getHomeStorage());
        for (Location step : locations) {
            Location cameFrom = camel.getLocation();
            Location cameTo = step;
            double distance = Map.getTotalDistance(camel.getLocation(), step);

            if (cameFrom.equals(cameTo)) {
                continue;
            }

            camel.move(step);
            request.increaseCurrentTime(Calculator.timeToTravel(distance, camel.getSpeed()));

            if (!cameTo.equals(camel.getHomeStorage())) {
                if (camel.getStamina() < distance) {
                    request.increaseCurrentTime(camel.getDrinkTime());
                    camel.drink();
                }
            }
        }

        System.out.printf("Cas: %.0f, %s, Navrat do skladu: %d\n", request.getCurrentTime(), camel, camel.getLocation().getId());
        camel.drink(); /* Drink when camel arrives to storage */
        request.increaseCurrentTime(camel.getDrinkTime());

        camel.setArriveTime(request.getCurrentTime()); /* "Disabling" camel for specific time */
    }

    /**
     * Function to calculate possible finish time of request
     * @param origin at which storage journey starts
     * @param destination at which oasis journey ends
     * @param maxStamina what is the maximum distance to travel without drinking
     * @param speed what is the speed of camel
     * @param drinkTime what is the drink time of camel
     * @param neededStretchers how many stretchers are needed to finish the request
     * @param requestArrivalTime when did request arrive
     * @param requestDeadlineTime when is the deadline for the request
     * @param storageExitTime when it is possible to exit storage
     * @return possible finish time of request, if returns lower than 0, it is not possible to proceed
     * @throws Exception if there was an error on map e.g.: could not be found location by ID
     */
    private double testFinishTime(Storage origin, Oasis destination, double maxStamina, double speed, double drinkTime, int neededStretchers, double requestArrivalTime, double requestDeadlineTime, double storageExitTime) throws Exception {
        ArrayList<Location> locations = Map.getLocationsBetween(origin, destination);
        double currentTime = requestArrivalTime + storageExitTime;
        double stamina = maxStamina;
        Location currentLocation = origin;

        for (Location step : locations) { /* Getting steps between storage and destination oasis */
            Location cameFrom = currentLocation;
            Location comeTo = step;
            double distance = Map.getTotalDistance(currentLocation, step);

            if (cameFrom.equals(comeTo)) { /*When camel wants to go to the same location*/
                continue;
            }

            if (distance > stamina) { /*When it is not possible to reach next step*/
                return -1;
            }

            currentLocation = step;
            stamina -= distance;
            currentTime += Calculator.timeToTravel(distance, speed);

            if (!comeTo.equals(destination)) { /* When camel next step is not destination, check for stamina and eventually drink */
                if (stamina < distance) {
                    currentTime += drinkTime;
                    stamina = maxStamina;
                }
            }
        }

        
        if (requestArrivalTime + requestDeadlineTime - (currentTime + (neededStretchers * (((Storage) origin).getLoadTime()))) < 0) { /* Checking if delivery is under deadline */
            return -2;
        }

        currentTime += neededStretchers * ((Storage) origin).getLoadTime();
        return currentTime;        
    }
}
