package me.illusion.ability.metadata;

public class AbilityMetadataKey<T> {

    private final String name;
    private T defaultValue;

    private AbilityMetadataKey(String name, T defaultValue) {
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public static <T> AbilityMetadataKey<T> create(String name) {
        return new AbilityMetadataKey<>(name, null);
    }

    public static <T> AbilityMetadataKey<T> create(String name, T defaultValue) {
        return new AbilityMetadataKey<>(name, defaultValue);
    }

    public T getDefaultValue() {
        return defaultValue;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
