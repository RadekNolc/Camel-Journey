import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Camel class is inherited from Camel Template, this class is responsible for all the camels that comes from template.
 * @author Radek Nolč
 */
public class Camel extends CamelTemplate {
    
    /** Unique identifier */
    private int id;
    /** Home location (Storage) of camel */
    private Storage homeStorage;
    /** When camel arrives to home storage */
    private double arriveTime;
    /** Constant value (speed) that is generated to current camel */
    private double speed;
    /** How much camel can travel */
    private double maxStamina;
    /** How much camel can travel left */
    private double stamina;

    /** Next identifier value of camel */
    private static int nextId = 1;

    private static ArrayList<Camel> camels = new ArrayList<Camel>();

    /**
     * Constructor of Camel
     * @param name name of the camel, no blank chararacters
     * @param speedMin the minimum speed of the camel
     * @param speedMax the maximum speed of the camel
     * @param distanceMin the minimum distance of the camel
     * @param distanceMax the maximum distance of the camel
     * @param drinkTime the drink time of the camel
     * @param maxLoad the maximum number of stretchers to load
     * @param ratio the probability of generation
     * @throws Exception if an error occurs during calculation maximum distance
     */
    public Camel(String name, double speedMin, double speedMax, double distanceMin, double distanceMax, double drinkTime, int maxLoad, double ratio) throws Exception {
        super(name, speedMin, speedMax, distanceMin, distanceMax, drinkTime, maxLoad, ratio);
        this.id = nextId++;
        this.speed = Calculator.continuousDistribution(speedMin, speedMax);
        this.stamina = Calculator.normalDistribution(distanceMin, distanceMax);
        this.maxStamina = this.stamina;
        this.arriveTime = 0;

        if (Settings.isTestMode()) {
            this.speed = 10.0;
            this.stamina = 40.0;
            this.maxStamina = this.stamina;
        }

        camels.add(this);
    }

    /**
     * Sets Location to the camel's location, decreases stamina of the camel upon location change
     * @param destination the destination where the camel should go
     */
    public void move(Location destination) {
        if (!getLocation().equals(destination)) {
            setStamina(getStamina() - getLocation().calculateDirectDistance(destination));
            setLocation(destination);
        }
    }

    public void drink() {
        setStamina(maxStamina);
    }

    //Getters and setters, most setters are not needed
    public int getId() {
        return id;
    }

    public Storage getHomeStorage() {
        return homeStorage;
    }

    public void setHomeStorage(Storage storage) {
        homeStorage = storage;
    }

    //Getting speed
    public double getSpeed() {
        return speed;
    }

    public void setArriveTime(double arriveTime) {
        this.arriveTime = arriveTime;
    }

    public double getArriveTime() {
        return arriveTime;
    }

    //....
    private void setStamina(double stamina) {
        if (stamina >= maxStamina) {
            this.stamina = maxStamina;
        } else {
            this.stamina = stamina;
        }
    }

    public double getMaxStamina() {
        return maxStamina;
    }

    public double getStamina() {
        return stamina;
    }

    public static List<Camel> getCamels() {
        return Collections.unmodifiableList(camels);
    }

    @Override
    public String toString() {
        return String.format("Velbloud: %d", getId());
    }
    
}
