import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Definice lokace, přidání ID k dané lokaci, k bodu by nemělo smysl
public class Location extends Point {
    
    //definice atributu s ID
    private int id;
    protected static int nextId = 1;

    private static ArrayList<Location> locations = new ArrayList<>();

    //prostě konstruktor, IDčko se přiřazuje v potomcích
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

    public double calculateDirectDistance(Location destination) {
        return Math.sqrt(Math.abs((getX() - destination.getX()) * (getX() - destination.getX())) + Math.abs((getY() - destination.getY()) * (getY() - destination.getY())));
    }
    
    public static List<Location> getLocations() {
        return Collections.unmodifiableList(locations);
    }

    public static Location getLocationById(int id) throws Exception {
        Location location = locations.get(id - 1);
        if (location != null) {
            return location;
        }

        throw new Exception("Could not find Location with ID " + id);
    }
}
