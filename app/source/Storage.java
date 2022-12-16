import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Storage class handles the specific storage
 * @author Radek Nolč
 */
public class Storage extends Location {
    
    /** Current count of stretchers available */
    private int stretchers;
    /** Maximum of stretchers that can be hold in storage */
    private int maxStretchers;
    /** How much time it takes to fill again stretchers */
    private double fillTime; 
    /** When has been storage filled last */
    private double lastFillTime;
    /** How much time it takes to load one stretcher to camel */
    private double loadTime;
    /** Next index for the new instance */
    private static int nextIndex = 1;

    /**
     * Creating new storage
     * @param x the coordinate X of the storage
     * @param y the coordinate Y of the storage
     * @param maxStretchers how many stretchers can storage have
     * @param fillTime how much time it takes to fill stretchers
     * @param loadTime how much time it takes to load one stretcher to camel
     */
    public Storage(double x, double y, int maxStretchers, double fillTime, double loadTime) {
        super(x, y);
        id = nextId++;
        index = nextIndex++;

        this.fillTime = fillTime;
        this.loadTime = loadTime;
        this.maxStretchers = maxStretchers;

        lastFillTime = 0;
        refillStretchers();

        if (Settings.isTestMode()) {
            System.out.printf("New Storage created. Attributes > x: %.2f, y: %.2f, maxStretchers: %d, fillTime: %.2f, loadTime: %.2f\n", x, y, maxStretchers, fillTime, loadTime);
        }
    }

    /**
     * Function to get if storage has enough stretchers available
     * @param count how many stretchers should be available
     * @return if storage has enough stretchers available returns true, else returns false
     */
    public boolean hasAvailableStretcher(int count) { 
        return (count <= stretchers);
    }

    /**
     * Function to remove stretchers from storage
     * @param count how many stretchers to remove
     */
    public void removeStretchers(int count) {
        this.stretchers -= count;
    }

    /**
     * Function to refill all possible stretchers to maximum
     */
    public void refillStretchers() {
        this.stretchers += maxStretchers - stretchers;
    }

    /**
     * Function to get load time of one stretcher
     * @return load time of one stretcher
     */
    public double getLoadTime() {
        return loadTime;
    }

    /**
     * Function to get fill time of stretchers to maximum value
     * @return fill time of maximum count of stretchers
     */
    public double getFillTime() {
        return fillTime;
    }

    /**
     * Function to get when was the last fill time of storage
     * @return when was the last fill time of storage
     */
    public double getLastFillTime() {
        return lastFillTime;
    }

    /**
     * Function to set the last fill time of storage
     * @param lastFillTime time to set as last fill time of storage
     */
    public void setLastFillTime(double lastFillTime) {
        this.lastFillTime = lastFillTime;
    }

    /**
     * Function to get all storages
     * @return list of all storages
     */
    public static List<Storage> getStorages() {
        List<Storage> storages = new ArrayList<Storage>();

        for (Location location : Location.getLocations()) {
            if (location instanceof Storage)
                storages.add((Storage)location);
        }

        return Collections.unmodifiableList(storages);
    }

    /**
     * Getting camels that are at storage
     * @return list of camels that are at storage
     */
    public List<Camel> getCamels() {
        List<Camel> result = new ArrayList<Camel>();
        for (Camel camel : Camel.getCamels()) {
            if (camel.getLocation().equals(this)) {
                result.add(camel);
            }
        }

        return Collections.unmodifiableList(result);
    }

    /**
     * Getting existing storage by index
     * @param index index of searched storage
     * @return the found storage
     * @throws Exception if the storage with the specified index does not exist
     */
    public static Storage getStorageByIndex(int index) throws Exception {
        for (Location location : Location.getLocations()) {
            if (location instanceof Storage && location.getIndex() == index) {
                return (Storage) location;
            }
        }

        throw new Exception("Could not find storage with index " + index);
    }

    @Override
    public String toString() {
        return String.format("Sklad: %d", getIndex());
    }
}
