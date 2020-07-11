package dispenser.drinks;

import dispenser.enums.Beverages;
import dispenser.enums.Ingredients;
import dispenser.exceptions.InsufficientItemException;
import dispenser.exceptions.MissingItemException;
import dispenser.utils.BeverageUtils;
import dispenser.utils.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;

public interface Beverage {

    default Boolean isMakeable(Beverages beverages, Map<Ingredients, Integer> currentItemsQuantity, Map<Ingredients, Integer> reqiuredItemsQuantity) {
        try {
            BeverageUtils.checkRecipeAndMakeBeverage(currentItemsQuantity, reqiuredItemsQuantity);
        } catch (MissingItemException e) {
            System.out.println(String.format(Constants.NOT_AVAILABLE_STRING, beverages.getValue(), e.getMessage()));
            try {
                Files.write(Paths.get("../testproject/src/main/resources/output.txt"), (String.format(Constants.NOT_AVAILABLE_STRING, beverages.getValue(), e.getMessage()) + "\n").getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ioe) {
                System.out.println("Error Reading Output File");
            }
            return false;
        } catch (InsufficientItemException e) {
            System.out.println(String.format(Constants.NOT_SUFFICIENT_STRING, beverages.getValue(), e.getMessage()));
            try {
                Files.write(Paths.get("../testproject/src/main/resources/output.txt"), (String.format(Constants.NOT_SUFFICIENT_STRING, beverages.getValue(), e.getMessage()) + "\n").getBytes(), StandardOpenOption.APPEND);
            } catch (IOException ioe) {
                System.out.println("Error Reading Output File");
            }
            return false;
        }
        return true;
    }

    void dispenseDrink(Map<Ingredients, Integer> currentItemsQuantity, Map<Ingredients, Integer> reqiuredItemsQuantity);
}
