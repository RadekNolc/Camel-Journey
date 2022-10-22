/**
 * Settings class is used to globally configure how program acts
 * @author Radek Nolč
 */
abstract class Settings {
    
    /** if is test mode enabled */
    private static boolean isTestMode = false;

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
}
