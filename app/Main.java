import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    //public static Scanner sc;
    
    public static void main(String[] args) throws Exception {
        
        /*File file = new File("vstup.txt");
        sc = new Scanner(file);*/
        

        
        //Vytvoření lokací
        Location obj1 = new Storage(10, 10, 3, 1000, 1);
        Location obj2 = new Oasis(0, 0);
        Location obj3 = new Oasis(0, 10);
        Location obj4 = new Oasis(20, 20);
        Location obj5 = new Oasis(30, 30);
        Location obj6 = new Oasis(40, 40);

        //vytvoření cest
        Path path12 = new Path(1, 2);
        Path path13 = new Path(1, 3);
        Path path14 = new Path(1, 4);
        Path path45 = new Path(4, 5);
        Path path56 = new Path(5, 6);

        //Vytvoření šablon pro velbloudy
        CamelTemplate pomaly = new CamelTemplate("Pomaly", 5, 10, 10, 50, 0, 1, 0.7);
        CamelTemplate ziznivy = new CamelTemplate("Ziznivy", 10, 10, 1, 1, 10, 1, 0.30);

        Request request1 = new Request(0, 2, 1, 1000);

        //Run
        Simulation sim = new Simulation();
        sim.run();
    }
}