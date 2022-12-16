import java.util.Random;

/**
 * Calculator class is custom calculation class that is compatible with the program.
 * Includes:
 * <ul>
 * <li>normal distribution calculation
 * <li>continuous distribution calculation
 * </ul>
 * <br>
 * @author Radek Nolč
 */
abstract class Calculator {
    
    /** Instance of Random class */
    private static Random random = new Random();
    
    /**
     * Function to calculate normal distributon with mean (<b>(min + max) / 2.0</b>) and standard deviation (<b>(max - min) / 4.0</b>)
     * @param min minimum value to calculate from
     * @param max maximum value to calculate to
     * @return the value calculated by normal distribution as double, higher or equals to min, lower or equals to max
     * @throws Exception if there was not possible to generate the normal distribution
     */
    public static double normalDistribution(double min, double max) throws Exception {
        Double result = null;
        int tries = 1;
        int maxTries = 50; /* Defining maximum tries to not cycle the program */

        while ((result == null) || (result < min || result > max)) {
            if (tries >= maxTries) throw new Exception("Error while computing normal distribution.");
                
            result = random.nextGaussian((min + max) / 2.0, (max - min) / 4.0);
            tries++;
        }

        return result.doubleValue();
    }

    /**
     * Function to calculate continuous distribution, every value has same probability to generate.
     * @param min minimum value to calculate from
     * @param max maximum value to calculate to
     * @return the value calculated by continuous distribution as double, higher or equals to min, lower or equals to max
     */
    public static double continuousDistribution(double min, double max) {
        if (min == max) return min;
        return random.nextDouble(min, max);
    }

    /**
     * Function to calculate direct distance between two points (locations).
     * @param location1 first location to calculate distance from
     * @param location2 second location to calculate distance from
     * @return distance between two points (locations)
     */
    public static double directDistance(Location location1, Location location2) {
        return Math.sqrt(Math.abs((location1.getX() - location2.getX()) * (location1.getX() - location2.getX())) + Math.abs((location1.getY() - location2.getY()) * (location1.getY() - location2.getY())));
    }

    /**
     * Function to calculate time to travel
     * @param distance how far to travel
     * @param speed at which speed to travel
     * @return needed time to travel the distance with constant speed
     */
    public static double timeToTravel(double distance, double speed) {
        return distance / speed;
    }
}
