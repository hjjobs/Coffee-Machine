package dispenser.drinks;

import dispenser.enums.Beverages;
import dispenser.enums.Ingredients;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import static dispenser.utils.Constants.IS_PREPARED;

public class ElaichiTea implements Beverage {

    public void dispenseDrink(Map<Ingredients, Integer> currentItemsQuantity, Map<Ingredients, Integer> reqiuredItemsQuantity) {
        if (isMakeable(Beverages.ELAICHI_TEA, currentItemsQuantity, reqiuredItemsQuantity)) {
            System.out.println(Beverages.ELAICHI_TEA.getValue() + IS_PREPARED);
            try {
                Files.write(Paths.get("../testproject/src/main/resources/output.txt"), (Beverages.ELAICHI_TEA.getValue() + IS_PREPARED + "\n").getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.out.println("Error Reading Output File");
            }
        }
    }
}
