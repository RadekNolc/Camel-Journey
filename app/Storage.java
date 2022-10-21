import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Třída pro sklad, potomek Lokace
public class Storage extends Location {
    
    //Atributy
    private int stretchers; //Pole košů
    private int maxStretchers;

    private double fillTime; //Čas do znovunaplnění košů
    private double lastFillTime; //Kdy naposled bylo naplněno
    private double loadTime; //Čas naložení koše na velblouda

    //Konstruktor s x, y, ....
    public Storage(int x, int y, int maxStretchers, double fillTime, double loadTime) {
        super(x, y); //Vytvoření lokace
        setId(nextId++); //Přiřazení ID

        this.fillTime = fillTime;
        this.loadTime = loadTime;
        this.maxStretchers = maxStretchers;

        lastFillTime = 0;
        refillStretchers(); //Naplnění košů
    }

    //Zjištění, zda ve skladu je nějaký dostupný koš, vrací true/false
    public boolean hasAvailableStretcher(int count) { //Přetížení metody
        return (count <= stretchers);
    }

    //Funkce sníží počet košů
    public void removeStretchers(int count) {
        this.stretchers -= count;
    }

    //Znovunaplnění košů do skladu
    public void refillStretchers() {
        this.stretchers = maxStretchers;
    }

    // Zjištění všech skladů
    public static List<Storage> getStorages() {
        List<Storage> storages = new ArrayList<Storage>();

        for (Location location : Location.getLocations()) {
            if (location instanceof Storage)
                storages.add((Storage)location);
        }

        return Collections.unmodifiableList(storages);
    }

    public double getLoadTime() {
        return loadTime;
    }

    public double getFillTime() {
        return fillTime;
    }

    public double getLastFillTime() {
        return lastFillTime;
    }

    public void setLastFillTime(double lastFillTime) {
        this.lastFillTime = lastFillTime;
    }

    /**
     * Getting camels that are at storage
     * @return
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

    @Override
    public String toString() {
        return String.format("Sklad: %d", getId());
    }
}
