package me.illusion.ability.modifier;

@FunctionalInterface
public interface ValueModifier<V> {

    V modify(V value);

    static ValueModifier<Double> addPercentage(float percentage) {
        return (value) -> value + (value * percentage / 100);
    }

    static ValueModifier<Double> multiplyPercentage(float percentage) {
        return (value) -> value * (percentage / 100);
    }

    static ValueModifier<Double> multiply(double multiplier) {
        return (value) -> value * multiplier;
    }

}
