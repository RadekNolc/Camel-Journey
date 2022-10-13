import java.util.List;

public class Simulation {

    public void run() throws Exception {
        Map.render();
        
        while (Request.getRequests().peek() != null) {
            Request request = Request.getRequests().poll();

            if (isPossibleToProcess(request)) {
                processRequest(request);
            }
            else {
                throw new Exception("Simulation failed.");
            }
        }

        System.out.println("Simulation finished.");
    }

    private void processRequest(Request request) throws Exception {
        Location oasis = Location.getLocationById(request.getOasisId());
        double time = request.getArrival();
        int needed = request.getNeededStretchers();
        double deadline = time + request.getDeadline();

        System.out.printf("Cas: %.0f, %s, %s, Pocet kosu: %d, Deadline: %.0f\n", time, request, oasis, needed, deadline);

        Storage storage = getPossibleStorage(request);
        
        //System.out.printf("Cas: %.0f, %s, %s, Nalozeno kosu: <k>, Odchod v: <t+k·tn>", time, camel, storage);
    }

    private boolean isPossibleToProcess(Request request) throws Exception {
        boolean check = true;
        
        //Kontrola, zda je vůbec možné vygenerovat velblouda, který splní úkol.
        Storage storage = getPossibleStorage(request);
        
        if (storage == null) {
            check = false;
        }
        

        return check;
    }

    //Zjištění nejlepšího skladu - dbá na dostupnost košů, zda sklad neobsahuje již nějakého velblouda, který by dokončil úkol
	private Storage getPossibleStorage(Request request) throws Exception {
		if (!Map.isRendered()) {
			throw new Exception("Map has not been rendered yet.");
		}

		List<CamelTemplate> templates = CamelTemplate.getCamelTemplates();
        double totalTimeWithoutTravel = 0;
        Storage bestStorage = null;
        
        for (Storage storage : Storage.getStorages()) {
            
        }

        return null;
	}
}
