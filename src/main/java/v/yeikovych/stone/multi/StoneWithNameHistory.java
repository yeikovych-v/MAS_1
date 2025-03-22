package v.yeikovych.stone.multi;

import v.yeikovych.stone.AbstractStone;

import java.util.Collections;
import java.util.List;

import static v.yeikovych.util.ValidationUtils.*;

public class StoneWithNameHistory extends AbstractStone {

    private List<String> names;

    public StoneWithNameHistory(long weightGrams, List<String> names) {
        super(weightGrams);
        setNames(names);
    }

    public List<String> getNames() {
        return Collections.unmodifiableList(names);
    }

    private void setNames(List<String> names) {
        throwIfFalse(() -> isMultiAttribute(names));
        this.names = names;
    }

    public void removeName(String name) {
        if (names.size() > 1) {
            names.remove(name);
        }
    }

    public void addName(String name) {
        throwIfFalse(() -> isValidName(name));
        names.add(name);
    }
}
