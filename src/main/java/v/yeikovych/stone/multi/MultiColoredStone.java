package v.yeikovych.stone.multi;

import v.yeikovych.stone.AbstractStone;
import v.yeikovych.stone.RgbColor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static v.yeikovych.stone.RgbColor.isValidRgb;
import static v.yeikovych.util.ValidationUtils.*;

public class MultiColoredStone extends AbstractStone {

    private List<RgbColor> colors;

    public MultiColoredStone(long weightGrams, List<RgbColor> colors) {
        super(weightGrams);
        throwIfAnyFalse(() -> isMultiAttribute(colors));

        this.colors = colors;
    }

    public List<RgbColor> getColors() {
        return Collections.unmodifiableList(colors);
    }

    public void setColors(List<RgbColor> colors) {
        if (isMultiAttribute(colors)) {
            this.colors = colors;
        }
    }

    public void removeColor(RgbColor color) {
        if (colors.size() > 1) {
            colors.remove(color);
        }
    }

    public void addColor(RgbColor color) {
        if (isValidRgb(color)) {
            colors.add(color);
        }
    }
}
