import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Třída pro sklad, potomek Lokace
public class Storage extends Location {
    
    //Atributy
    private Stretcher[] stretchers; //Pole košů
    private ArrayList<Camel> camels;

    private double fillTime; //Čas do znovunaplnění košů
    private double loadTime; //Čas naložení koše na velblouda

    //Konstruktor s x, y, ....
    public Storage(int x, int y, int maxStretchers, double fillTime, double loadTime) {
        super(x, y); //Vytvoření lokace
        setId(nextId++); //Přiřazení ID

        this.fillTime = fillTime;
        this.loadTime = loadTime;

        stretchers = new Stretcher[maxStretchers]; //Vytvoření pole 
        camels = new ArrayList<>(); //Vytvoření ArrayListu velbloudů

        refillStretchers(); //Naplnění košů
    }

    //Zjištění, zda ve skladu je nějaký dostupný koš, vrací true/false
    public boolean hasAvailableStretcher() {
        return (getAvailableStretcher() != null);
    }

    //Zjištění prvního dostupného koše
    public Stretcher getAvailableStretcher() {
        for (int i = 0; i < stretchers.length; i++) {
            if (stretchers[i] != null) return stretchers[i];
        }

        return null;
    }

    //Znovunaplnění košů do skladu
    public void refillStretchers() {
        for (int i = 0; i < stretchers.length; i++) {
            stretchers[i] = new Stretcher(loadTime);
        }
    }

    public Camel generateCamel() throws Exception {
        Camel camel = Factory.camel();
        camel.setLocation(this);

        camels.add(camel);
        return camel;
    }

    public static List<Storage> getStorages() {
        List<Storage> storages = new ArrayList<Storage>();

        for (Location location : Location.getLocations()) {
            if (location instanceof Storage)
                storages.add((Storage)location);
        }

        return Collections.unmodifiableList(storages);
    }

    @Override
    public String toString() {
        return String.format("Sklad: %d", getId());
    }
}
