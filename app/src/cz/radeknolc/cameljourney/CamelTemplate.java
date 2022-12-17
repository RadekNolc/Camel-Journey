package cz.radeknolc.cameljourney;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Camel Template is class that handles information about possible camels that can be generated.
 * @author Radek Nolč
 */
public class CamelTemplate {
    
    /** Camel template's name */
    private final String templateName;
    /** Minimum speed for calculation that can camel have */
    private final double speedMin;
    /** Maximum speed for calculation that can camel have */
    private final double speedMax;
    /** Minimum distance for calculation that can camel travel */
    private final double distanceMin;
    /** Maximum distance for calculation that can camel travel */
    private final double distanceMax;
    /** How much time it takes to drink */
    private final double drinkTime;
    /** How many stretchers are possible to load */
    private final int maxLoad;
    /** Generation probability <0;1> */
    private final double ratio;
    /** List that contains all the camel templates of the program. */
    private static List<CamelTemplate> camelTemplates = new ArrayList<CamelTemplate>();

    /**
     * Constructor of Camel Template
     * @param templateName name of the camel template
     * @param speedMin minimum speed for calculation that can camel have
     * @param speedMax maximum speed for calculation that can camel have
     * @param distanceMin minimum distance for calculation that can camel travel
     * @param distanceMax maximum distance for calculation that can camel travel
     * @param drinkTime how much time it takes to drink
     * @param maxLoad how many stretchers are possible to load
     * @param ratio generation probability from 0.00 to 1.00, 1.00 equals to 100 %
     */
    public CamelTemplate(String templateName, double speedMin, double speedMax, double distanceMin, double distanceMax, double drinkTime, int maxLoad, double ratio) {
        this.templateName = templateName;
        this.speedMin = speedMin;
        this.speedMax = speedMax;
        this.distanceMin = distanceMin;
        this.distanceMax = distanceMax;
        this.drinkTime = drinkTime;
        this.maxLoad = maxLoad;
        this.ratio = ratio;

        camelTemplates.add(this);
    }

    /**
     * Function to get the name of the camel template
     * @return template name of the camel (from camel template)
     */
    protected String getTemplateName() {
        return templateName;
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
