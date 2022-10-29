import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * DataReader class is used for reading simulation data
 * @author Radek Nolč
 */
public class DataReader {
    
    /** Scanner instance */
    private Scanner scanner;

    /**
     * Creating data reader instance
     * @param file input file to read
     */
    public DataReader(File file) {
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + file.getAbsolutePath());
        }
    }

    /**
     * Function to read simulation data and clear it from comments etc.
     * @return list of simulation data
     */
    private ArrayList<String> getData() {
        /* Array list with data */
        ArrayList<String> data = new ArrayList<String>();

        /* Reading file */
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("🐪")) {
                continue;
            }

            if (!line.equals("")) {
                data.add(line);
            }
        }

        return data;
    }

    /**
     * Function to process data and load it into simulation
     * @throws Exception
     * @throws NumberFormatException
     */
    public void processData() throws NumberFormatException, Exception {
        ArrayList<String> data = getData();

        boolean loaded = false;
        int property = 0;
        int index = 0;
        int propertyCount = 0;

        while (!loaded) {
            for (int i = 0; i < Integer.parseInt(data.get(propertyCount)); i++) {
                String[] values = data.get(index+1).split("\s");
                switch (property) {
                    case 0:
                        new Storage(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), Double.parseDouble(values[3]), Double.parseDouble(values[4]));
                    break;

                    case 1:
                        new Oasis(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                    break;

                    case 2:
                        new Path(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                    break;

                    case 3:
                        new CamelTemplate(values[0], Double.parseDouble(values[1]), Double.parseDouble(values[2]), Double.parseDouble(values[3]), Double.parseDouble(values[4]), Double.parseDouble(values[5]), Integer.parseInt(values[6]), Double.parseDouble(values[7]));
                    break;

                    case 4:
                        new Request(Double.parseDouble(values[0]), Integer.parseInt(values[1]), Integer.parseInt(values[2]), Double.parseDouble(values[3]));
                    break;
                }

                index++;
            }

            index++; /* Moving on */
            propertyCount = index;
            property++;

            if (property > 4) {
                loaded = true;
            }
        }
    }
}
