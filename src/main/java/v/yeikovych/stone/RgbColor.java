package v.yeikovych.stone;

import java.io.Serializable;

import static v.yeikovych.util.ValidationUtils.*;

public class RgbColor implements Serializable {

    private int red;
    private int green;
    private int blue;

    public RgbColor(int red, int green, int blue) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        throwIfFalse(() -> isValidRgb(red));
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        throwIfFalse(() -> isValidRgb(green));
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        throwIfFalse(() -> isValidRgb(blue));
        this.blue = blue;
    }

    public static boolean isValidRgb(int color) {
        return color >= 0 && color <= 255;
    }

    public static boolean isValidRgb(RgbColor color) {
        return color != null && allTrue(
                () -> isValidRgb(color.getRed()),
                () -> isValidRgb(color.getGreen()),
                () -> isValidRgb(color.getBlue())
        );
    }
}
