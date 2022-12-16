import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * DataReader class is used for reading simulation data
 * @author Radek Nolč
 */
public class DataReader {
    
    /** Getting read file */
    private File file;

    /**
     * Creating data reader instance
     * @param file input file to read
     */
    public DataReader(File file) {
        this.file = file;
    }

    /**
     * Function to read input file and convert it into a string
     * @return file content as string
     * @throws Exception if there was an error reading input file
     */
    private StringBuilder readFileAsString() throws Exception {
        StringBuilder fileAsString = new StringBuilder();
        Scanner scanner = null;
        FileInputStream inputStream = null;

        try {
            inputStream = new FileInputStream(file);
            scanner = new Scanner(inputStream);

            while (scanner.hasNextLine()) {
                fileAsString.append(scanner.nextLine()).append(" "); /* Space instead new line on the end of line */
            }

            if (scanner.ioException() != null) {
                throw scanner.ioException();
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }

            if (scanner != null) {
                scanner.close();
            }
        }

        if (fileAsString.isEmpty()) {
            throw new Exception("Dokument nejspíš neobsahuje žádný text.");
        }

        return fileAsString;
    }

    /**
     * Function to read simulation data and clear it from comments etc.
     * @return list of simulation data
     * @throws Exception if there was an error reading file as text
     */
    private StringBuilder getCleanedData() throws Exception {
        /* Basic init */
        StringBuilder input = readFileAsString();
        ArrayList<Integer> commentStarts = new ArrayList<Integer>();

        /* Searching for comments */
        int index = input.indexOf(Settings.getCommentStart());
        while (index >= 0) {
            commentStarts.add(index);
            index = input.indexOf(Settings.getCommentStart(), index + 1);
        }

        /* Cleaning input */
        while (input.indexOf(Settings.getCommentStart()) >= 0 && input.indexOf(Settings.getCommentEnd()) >= 0) {

            /* Getting last comment start */
            int start = commentStarts.get(commentStarts.size() - 1);
            
            /* Getting nearest comment after last comment start */
            int end = input.indexOf(Settings.getCommentEnd(), start);
            
            /* Removing text in comment */
            input = new StringBuilder(input.substring(0, start) + " " + input.substring(end + Settings.getCommentEnd().length(), input.length()));
            commentStarts.remove(commentStarts.size() - 1);
        }

        return input;
    }

    /**
     * Function to process data and load it into simulation
     * @throws Exception
     * @throws NumberFormatException
     */
    public void processData() throws NumberFormatException, Exception {
        /* Retrieving data */
        StringBuilder data = getCleanedData();

        /* Basic init */
        Pattern anyNumberPattern = Pattern.compile(/*"(\\-?\\d*\\.?\\d*)(?:\\^|e)(\\-?\\d*\\.?\\d*)"*/"(\\-?\\d*\\.?\\d*)((?:\\^|e)(\\-?\\d*\\.?\\d*))?");
        Scanner scanner = null;

        int propertyType = 0; /* 0 = storage, 1 = oasis, 2 = path, 3 = camel template, 4 = request */

        try {
            /* Reading data using scanner */
            scanner = new Scanner(data.toString());

            /* Reading each number */
            while(scanner.hasNext(anyNumberPattern)) {
                
                /* Getting how many properties to init */
                int propertyCount = Integer.parseInt(scanner.next(anyNumberPattern));

                for (int i = 0; i < propertyCount; i++) {

                    /* Reading specific attributes for specific property */
                    switch (propertyType) {
                        case 0:
                            new Storage(Double.parseDouble(scanner.next(anyNumberPattern)), Double.parseDouble(scanner.next(anyNumberPattern)), Integer.parseInt(scanner.next(anyNumberPattern)), Double.parseDouble(scanner.next(anyNumberPattern)), Double.parseDouble(scanner.next(anyNumberPattern)));
                            break;
                            
                        case 1:
                            new Oasis(Double.parseDouble(scanner.next(anyNumberPattern)), Double.parseDouble(scanner.next(anyNumberPattern)));
                            break;

                        case 2:
                            new Path(Integer.parseInt(scanner.next(anyNumberPattern)), Integer.parseInt(scanner.next(anyNumberPattern)));
                            break;

                        case 3:
                            new CamelTemplate(scanner.next().replaceAll("\\s+",""), Double.parseDouble(scanner.next(anyNumberPattern)), Double.parseDouble(scanner.next(anyNumberPattern)), Double.parseDouble(scanner.next(anyNumberPattern)), Double.parseDouble(scanner.next(anyNumberPattern)), Double.parseDouble(scanner.next(anyNumberPattern)), Integer.parseInt(scanner.next(anyNumberPattern)), Double.parseDouble(scanner.next(anyNumberPattern)));
                            break;

                        case 4:
                            new Request(Double.parseDouble(scanner.next(anyNumberPattern)), Integer.parseInt(scanner.next(anyNumberPattern)), Integer.parseInt(scanner.next(anyNumberPattern)), Double.parseDouble(scanner.next(anyNumberPattern)));
                            break;
                    }
                }

                /* Incrementing property type and setting to read for new property count */
                propertyType++;
            }
        } finally {
            /* Closing scanner */
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}
