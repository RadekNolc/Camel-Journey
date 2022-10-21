import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Definice lokace, přidání ID k dané lokaci, k bodu by nemělo smysl
/**Creating new locations and matching them to Ids*/
public class Location extends Point {
    
    //definice atributu s ID
	/**ID to match the location*/	
    private int id;
    /**?*/
    protected static int nextId = 1;

    /**Creating new Arraylist for future locations*/
    private static ArrayList<Location> locations = new ArrayList<>();

    //prostě konstruktor, IDčko se přiřazuje v potomcích
    /**Constructor for location using coordinates and adding it to the list
     * @param x coordinate X
     * @param y coordinate Y
     */
    public Location(int x, int y) {
        super(x, y);
        locations.add(this);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    /**Calculator a direct distance from the currect point to destination
     * @param destination the final destination for calculation using coordinates
     * @return the calculated distance
     */
    public double calculateDirectDistance(Location destination) {
        return Math.sqrt(Math.abs((getX() - destination.getX()) * (getX() - destination.getX())) + Math.abs((getY() - destination.getY()) * (getY() - destination.getY())));
    }
    
    /**Getting all locations from the list
     * @return unmodifiablelist with all locations
     */
    public static List<Location> getLocations() {
        return Collections.unmodifiableList(locations);
    }
    
    /**Getting location by using ID
     * @param id
     * @return the founded location
     * @throws if the current ID is not match with any location
     */
    public static Location getLocationById(int id) throws Exception {
        Location location = locations.get(id - 1);
        if (location != null) {
            return location;
        }

        throw new Exception("Could not find Location with ID " + id);
    }
}
