public class Main {
    
	/**Creating Locations, Paths, CamelTemplates and Requests for the simulation
	 * 
	 */
    public static void main(String[] args) throws Exception {

        Settings.setTestMode(true); //Setting test mode
        
        //Vytvoření lokací
        Location obj1 = new Oasis(10, 10);
        Location obj2 = new Oasis(0, 0);
        Location obj3 = new Oasis(0, 10);
        Location obj4 = new Oasis(20, 20);
        Location obj5 = new Storage(30, 30, 3, 20, 3);
        Location obj6 = new Oasis(40, 40);
        Location obj7 = new Storage(0, 20, 3, 20, 3);

        //vytvoření cest
        Path path12 = new Path(1, 2);
        Path path13 = new Path(1, 3);
        Path path14 = new Path(1, 4);
        Path path45 = new Path(4, 5);
        Path path56 = new Path(5, 6);
        Path path37 = new Path(3, 7);

        //Vytvoření šablon pro velbloudy
        CamelTemplate pomaly = new CamelTemplate("Pomaly", 5, 10, 10, 50, 1, 1, 0.75);
        CamelTemplate ziznivy = new CamelTemplate("Ziznivy", 10, 10, 1, 1, 10, 1, 0.25);

        Request request1 = new Request(0, 3, 3, 30);
        Request request2 = new Request(20, 2, 1, 20);

        //Run
        Simulation sim = new Simulation();
        sim.run();
    }
}