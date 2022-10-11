//Třída pro sklad, potomek Lokace
public class Storage extends Location {
    
    //Atributy
    private Stretcher[] stretchers; //Pole košů

    private double fillTime; //Čas do znovunaplnění košů
    private double loadTime; //Čas naložení koše na velblouda

    //Konstruktor s x, y, ....
    public Storage(int x, int y, int maxStretchers, double fillTime, double loadTime) {
        super(x, y); //Vytvoření lokace
        setId(nextId++); //Přiřazení ID

        this.fillTime = fillTime;
        this.loadTime = loadTime;

        stretchers = new Stretcher[maxStretchers]; //Vytvoření pole 

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

    @Override
    public String toString() {
        return String.format("Storage | ID: %d | X: %d | Y: %d | maxStretchers: %d | fillTime: %.2f | loadTime: %.2f", getId(), getX(), getY(), stretchers.length, fillTime, loadTime);
    }
}
