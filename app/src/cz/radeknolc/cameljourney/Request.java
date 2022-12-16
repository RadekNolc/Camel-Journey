package cz.radeknolc.cameljourney;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Request class handles the all needed information about request.
 * @author Radek Nolč
 */
public class Request {
    
    /** Unique identifier of request */
    private int id;
    /** Current time of request */
    private double currentTime;
    /** Arrival time (when the request arrives) */
    private double arrivalTime;
    /** Camel that processes the request */
    private Camel camel;
    /** Destination oasis */
    private Oasis oasis;
    /** How many stretchers are needed at destination */
    private int neededStretchers;
    /** Deadline time to handle the request */
    private double deadlineTime;
    /** Next unique identifier for the new instance  */
    private static int nextId = 1;
    /** Queue of all requests */
    private static Queue<Request> requests = new LinkedList<Request>();

    /**
     * Creating a new request
     * @param arrivalTime arrival time of the request as double
     * @param oasisIndex destination oasis index
     * @param neededStretchers how much stretchers are needed at destination
     * @param deadlineTime until what time it is needed to proceed
     * @throws Exception if the location could not be found by ID
     */
    public Request(double arrivalTime, int oasisIndex, int neededStretchers, double deadlineTime) throws Exception {
        this.id = nextId++;
        this.arrivalTime = arrivalTime;
        this.oasis = Oasis.getOasisByIndex(oasisIndex);
        this.neededStretchers = neededStretchers;
        this.deadlineTime = deadlineTime;
        this.currentTime = arrivalTime;

        requests.add(this);

        if (Settings.isTestMode()) {
            System.out.printf("New Request created. Attributes > arrivalTime: %.2f, oasisIndex: %d, neededStretchers: %d, deadlineTime: %.2f\n", arrivalTime, oasisIndex, neededStretchers, deadlineTime);
        }
    }

    /**
     * Function to get ID of request
     * @return ID of request
     */
    public int getId() {
        return id;
    }

    /**
     * Function to get arrival time of request
     * @return arrival time of request
     */
    public double getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Function to get destination oasis
     * @return destination oasis
     */
    public Oasis getOasis() {
        return oasis;
    }

    /**
     * Function to get count of needed stretchers at destination
     * @return how many stretchers are needed at destination
     */
    public int getNeededStretchers() {
        return neededStretchers;
    }

    /**
     * Function to get deadline time of request
     * @return deadline time of request
     */
    public double getDeadlineTime() {
        return deadlineTime;
    }

    /**
     * Function to get current time of request
     * @return current time of request
     */
    public double getCurrentTime() {
        return currentTime;
    }

    /**
     * Function to increase current time of request
     * @param time how many units of time to increase to current time
     */
    public void increaseCurrentTime(double time) {
        this.currentTime += time;
    }

    /**
     * Function to set current time of request
     * @param time value to set current time of request
     */
    public void setCurrentTime(double time) {
        this.currentTime = time;
    }

    /**
     * Function to get camel that processes the request
     * @return camel that processes the request
     */
    public Camel getCamel() {
        return camel;
    }

    /**
     * Function to set camel that processes the request
     * @param camel which camel processes the request
     */
    public void setCamel(Camel camel) {
        this.camel = camel;
    }

    /**
     * Function to get all requests to proceed
     * @return queue of requests to proceed
     */
    public static Queue<Request> getRequests() {
        return requests;
    }

    /**
     * Returns a text representation of the request
     * @return a text representation of the request
     */
    @Override
    public String toString() {
        return String.format("Pozadavek: %d", getId());
    }
}
