/**
 * Stretcher class handles the Stretcher for the Camels.
 * @author Radek Nolč
 */
public class Stretcher {
    
    /** Time it takes to load a stretcher on a camel  */
    private double loadTime;

    /**
     * Constructor of stretcher
     * @param loadTime how much time it takes to load on the camel
     */
    public Stretcher(double loadTime) {
        this.loadTime = loadTime;
    }

    /**
     * Returning the load time of one stretcher
     * @return the load time it takes to load one piece on the camel
     */
    public double getLoadTime() {
        return loadTime;
    }
}
