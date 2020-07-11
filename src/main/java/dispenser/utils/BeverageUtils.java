package dispenser.utils;

import dispenser.enums.Ingredients;
import dispenser.exceptions.InsufficientItemException;
import dispenser.exceptions.MissingItemException;

import java.util.HashMap;
import java.util.Map;

import static dispenser.utils.Constants.ITEM_INSUFFICIENT_KEY;
import static dispenser.utils.Constants.ITEM_MISSING_KEY;

public class BeverageUtils {

    public synchronized static Map<String, String> checkRecipeAndMakeBeverage(Map<Ingredients, Integer> totalItemsQuantity, Map<Ingredients, Integer> requiredItemsQuantity)
            throws MissingItemException, InsufficientItemException {
        Map<String, String> missingAndInsufficientItems = findMissingAndInsufficientItems(totalItemsQuantity, requiredItemsQuantity);
        if (missingAndInsufficientItems.get(ITEM_MISSING_KEY) != null) {
            throw new MissingItemException(missingAndInsufficientItems.get(ITEM_MISSING_KEY));
        }

        if (missingAndInsufficientItems.get(ITEM_INSUFFICIENT_KEY) != null) {
            throw new InsufficientItemException(missingAndInsufficientItems.get(ITEM_INSUFFICIENT_KEY));
        }

        if (missingAndInsufficientItems.get(ITEM_MISSING_KEY) == null && missingAndInsufficientItems.get(ITEM_INSUFFICIENT_KEY) == null) {
            makeBeverage(totalItemsQuantity, requiredItemsQuantity);
        }
        return missingAndInsufficientItems;
    }

    private static Map<String, String> findMissingAndInsufficientItems(Map<Ingredients, Integer> totalItemsQuantity, Map<Ingredients, Integer> requiredItemsQuantity) {
        Map<String, String> unavailableItemsMap = new HashMap<>();

        for (HashMap.Entry<Ingredients, Integer> item : requiredItemsQuantity.entrySet()) {
            if (isItemMissing(totalItemsQuantity, item.getKey())) {
                unavailableItemsMap.put(ITEM_MISSING_KEY, item.getKey().getValue());
                break;
            }
        }

        if (unavailableItemsMap.get(ITEM_MISSING_KEY) == null) {
            for (HashMap.Entry<Ingredients, Integer> item : requiredItemsQuantity.entrySet()) {
                if (!checkItemsQuantity(totalItemsQuantity.get(item.getKey()), item.getValue())) {
                    unavailableItemsMap.put(ITEM_INSUFFICIENT_KEY, item.getKey().getValue());
                    break;
                }
            }
        }
        return unavailableItemsMap;
    }

    private static Boolean checkItemsQuantity(Integer totalItemsQuantity, Integer requiredItemsQuantity) {
        if (totalItemsQuantity >= requiredItemsQuantity) {
            return true;
        }
        return false;

    }

    private static Boolean isItemMissing(Map<Ingredients, Integer> totalItemsQuantity, Ingredients item) {
        if (totalItemsQuantity.get(item) == null) {
            return true;
        }
        return false;
    }

    private static void makeBeverage(Map<Ingredients, Integer> totalItemsQuantity, Map<Ingredients, Integer> requiredItemsQuantity) {
        requiredItemsQuantity.entrySet().forEach(item -> {
            totalItemsQuantity.put(item.getKey(), totalItemsQuantity.get(item.getKey()) - item.getValue());
        });
    }
}
