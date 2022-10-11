//Definice daného bodu v souřadnicích, matka třídy Lokace
public class Point {

    //Definice atributů, očekává se int
    private int x;
    private int y;

    //Konstruktor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
