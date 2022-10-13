import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Camel Template is class that handles information about possible camels that can be factored.
 * @author Radek Nolč
 */
public class CamelTemplate {
    
    /** Camel's name */
    private String name;
    /** Minimum speed for calculation that can camel have */
    private double speedMin;
    /** Maximum speed for calculation that can camel have */
    private double speedMax;
    /** Minimum distance for calculation that can camel travel */
    private double distanceMin;
    /** Maximum distance for calculation that can camel travel */
    private double distanceMax;
    /** How much time it takes to drink */
    private double drinkTime;
    /** How many stretchers are possible to load */
    private int maxLoad;
    /** Generation probability <0;1> */
    private double ratio;
    /** Current location of camel */
    private Location location;

    /** Current stretchers loaded */
    private Stretcher[] stretchers;

    /** Array List that handles all the camel templates of the program. */
    private static ArrayList<CamelTemplate> camelTemplates = new ArrayList<>();

    /**
     * Constructor of Camel
     * @param name name of the camel, no blank chararacters
     * @param speedMin minimum speed for calculation that can camel have
     * @param speedMax maximum speed for calculation that can camel have
     * @param distanceMin minimum distance for calculation that can camel travel
     * @param distanceMax maximum distance for calculation that can camel travel
     * @param drinkTime how much time it takes to drink
     * @param maxLoad how many stretchers are possible to load
     * @param ratio generation probability <0;1>
     */
    public CamelTemplate(String name, double speedMin, double speedMax, double distanceMin, double distanceMax, double drinkTime, int maxLoad, double ratio) {
        this.name = name;
        this.speedMin = speedMin;
        this.speedMax = speedMax;
        this.distanceMin = distanceMin;
        this.distanceMax = distanceMax;
        this.drinkTime = drinkTime;
        this.maxLoad = maxLoad;
        this.ratio = ratio;

        this.stretchers = new Stretcher[maxLoad];

        if (!(this instanceof Camel)) //Adding to camel templates
            camelTemplates.add(this);
    }

    /**
     * Function to get the name of the camel
     * @return default name of the camel (from camel template)
     */
    protected String getName() {
        return name;
    }

    /**
     * Function to return the time of drinking (renewing stamina)
     * @return how much time it is needed to drink to refresh stamina
     */
    protected double getDrinkTime() {
        return drinkTime;
    }

    protected int getMaxLoad() {
        return maxLoad;
    }

    protected double getRatio() {
        return ratio;
    }

    protected double getSpeedMin() {
        return speedMin;
    }

    protected double getSpeedMax() {
        return speedMax;
    }

    protected double getDistanceMin() {
        return distanceMin;
    }

    protected double getDistanceMax() {
        return distanceMax;
    }

    protected void setLocation(Location location) {
        this.location = location;
    }

    protected Location getLocation() {
        return location;
    }

    /**
     * Function to get all camel templates
     * @return unmodifiable list with camel templates
     */
    public static List<CamelTemplate> getCamelTemplates() {
        return Collections.unmodifiableList(camelTemplates);
    }
    
}
