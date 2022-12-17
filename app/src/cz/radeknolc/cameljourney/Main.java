package cz.radeknolc.cameljourney;

import java.io.File;
import java.io.IOException;

/**
 * Main class handling the whole program simulation
 * @author Radek Nolč
 */
public class Main {

    /**
     * Main method handling whole program simulation
     * @param args arguments to start program with
     * @throws IOException if an error occurs during simulation
     * @throws RuntimeException if an error occurs while processing requests
     * @throws NoSuchFieldException if the map has not been rendered
     */
    public static void main(String[] args) throws IOException, RuntimeException, NoSuchFieldException {

        DataReader reader = new DataReader(new File(Settings.getInputFile())); /* Initialization of data reader */

        reader.processData(); /* Process data */

        /* Running simulation */
        Simulation simulation = new Simulation();
        simulation.run();
    }
}