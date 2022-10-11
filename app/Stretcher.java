//Třída pro koš na velblouda
public class Stretcher {
    
    private double loadTime; //Čas na naložení na velblouda

    //Konstruktor
    public Stretcher(double loadTime) {
        this.loadTime = loadTime;
    }

    //Getter na loadTime
    public double getLoadTime() {
        return loadTime;
    }
}
