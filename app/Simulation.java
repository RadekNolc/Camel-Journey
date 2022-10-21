import java.util.ArrayList;

public class Simulation {

    public void run() throws Exception {
        Map.render();
        
        while (Request.getRequests().peek() != null) {
            Request request = Request.getRequests().poll();
            receiveRequest(request);
            prepareAll(request);
            processRequest(request); // chůze, pití, vyložení
        }

        System.out.println("Simulation finished.");
    }

    /**
     * Function to call function to receive request
     * @param request
     * @throws Exception
     */
    private void receiveRequest(Request request) throws Exception {
        //Příchod požadavku
        System.out.printf("Cas: %.0f, %s, %s, Pocet kosu: %d, Deadline: %.0f\n", request.getArrival(), request, request.getOasis(), request.getNeededStretchers(), request.getArrival() + request.getDeadline());
    }

    
    /*
     * Zjištění nejlepšího skladu
     * Vygenerování vhodného velblouda
     */
	private void prepareAll(Request request) throws Exception {

        Storage possibleStorage = null;
		double exitTime = Double.MAX_VALUE;
        
        for (Storage storage : Storage.getStorages()) {
            double time = 0.0;
            if (!storage.hasAvailableStretcher(request.getNeededStretchers())) { //Pokud nejsou dostupné koše, přidá se čekačka na doplnění
                time += storage.getFillTime();
            }
            time += request.getNeededStretchers() * storage.getLoadTime(); //Nakládka

            if (time < exitTime) {
                exitTime = time;
                possibleStorage = storage;
            }
        }

        Camel camel = null;
        boolean isPossible = false;
        while (!isPossible) {
            camel = Factory.camel(possibleStorage); 
            isPossible = testCamel(camel, request.getOasis(), request.getNeededStretchers(), request.getDeadline(), exitTime) >= 0 ? true : false;
        }


        camel.setStretchers(request.getNeededStretchers()); //Naložení košů
        request.setCamel(camel); //Přiřazení velblouda k požadavku
        request.setCurrentTime(exitTime);

        System.out.printf("Cas: %.0f, %s, %s, Nalozeno kosu: %d, Odchod v: %.0f\n", request.getArrival(), camel, possibleStorage, request.getNeededStretchers(), exitTime);
	}

    /* Předpokladem je mít správného velblouda, jinak se program zacyklí */
    private void processRequest(Request request) throws Exception {
        Camel camel = request.getCamel();
        ArrayList<Location> locations = Map.getLocationsBetween(camel.getLocation(), request.getOasis());

        //Cesta tam
        for (Location step : locations) {
            Location cameFrom = camel.getLocation();
            Location cameTo = step;
            double distance = Map.getTotalDistance(camel.getLocation(), step);

            if (cameFrom.equals(cameTo)) {
                continue;
            }

            camel.move(step);
            request.increaseCurrentTime(Calculator.timeToTravel(distance, camel.getSpeed()));

            if (!cameTo.equals(request.getOasis())) {
                if (camel.getStamina() < distance) {
                    System.out.printf("Cas: %.0f, %s, %s, Ziznivy %s, Pokracovani mozne v: %.0f\n", request.getCurrentTime(), camel, cameTo, camel.getName(), request.getCurrentTime() + camel.getDrinkTime());
                    request.increaseCurrentTime(camel.getDrinkTime());
                    camel.drink();
                } else {
                    System.out.printf("Cas: %.0f, %s, %s, Kuk na velblouda\n", request.getCurrentTime(), camel, cameTo);
                }
            }
        }

        if (request.getDeadline() - (request.getCurrentTime() + (request.getNeededStretchers() * camel.getHomeStorage().getLoadTime())) < 0) {
            System.out.printf("Cas: %.0f, %s, Vsichni vymreli, Harpagon zkrachoval, Konec simulace\n", request.getCurrentTime(), camel.getLocation());
            System.exit(0);
        }

        System.out.printf("Cas: %.0f, %s, %s, Vylozeno kosu: %d, Vylozeno v: %.0f, Casova rezerva: %.0f\n", request.getCurrentTime(), camel, camel.getLocation(), request.getNeededStretchers(), request.getCurrentTime() + (request.getNeededStretchers() * camel.getHomeStorage().getLoadTime()), request.getDeadline() - (request.getCurrentTime() + (request.getNeededStretchers() * camel.getHomeStorage().getLoadTime())));
        request.increaseCurrentTime(request.getNeededStretchers() * camel.getHomeStorage().getLoadTime());

        //Cesta zpátky
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
        
        camel.drink(); //Napití
        camel.setStretchers(0); //Vyložení košů
    }

    private double testCamel(Camel camel, Location destination, int neededStretchers, double deadLine, double exitTime) throws Exception {
        ArrayList<Location> locations = Map.getLocationsBetween(camel.getLocation(), destination);
        double currentTime = exitTime;
        double stamina = camel.getMaxStamina();
        Location currentLocation = camel.getLocation();

        for (Location step : locations) {
            Location cameFrom = currentLocation;
            Location cameTo = step;
            double distance = Map.getTotalDistance(currentLocation, step);

            if (cameFrom.equals(cameTo)) {
                continue;
            }

            if (distance > camel.getMaxStamina()) { //Pokud je vzdálenost větší, než může vůbec velbloud ujít
                return -1;
            }

            currentLocation = step;
            stamina -= distance;
            currentTime += Calculator.timeToTravel(distance, camel.getSpeed());

            if (!cameTo.equals(destination)) {
                if (stamina < distance) {
                    currentTime += camel.getDrinkTime();
                    stamina = camel.getMaxStamina();
                }
            }
        }

        //Deadline check
        if (deadLine - (currentTime + (neededStretchers * camel.getHomeStorage().getLoadTime())) < 0) {
            return -1;
        }

        currentTime += neededStretchers * camel.getHomeStorage().getLoadTime();
        return currentTime;
    }
}
