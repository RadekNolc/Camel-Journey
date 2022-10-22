import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Camel Template is class that handles information about possible camels that can be generated.
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
    /** List that contains all the camel templates of the program. */
    private static ArrayList<CamelTemplate> camelTemplates = new ArrayList<CamelTemplate>();

    /**
     * Constructor of Camel Template
     * @param name name of the camel
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
     * Function to return the time of drinking
     * @return how much time it is needed to drink
     */
    protected double getDrinkTime() {
        return drinkTime;
    }

    /**
     * Function to get max stretchers load of the camel
     * @return how many stretchers are possible to load on the camel
     */
    protected int getMaxLoad() {
        return maxLoad;
    }

    /**
     * Function to get the ratio of generation for the current template
     * @return ratio of generation for the current template
     */
    protected double getRatio() {
        return ratio;
    }

    /**
     * Function to get minimum speed camel can have from the current template
     * @return minimum speed camel can have from the current template
     */
    protected double getSpeedMin() {
        return speedMin;
    }

    /**
     * Function to get maximum speed camel can have from the current template
     * @return maximum speed camel can have from the current template
     */
    protected double getSpeedMax() {
        return speedMax;
    }

    /**
     * Function to get maximum distance camel can have from the current template
     * @return maximum distance camel can have from the current template
     */
    protected double getDistanceMax() {
        return distanceMax;
    }

    /**
     * Function to get minimum speed camel can have from the current template
     * @return minimum speed camel can have from the current template
     */    
    protected double getDistanceMin() {
        return distanceMin;
    }

    /**
     * Function to get all possible camels from templates
     * @return list with all possible camels from templates
     */
    public static List<CamelTemplate> getCamelTemplates() {
        return Collections.unmodifiableList(camelTemplates);
    }
    
}
