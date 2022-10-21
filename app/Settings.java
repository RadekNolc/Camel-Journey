public class Settings {
    
    private static boolean isTestMode = false;

    public static void setTestMode(boolean state) {
        isTestMode = state;
    }

    public static boolean isTestMode() {
        return isTestMode;
    }
}
