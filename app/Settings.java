/**
 * Settings class is used to globally configure how program acts
 * @author Radek Nolč
 */
abstract class Settings {
    
    /** If is test mode enabled */
    private static boolean isTestMode = false;
    /** File of input */
    private static String inputFile = "data/minimal_example.txt";
    /** Comment(s) start text */
    private static String commentStart = "🐪";
    /** Comment(s) end text */
    private static String commentEnd = "🏜";

    /**
     * Function to set state of test mode
     * @param state to set as test mode
     */
    public static void setTestMode(boolean state) {
        isTestMode = state;
    }

    /**
     * Function to get state of test mode
     * @return state of test mode
     */
    public static boolean isTestMode() {
        return isTestMode;
    }

    /**
     * Function to set input file
     * @param file name of file to get data from
     */
    public static void setInputFile(String file) {
        inputFile = file;
    }

    /**
     * Function to get input file
     * @return file name of input file
     */
    public static String getInputFile() {
        return inputFile;
    }

    /**
     * Function to set text which starts the comment
     * @param start text starting a comment
     */
    public static void setCommentStart(String start) {
        commentStart = start;
    }

    /**
     * Function to get text which the comment starts
     * @return text starting a comment
     */
    public static String getCommentStart() {
        return commentStart;
    }

    /**
     * Function to set text which ends the comment
     * @param start text ending a comment
     */
    public static void setCommentEnd(String end) {
        commentEnd = end;
    }

    /**
     * Function to get text which the comment ends
     * @return text ending a comment
     */
    public static String getCommentEnd() {
        return commentEnd;
    }
}
