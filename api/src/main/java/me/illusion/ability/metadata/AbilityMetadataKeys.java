package me.illusion.ability.metadata;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import me.illusion.ability.metadata.type.AbilityActivationState;

public final class AbilityMetadataKeys {

    private static final Map<String, AbilityMetadataKey<?>> METADATA_KEYS = new ConcurrentHashMap<>();

    private static <T> AbilityMetadataKey<T> register(AbilityMetadataKey<T> key) {
        METADATA_KEYS.put(key.getName(), key);
        return key;
    }

    private static <T> AbilityMetadataKey<T> create(String name, T defaultValue) {
        return register(AbilityMetadataKey.create(name, defaultValue));
    }

    private static <T extends Enum<T>> AbilityMetadataKey<T> createEnum(String name, T defaultValue) {
        return create(name, defaultValue);
    }

    private static <T> AbilityMetadataKey<T> createTransient(String name, T defaultValue) {
        return create(name, defaultValue);
    }

    private static <T> AbilityMetadataKey<T> createTransient(String name) {
        return createTransient(name, null);
    }

    public static AbilityMetadataKey<?> get(String name) {
        return METADATA_KEYS.get(name);
    }

    public static final AbilityMetadataKey<Long> COOLDOWN = create("cooldown", 0L);
    public static final AbilityMetadataKey<AbilityActivationState> ACTIVATION_STATE = createEnum("activation-state", AbilityActivationState.INACTIVE);

    public static final AbilityMetadataKey<Integer> STACKS = createTransient("stacks", 0);
    public static final AbilityMetadataKey<Double> ACCUMULATED_DAMAGE = createTransient("accumulated-damage", 0.0);
}
