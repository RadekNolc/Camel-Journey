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

        /* Measuring time */
        long start = 0;
        long end = 0;

        //TODO: Vymazat
        Settings.setTestMode(true); /* Setting test mode */
        Settings.setInputFile("data/test.txt"); /* Setting input file */

        DataReader reader = new DataReader(new File(Settings.getInputFile())); /* Initialization of data reader */

        if (Settings.isTestMode()) {
            System.out.println("Starting data processing...");
            start = System.nanoTime();
        }

        reader.processData(); /* Process data */

        if (Settings.isTestMode()) {
            end = System.nanoTime();
            System.out.printf("File processing finished in %.2f seconds\n", (double) (end - start) / 1_000_000_000);
        }

        /* Running simulation */
        Simulation simulation = new Simulation();
        //simulation.run();
    }
}