import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Class that handles the request.
 * @author Radek Nolč
 * @version 1.0
 */
public class Request {
    
    /** Unique identifier of each request. */
    private int id;
    /** Arrival time as double, when the request arrives. */
    private double arrival;
    /** Destination oasis index as int. */
    private int oasis;
    /** How many stretchers are needed in destination. */
    private int needed;
    /** Deadline to handle the request. */
    private double deadline;

    /** Next identifier. */
    private static int nextId = 1;

    private static Queue<Request> requests = new PriorityQueue<Request>();

    /**
     * Constructor of request
     * @param arrival arrival time of the request as double
     * @param oasis destination oasis index (id) as int
     * @param needed how much stretchers are needed in destination
     * @param deadline until what time it is needed to proceed
     */
    public Request(double arrival, int oasis, int needed, double deadline) {
        this.id = nextId++;
        this.arrival = arrival;
        this.oasis = oasis;
        this.needed = needed;
        this.deadline = deadline;

        requests.add(this);
    }

    public static Queue<Request> getRequests() {
        return requests;
    }
}
