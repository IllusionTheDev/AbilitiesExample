package me.illusion.ability.modifier;

import java.util.ArrayList;
import java.util.List;

public class ValueModifiers<T> {

    private final List<ValueModifier<T>> modifiers = new ArrayList<>();

    private ValueModifiers(List<ValueModifier<T>> modifiers) {
        this.modifiers.addAll(modifiers);
    }

    public static <T> ValueModifiers<T> of(List<ValueModifier<T>> modifiers) {
        return new ValueModifiers<>(modifiers);
    }

    public static <T> ValueModifiers<T> of(ValueModifier<T>... modifiers) {
        return new ValueModifiers<>(List.of(modifiers));
    }

    public static <T> ValueModifiers<T> of() {
        return new ValueModifiers<>(List.of());
    }

    public void addModifier(ValueModifier<T> modifier) {
        modifiers.add(modifier);
    }

    public T getValue(T value) {
        for (ValueModifier<T> modifier : modifiers) {
            value = modifier.modify(value);
        }
        return value;
    }

    public List<ValueModifier<T>> getModifiers() {
        return modifiers;
    }

    public boolean isEmpty() {
        return modifiers.isEmpty();
    }


}
