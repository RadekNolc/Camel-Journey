import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Camel class is inherited from Camel Template, this class is responsible for all the camels that are generated from template.
 * @author Radek Nolč
 */
public class Camel extends CamelTemplate {
    
    /** Unique identifier */
    private int id;
    /** Current location of camel */
    private Location location;
    /** Home location (Storage) of camel */
    private Storage homeStorage;
    /** When camel arrives to home storage after journey */
    private double arriveTime;
    /** Constant value (speed) that is generated to current camel */
    private double speed;
    /** How much camel can travel at maximum */
    private double maxStamina;
    /** How much camel can travel left */
    private double stamina;
    /** Current stretchers loaded */
    private int stretchers;
    /** Next unique identifier for the new instance */
    private static int nextId = 1;
    /** All created camels */
    private static ArrayList<Camel> camels = new ArrayList<Camel>();

    /**
     * Constructor of Camel
     * @param name name of the camel
     * @param speedMin minimum speed for calculation that can camel have
     * @param speedMax maximum speed for calculation that can camel have
     * @param distanceMin minimum distance for calculation that can camel travel
     * @param distanceMax maximum distance for calculation that can camel travel
     * @param drinkTime how much time it takes to drink
     * @param maxLoad how many stretchers are possible to load
     * @param ratio generation probability <0;1>
     * @throws Exception if an error occurs during calculation maximum distance
     */
    public Camel(String name, double speedMin, double speedMax, double distanceMin, double distanceMax, double drinkTime, int maxLoad, double ratio) throws Exception {
        super(name, speedMin, speedMax, distanceMin, distanceMax, drinkTime, maxLoad, ratio);
        this.id = nextId++;
        this.speed = Calculator.continuousDistribution(speedMin, speedMax);
        this.stamina = Calculator.normalDistribution(distanceMin, distanceMax);
        this.maxStamina = this.stamina;
        this.arriveTime = 0;
        this.stretchers = 0;

        /** Unified test mode settings */
        if (Settings.isTestMode()) {
            this.speed = 10.0;
            this.stamina = 40.0;
            this.maxStamina = this.stamina;
        }

        camels.add(this);
    }

    /**
     * Moves the camel to the specified location, decreases stamina of the camel upon location distance
     * @param destination where the camel should travel
     * @throws Exception if map has not been rendered yet
     */
    public void move(Location destination) throws Exception {
        double distance = Map.getTotalDistance(getLocation(), destination);
        if (!getLocation().equals(destination) && getStamina() >= distance) {
            setStamina(getStamina() - distance);
            setLocation(destination);
        }
    }

    /**
     * Function sets the camel's stamina to the maximum value
     */
    public void drink() {
        setStamina(maxStamina);
    }

    /**
     * Function to get camel's unique identifier 
     * @return camel's unique identifier
     */
    public int getId() {
        return id;
    }

    /**
     * Function to get current location of camel
     * @return current location of camel
     */
    protected Location getLocation() {
        return location;
    }

    /**
     * Function to set current location of camel
     * @param location which to set to the camel
     */
    protected void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Function to get camel's home storage
     * @return camel's home storage
     */
    public Storage getHomeStorage() {
        return homeStorage;
    }

    /**
     * Function to set camel's home storage
     * @param storage to be set as camel's home storage
     */
    public void setHomeStorage(Storage storage) {
        homeStorage = storage;
    }

    /**
     * Function to get camel's speed
     * @return constant camel's speed
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Function to get camel's arrival time from the journey
     * @return camel's arrival time from the journey
     */
    public double getArriveTime() {
        return arriveTime;
    }

    /**
     * Function to set camel's arrival time from the journey
     * @param arriveTime time when the camel finished the journey and is ready to make a new journey
     */
    public void setArriveTime(double arriveTime) {
        this.arriveTime = arriveTime;
    }

    /**
     * Function to get camel's current stamina
     * @return camel's current stamina
     */
    public double getStamina() {
        return stamina;
    }

    /**
     * Function to set camel's stamina
     * @param stamina value to set
     */
    private void setStamina(double stamina) {
        if (stamina >= maxStamina) {
            this.stamina = maxStamina;
        } else {
            this.stamina = stamina;
        }
    }

    /**
     * Function to get camel's maximum stamina value
     * @return camel's maximum stamina
     */
    public double getMaxStamina() {
        return maxStamina;
    }

    /**
     * Function to get camel's current loaded stretchers
     * @return how many stretchers are loaded
     */
    protected int getStretchers() {
        return stretchers;
    }

    /**
     * Function to set camel's current loaded strethers
     * @param count how many stretchers to set
     */
    protected void setStretchers(int count) {
        this.stretchers = count;
    }

    /**
     * Function to get list of all created camels
     * @return list of all created camels
     */
    public static List<Camel> getCamels() {
        return Collections.unmodifiableList(camels);
    }

    @Override
    public String toString() {
        return String.format("Velbloud: %d", getId());
    }
    
}
