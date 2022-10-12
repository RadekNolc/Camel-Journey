/**
 * Camel class is inherited from Camel Template, this class is responsible for all the camels that comes from template.
 * @author Radek Nolč
 */
public class Camel extends CamelTemplate {
    
    /** Unique identifier */
    private int id;
    /** Current location of camel */
    private Location location;
    /** Constant value (speed) that is generated to current camel */
    private double speed;
    /** How much camel can travel */
    private double stamina;

    /** Next identifier value of camel */
    private static int nextId = 1;

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
    }

    //Přesun do nějaké destinace
    public void move(Location destination) throws Exception {
        setStamina(getStamina() - getLocation().calculateDirectDistance(destination));
        setLocation(destination);
    }

    //Getters and setters, most setters are not needed
    public int getId() {
        return id;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    //Getting speed
    public double getSpeed() {
        return speed;
    }

    //....
    public void setStamina(double stamina) {
        this.stamina = stamina;
    }

    public double getStamina() {
        return stamina;
    }

    @Override
    public String toString() {
        return String.format("Velbloud: %d", getId());
    }
    
}
