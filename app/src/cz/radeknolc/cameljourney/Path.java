package cz.radeknolc.cameljourney;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Path class handles the instance of both-way path between two locations
 * @author Radek Nolč
 */
public class Path {

    /** From where the path is */
    private final Location from;
    /** To where the path is */
    private final Location to;
    /** List that handles all the paths */
    private static List<Path> paths = new ArrayList<>();

    /**
     * Creating both-way path between two location IDs
     * @param from where the path starts as location ID
     * @param to where the path ends as location ID
     * @throws NullPointerException if the location(s) could not be found by ID
     */
    public Path(int from, int to) throws NullPointerException {
        this.from = Location.getLocationById(from);
        this.to = Location.getLocationById(to);

        paths.add(this);
    }

    /**
     * Creating both-way path between two locations
     * @param from where the path starts
     * @param to where the path ends
     * @throws NullPointerException if the location(s) could not be found by ID
     */
    public Path(Location from, Location to) throws NullPointerException {
        this.from = from;
        this.to = to;

        paths.add(this);
    }

    /**
     * Function to get where the path starts
     * @return where the path starts
     */
    public Location getFrom() {
        return from;
    }

    /**
     * Function to get where the path ends
     * @return where the path ends
     */
    public Location getTo() {
        return to;
    }

    /**
     * Function to get all paths
     * @return list of all paths
     */
    public static List<Path> getPaths() {
        return Collections.unmodifiableList(paths);
    }
}
