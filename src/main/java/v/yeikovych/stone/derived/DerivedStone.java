package v.yeikovych.stone.derived;

import v.yeikovych.stone.AbstractStone;

import static v.yeikovych.util.ValidationUtils.*;

public class DerivedStone extends AbstractStone {

    private double price;

    public DerivedStone(long weightGrams, double price) {
        super(weightGrams);
        throwIfAnyFalse(() -> isPositiveOrZero(price));

        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (isPositiveOrZero(price)) {
            this.price = price;
        }
    }

    public double getPricePerGram() {
        return price / getWeightGrams();
    }
}
