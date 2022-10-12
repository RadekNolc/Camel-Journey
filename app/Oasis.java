//Definice oázy, potomek Lokace
public class Oasis extends Location {

    //Konstruktor
    public Oasis(int x, int y) {
        super(x, y);
        setId(nextId++); //Defincie IDčka
    }

    @Override
    public String toString() {
        return String.format("Oaza: %d", getId()); //Pouze pro výpis
    }
}
