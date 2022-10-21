import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Factory class is used to create custom instances of objects. This class is used for creating instances by ratio.
 * @author Radek Nolč
 */
public class Factory {
    
    /**
     * Creating new instance of camel from randomly selected template with ratio
     * @return new camel created from selected template with generated attributes (distance, speed).
     * @throws Exception if the ratio sum of templates is over / under 1.0 
     */
    public static Camel camel() throws Exception {
        List<CamelTemplate> templates = CamelTemplate.getCamelTemplates();
        CamelTemplate[] select = new CamelTemplate[100];
        int nextIdx = 0;
        
        for (CamelTemplate template : templates) {
            int leftIterations = (int)(template.getRatio() * 100);
            
            for (int i = 0; i < leftIterations; i++) {
                if (nextIdx >= 100)
                    throw new Exception("Camel ratio sum is over value 1.0");

                select[nextIdx] = template;
                nextIdx++;
            }
        }

        if (nextIdx < 100) {
            throw new Exception("Camel ratio sum is under value 1.0");
        }

        Random random = new Random();
        CamelTemplate template = select[random.nextInt(select.length)];
        return new Camel(template.getName(), template.getSpeedMin(), template.getSpeedMax(), template.getDistanceMin(), template.getDistanceMax(), template.getDrinkTime(), template.getMaxLoad(), template.getRatio());
    }

    public static Camel camel(Storage homeStorage) throws Exception {
        Camel camel = camel();
        camel.setLocation(homeStorage);
        camel.setHomeStorage(homeStorage);

        return camel;
    }
}
