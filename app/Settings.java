/**
 * Settings class is used to globally configure how program acts
 * @author Radek Nolč
 */
abstract class Settings {
    
    /** if is test mode enabled */
    private static boolean isTestMode = false;
    /** File of input */
    private static String fileName = "";

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
        fileName = file;
    }

    /**
     * Function to get input file
     * @return file name of input file
     */
    public static String getInputFile() {
        return fileName;
    }
}
