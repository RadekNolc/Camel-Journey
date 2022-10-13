import java.util.List;

public class Simulation {

    public void run() throws Exception {
        Map.render();
        
        while (Request.getRequests().peek() != null) {
            Request request = Request.getRequests().poll();

            if (isPossibleToProcess(request))
                processRequest(request);
            else
                throw new Exception("Simulation failed.");
        }

        System.out.println("Simulation finished.");
    }

    public void processRequest(Request request) throws Exception {
        Location oasis = Location.getLocationById(request.getOasisId());
        double time = request.getArrival();
        int needed = request.getNeededStretchers();
        double deadline = time + request.getDeadline();

        System.out.printf("Cas: %.0f, %s, %s, Pocet kosu: %d, Deadline: %.0f\n", time, request, oasis, needed, deadline);

        Storage storage = Map.getNearestStorage(oasis);
        
        //System.out.printf("Cas: %.0f, %s, %s, Nalozeno kosu: <k>, Odchod v: <t+k·tn>", time, camel, storage);
    }

    public boolean isPossibleToProcess(Request request) throws Exception {
        boolean check = true;
        
        //Kontrola, zda je vůbec možné vygenerovat velblouda, který splní úkol.
        Oasis oasis = (Oasis) Location.getLocationById(request.getOasisId());
        Storage storage = Map.getNearestStorage(oasis);
        List<CamelTemplate> templates = CamelTemplate.getCamelTemplates();
        

        return check;
    }
}
