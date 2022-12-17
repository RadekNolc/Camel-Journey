package cz.radeknolc.cameljourney;
import java.util.List;
import java.util.Random;

/**
 * Factory class is used to create custom instances of objects.
 * @author Radek Nolč
 */
public class Factory {
    
    /**
     * Creating new instance of camel from randomly selected template with ratio
     * @return new camel created from selected template with generated attributes (distance, speed).
     */
    public static Camel camel() {
        List<CamelTemplate> templates = CamelTemplate.getCamelTemplates();
        int[] select = new int[100];
        int nextIdx = 0;
        
        int templateIdx = 0;
        for (CamelTemplate template : templates) {
            int leftIterations = (int)(template.getRatio() * 100);
            
            for (int i = 0; i < leftIterations; i++) {
                if (nextIdx >= 100) {
                    System.out.println("Camel ratio sum is over value 1.0");
                    System.exit(2);
                }

                select[nextIdx] = templateIdx;
                nextIdx++;
            }

            templateIdx++;
        }

        if (nextIdx < 100) {
            System.out.println("Camel ratio sum is under value 1.0");
            System.exit(2);
        }

        Random random = new Random();

        int test = select[random.nextInt(select.length)];
        CamelTemplate template = templates.get(test);
        return new Camel(template.getTemplateName(), Calculator.continuousDistribution(template.getSpeedMin(), template.getSpeedMax()), Calculator.normalDistribution(template.getDistanceMin(), template.getDistanceMax()), template.getDrinkTime(), template.getMaxLoad());
    }

    /**
     * Creating new instance of camel from randomly selected template with ratio, setting home storage of the new instance (camel)
     * @param homeStorage which storage to set as home storage for the new instance (camel)
     * @return new camel created from selected template with generated attributes (distance, speed) and location + home storage.
     */
    public static Camel camel(Storage homeStorage) {
        Camel camel = camel();
        camel.setHomeStorage(homeStorage);
        camel.setLocation(homeStorage);

        return camel;
    }
}
