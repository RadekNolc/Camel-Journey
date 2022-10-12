import java.util.List;

public class Simulation {

    public void run() throws Exception {
        Map.render();
        
        while (Request.getRequests().peek() != null) {
            Request request = Request.getRequests().poll();

            processRequest(request);
        }

        System.out.println("Simulation finished.");
    }

    public void processRequest(Request request) throws Exception {
        int oasisId = request.getOasisId();
        int neededStretchers = request.getNeededStretchers();
        double arrival = request.getArrival();
        double deadline = request.getArrival() + request.getDeadline();

        //Příchod požadavku
        System.out.printf("Cas: %.0f, %s, %s, Pocet kosu: %d, Deadline: %.0f\n", arrival, request, Location.getLocationById(oasisId), neededStretchers, deadline);

        Location destination = Location.getLocationById(oasisId);
        Storage storage = Map.getBestStorage(destination);
        Camel camel = storage.generateCamel();
        double time = arrival;
        System.out.printf("Cas: %.0f, %s, %s, Nalozeno kosu: <k>, Odchod v: <t+k·tn>", time, camel, storage);
    }
}
