package org.core;

public abstract class Utilities {
    public static <E extends Enum<E>> boolean isInEnum(String value, Class<E> enumClass) {
        for (E e : enumClass.getEnumConstants()) {
            if(value.contains(e.name())) { return true; }
        }
        return false;
    }
}
