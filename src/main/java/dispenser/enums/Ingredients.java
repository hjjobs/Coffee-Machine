package dispenser.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Ingredients {
    HOT_WATER("hot_water"),
    HOT_MILK("hot_milk"),
    TEA_LEAVES_SYRUP("tea_leaves_syrup"),
    GINGER_SYRUP("ginger_syrup"),
    SUGAR_SYRUP("suagr_syrup"),
    GREEN_MIXTURE("green_mixture"),
    ELAICHI_SYRUP("elaichi_syrup");

    private String value;

    Ingredients(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @JsonCreator
    public static Ingredients fromString(String value) {
        return Ingredients.valueOf(value.toUpperCase());
    }
}
