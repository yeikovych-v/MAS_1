package v.yeikovych.stone.optional;

import v.yeikovych.stone.AbstractStone;

import static v.yeikovych.util.ValidationUtils.*;

public class PricedStone extends AbstractStone {

    private double price;
    private String name;

    public PricedStone(long weightGrams, double price) {
        super(weightGrams);
        setPrice(price);
    }

    public PricedStone(long weightGrams, double price, String name) {
        super(weightGrams);
        setPrice(price);
        setName(name);
    }

    public String getName() {
        return name;
    }

    public String getNameOrPseudonym() {
        return name != null ? name : "AnonymousStone";
    }

    public void setName(String name) {
        throwIfFalse(() -> isValidNullableName(name));
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        throwIfFalse(() -> isPositiveOrZero(price));
        this.price = price;
    }

    @Override
    public void printStoneInfo() {
        System.out.printf("Name: %s, Price: %s\n", getNameOrPseudonym(), price);
    }
}
