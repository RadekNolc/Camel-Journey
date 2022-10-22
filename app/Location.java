import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** 
 * Location class handles the specific location identified by IDs 
 * @author Radek Nolč
 */
abstract class Location extends Point {
    
	/** Unique identifier */	
    protected int id;
    /** Next unique identifier for the new instance */
    protected static int nextId = 1;
    /** All created locations */
    private static ArrayList<Location> locations = new ArrayList<>();

    /**
     * Creating new location
     * @param x the coordinate X of the location
     * @param y the coordinate Y of the location
     */
    protected Location(int x, int y) {
        super(x, y);
        locations.add(this);
    }

    /**
     * Function to get the ID of the location
     * @return the id of the location
     */
    protected int getId() {
        return id;
    }
    
    /**
     * Getting all created locations
     * @return list including all created locations
     */
    public static List<Location> getLocations() {
        return Collections.unmodifiableList(locations);
    }
    
    /**
     * Getting existing location by ID
     * @param id unique identifier of searched location
     * @return the found location
     * @throws Exception if the location with the specified ID does not exist
     */
    public static Location getLocationById(int id) throws Exception {
        Location location = locations.get(id - 1);
        if (location != null) {
            return location;
        }

        throw new Exception("Could not find Location with ID " + id);
    }
}
