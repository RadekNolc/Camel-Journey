/**
 * Point class handles the X and Y coordinates
 * @author Radek Nolč
 */
abstract class Point {

    /** X coordinate of point */
    private int x;
    /** Y coordinate of point */
    private int y;

    /**
     * Creating point by coordinates
     * @param x the coordinate X
     * @param y the coordinate Y
     */
    protected Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Function to get X coordinate of point
     * @return x coordinate of point
     */
    protected int getX() {
        return x;
    }

    /**
     * Function to get Y coordinate of point
     * @return y coordinate of point
     */
    protected int getY() {
        return y;
    }
}
