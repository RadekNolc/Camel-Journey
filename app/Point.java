/**
 * Point class handles the X and Y coordinates
 * @author Radek Nolč
 */
abstract class Point {

    /** X coordinate of point */
    private double x;
    /** Y coordinate of point */
    private double y;

    /**
     * Creating point by coordinates
     * @param x the coordinate X
     * @param y the coordinate Y
     */
    protected Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Function to get X coordinate of point
     * @return x coordinate of point
     */
    protected double getX() {
        return x;
    }

    /**
     * Function to get Y coordinate of point
     * @return y coordinate of point
     */
    protected double getY() {
        return y;
    }
}
