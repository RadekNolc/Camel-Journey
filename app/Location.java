import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Definice lokace, přidání ID k dané lokaci, k bodu by nemělo smysl

/** Definition of location, matching an ID with the location*/
public class Location extends Point {
    
    //definice atributu s ID
    /**Setting new private attributes*/
    private int id;
    protected static int nextId = 1;

/**Creating a new ArrayList for future locations*/
    private static ArrayList<Location> locations = new ArrayList<>();

    //prostě konstruktor, IDčko se přiřazuje v potomcích
    /**Just a constructor
     * coordinates X,Y in the Cartesian system
     * @param x
     * @param y*/
    public Location(int x, int y) {
        super(x, y);
        locations.add(this);
    }

/**Setting a new ID
 * @para, id*/
    public void setId(int id) {
        this.id = id;
    }

/**Getter...*/
    public int getId() {
        return id;
    }
/**Simple calculator of a direct distance from a current location to the final destianation
 * @param Location ....?
 * @param destination ...?
 * */
    public double calculateDirectDistance(Location destination) {
        return Math.sqrt(Math.abs((getX() - destination.getX()) * (getX() - destination.getX())) + Math.abs((getY() - destination.getY()) * (getY() - destination.getY())));
    }
    
   /** ? */ 
    public static List<Location> getLocations() {
        return Collections.unmodifiableList(locations);
    }

/**Location finder by using ID
 * ...snad?
 * @param id*/
    public static Location getLocationById(int id) throws Exception {
        Location location = locations.get(id - 1);
        
        if (location != null) {
            return location;
        }

        throw new Exception("Could not find Location with ID: " + id);
    }
}
