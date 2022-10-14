public class Main {

    //public static Scanner sc;
    
    public static void main(String[] args) throws Exception {
        
        /*File file = new File("vstup.txt");
        sc = new Scanner(file);*/
        
        //Vytvoření lokací
        /**Creating a new location - oasis and warehauses
         * Oases = key destinations for our business, camels and aopportunities for camels to recover and reply their energy reserves (if necessary)
         * Warehauses (Storages)= Starting points of our camels and the place to which the camels return to replenish further supplies
         * 
         * @param of Oases: Coordinate X, Coordinate Y
         * @param of warehauses (Storage): Coordinate X, Coordinate Y, the number of containers tha will be/are being refilled, the time it takes to refill, the time for loading/unloading containers on camel
         * 
         * ...snad?
         *  --- asi bych zmenil storages na warehause at je to i v tom jazyce srovnany
          */
        Location obj1 = new Storage(10, 10, 3, 1000, 1);
        Location obj2 = new Oasis(0, 0);
        Location obj3 = new Oasis(0, 20); //TODO: Změnit na 0, 10
        Location obj4 = new Oasis(20, 20);
        Location obj5 = new Oasis(30, 30);
        Location obj6 = new Oasis(40, 40);
        Location obj7 = new Storage(50, 50, 3, 1000, 1);

        //vytvoření cest
        /**Creating a new paths to connect locations with warehauses (storages*)
         *@param of Path: indices i,j indicating places (Oasis, warehause)
         */
        Path path12 = new Path(1, 2);
        Path path13 = new Path(1, 3);
        Path path14 = new Path(1, 4);
        Path path45 = new Path(4, 5);
        Path path56 = new Path(5, 6);
        Path path67 = new Path(6, 7);

        //Vytvoření šablon pro velbloudy
        /**Creating  templates of camels that represent individual camels with different features
         *@param of a each cameltemplate: type of camel, min speed, max speed, min possible distance of the species, max possible distance of the species, time the species need to recover, max number of containers that a certain type of camel can load, proportion of species in the herd (interval from 0 to 1, including)
          
          * mozna by bylo lepsi stylem a,b,c...?
          * a=...
          * b...
          * c...
          
          */
        
        CamelTemplate pomaly = new CamelTemplate("Pomaly", 5, 10, 10, 50, 0, 1, 0.7);
        CamelTemplate ziznivy = new CamelTemplate("Ziznivy", 10, 10, 1, 1, 10, 1, 0.30);
        
/**Receiving requests
 * @param of a request: time of arrival of the request, oasis index as the final destination of the request, number of containers required, expiry time)
 */
        Request request1 = new Request(0, 2, 1, 1000);

        //Run
        Simulation sim = new Simulation();
        sim.run();
    }
}