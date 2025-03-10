package v.yeikovych.stone.complex;

import v.yeikovych.stone.AbstractStone;
import v.yeikovych.stone.RgbColor;

public class ColoredStone extends AbstractStone {

    private RgbColor color;

    public ColoredStone(long weightGrams, int red, int green, int blue) {
        super(weightGrams);
        this.color = new RgbColor(red, green, blue);
    }

    public RgbColor getColor() {
        return color;
    }

    public void setColor(RgbColor color) {
        if (color != null) {
            this.color = color;
        }
    }
}
