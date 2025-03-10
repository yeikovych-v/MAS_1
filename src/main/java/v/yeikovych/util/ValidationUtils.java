package v.yeikovych.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;
import java.util.function.BooleanSupplier;

public class ValidationUtils {

    public static boolean isValidString(String string) {
        return string != null && !string.isBlank();
    }

    public static boolean isValidAge(int age) {
        return age >= 0 && age <= 110;
    }

    public static boolean isValidName(String name) {
        return isValidString(name) && name.trim().length() > 1;
    }

    public static boolean isPositiveOrZero(double number) {
        return number >= 0;
    }

    public static boolean isPositiveOrZero(int number) {
        return number >= 0;
    }

    public static boolean isPositiveOrZero(long number) {
        return number >= 0;
    }

    public static boolean isMultiAttribute(List<?> multiAttribute) {
        return multiAttribute != null && !multiAttribute.isEmpty() && !anyNull(multiAttribute);
    }

    public static boolean anyNull(List<?> list) {
        return list.stream().anyMatch(Objects::isNull);
    }

    public static boolean isValidNullableName(String name) {
        if (name == null) return true;
        return isValidName(name);
    }

    public static void throwIfNull(Object object) throws ValidationException {
        if (Objects.isNull(object)) {
            throw new ValidationException("Validation failed: null is not allowed");
        }
    }

    public static boolean allTrue(BooleanSupplier... conditions) {
        for (BooleanSupplier condition : conditions) {
            if (!condition.getAsBoolean()) {
                return false;
            }
        }
        return true;
    }

    public static void throwIfAnyFalse(BooleanSupplier... conditions) throws ValidationException {
        if (!allTrue(conditions)) {
            throw new ValidationException("Validation failed");
        }
    }
}
