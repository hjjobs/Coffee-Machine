package dispenser.enums;

import dispenser.drinks.*;

import java.util.HashMap;
import java.util.Map;

public enum Beverages {
    HOT_TEA("hot_tea", new Tea()),
    HOT_COFFEE("hot_coffee", new Coffee()),
    BLACK_TEA("black_tea", new BlackTea()),
    GREEN_TEA("green_tea", new GreenTea()),
    GINGER_TEA("ginger_tea", new GingerTea()),
    HOT_MILK("hot_milk", new HotMilk()),
    HOT_WATER("hot_water", new HotWater()),
    ELAICHI_TEA("elaichi_tea", new ElaichiTea());

    public String value;
    public Beverage beverage;

    private static final Map<String, Beverage> beverageMap = new HashMap<>();

    static {
        for (Beverages beverages : values()) {
            beverageMap.put(beverages.value, beverages.beverage);
        }
    }

    // FlyWeight Pattern
    public static Beverage getByValue(String value) {
        if (beverageMap.get(value) != null)
            return beverageMap.get(value);
        else {
            beverageMap.put(value, Beverages.valueOf(value).beverage);
            return Beverages.valueOf(value).beverage;
        }
    }

    public String getValue() {
        return value;
    }

    Beverages(String value, Beverage beverage) {
        this.value = value;
        this.beverage = beverage;
    }
}
