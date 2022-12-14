import java.io.File;

/**
 * Main class handling the whole program simulation
 * @author Radek Nolč
 */
public class Main {

    /**
     * Main method handling whole program simulation
     * @param args arguments to start program with
     * @throws Exception if an error occurs during simulation
     */
    public static void main(String[] args) throws Exception {

        Settings.setTestMode(true); /* Setting test mode */
        Settings.setInputFile("data/vstup.txt"); /* Setting input file */
        
        DataReader reader = new DataReader(new File(Settings.getInputFile())); /* Initialization of data reader */
        reader.processData(); /* Process data */

        /* Running simulation */
        Simulation simulation = new Simulation();
        simulation.run();
    }
}