/**
 * Oasis class handles the specific oasis
 * @author Radek Nolč
 */
public class Oasis extends Location {

    /**
     * Creating new oasis
     * @param x the coordinate X of the oasis
     * @param y the coordinate Y of the oasis
     */
    public Oasis(int x, int y) {
        super(x, y);
        id = nextId++;
    }

    @Override
    public String toString() {
        return String.format("Oaza: %d", getId());
    }
}
