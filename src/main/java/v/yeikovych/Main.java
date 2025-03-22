package v.yeikovych;

import v.yeikovych.extent.Bird;
import v.yeikovych.stone.optional.PricedStone;
import v.yeikovych.util.SerializationUtil;

public class Main {
    public static void main(String[] args) {
        SerializationUtil.readExtent();
        System.out.println("Hello MAS!");
        System.out.println("Hello Birds: ");
        System.out.println(Bird.getExtent());

        var stone = new PricedStone(15,10, "   ss");
        var price = stone.getPrice();
        System.out.println(price);
        System.out.println(stone.getName());

        var bird1 = new Bird("old Bird", 23);
        var bird2 = new Bird("young Bird", 7);

        System.out.println(Bird.getOldBirds());
    }
}