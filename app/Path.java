import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Path class handles the instance of path between two locations
 * @author Radek Nolč
 * @version 1.0
 */
public class Path {
    /** From where the path is */
    private Location from;
    /** To where the path is */
    private Location to;

    /** ArrayList that handles all the paths */
    private static ArrayList<Path> paths = new ArrayList<>();

    /**
     * Constructor
     * @param from
     * @param to
     * @throws Exception
     */
    public Path(int from, int to) throws Exception {
        this.from = Location.getLocationById(from);
        this.to = Location.getLocationById(to);

        paths.add(this);
    }

    public Path(Location from, Location to) throws Exception {
        this.from = from;
        this.to = to;

        paths.add(this);
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public static List<Path> getPaths() {
        return Collections.unmodifiableList(paths);
    }
}
