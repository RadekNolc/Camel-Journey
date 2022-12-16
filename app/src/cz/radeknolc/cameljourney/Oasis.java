package cz.radeknolc.cameljourney;
/**
 * Oasis class handles the specific oasis
 * @author Radek Nolč
 */
public class Oasis extends Location {

    /** Next index for the new instance */
    private static int nextIndex = 1;

    /**
     * Creating new oasis
     * @param x the coordinate X of the oasis
     * @param y the coordinate Y of the oasis
     */
    public Oasis(double x, double y) {
        super(x, y);
        id = nextId++;
        index = nextIndex++;

        if (Settings.isTestMode()) {
            System.out.printf("New Oasis created. Attributes > x: %.2f, y: %.2f\n", x, y);
        }
    }

    /**
     * Getting existing oasis by index
     * @param index index of searched oasis
     * @return the found oasis
     * @throws Exception if the oasis with the specified index does not exist
     */
    public static Oasis getOasisByIndex(int index) throws Exception {
        for (Location location : Location.getLocations()) {
            if (location instanceof Oasis && location.getIndex() == index) {
                return (Oasis) location;
            }
        }

        throw new Exception("Could not find oasis with index " + index);
    }

    /**
     * Returns a text representation of the oasis
     * @return a text representation of the oasis
     */
    @Override
    public String toString() {
        return String.format("Oaza: %d", getIndex());
    }
}
