package v.yeikovych.stone;

import static v.yeikovych.util.ValidationUtils.*;

public abstract class AbstractStone {

    private static String commonOrigin = "Earth";
    private long weightGrams;

    public AbstractStone(long weightGrams) {
        setWeightGrams(weightGrams);
    }

    public long getWeightGrams() {
        return weightGrams;
    }

    public void setWeightGrams(long weightGrams) {
        throwIfFalse(() -> isPositiveOrZero(weightGrams));
        this.weightGrams = weightGrams;
    }

    public static String getCommonOrigin() {
        return commonOrigin;
    }

    public static void setCommonOrigin(String commonOrigin) {
        throwIfFalse(() -> isValidString(commonOrigin));
        AbstractStone.commonOrigin = commonOrigin;
    }

    public void printStoneInfo() {
        System.out.printf("CommonOrigin: %s, Weight: %s\n", commonOrigin, weightGrams);
    }
}
