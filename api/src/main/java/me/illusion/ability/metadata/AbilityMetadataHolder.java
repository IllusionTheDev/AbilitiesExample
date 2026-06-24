package me.illusion.ability.metadata;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AbilityMetadataHolder {

    private final Map<AbilityMetadataKey<?>, AbilityMetadataValueHolder<?>> metadata = new ConcurrentHashMap<>();

    public <T> void set(AbilityMetadataKey<T> key, T value) {
        metadata.put(key, AbilityMetadataValueHolder.of(key, value));
    }

    public <T> void set(AbilityMetadataKey<T> key, T value, long expiryTimestamp) {
        metadata.put(key, AbilityMetadataValueHolder.of(key, value, expiryTimestamp));
    }

    public <T> void setHolder(AbilityMetadataValueHolder<T> holder) {
        metadata.put(holder.getKey(), holder);
    }

    public <T> void setTemporary(AbilityMetadataKey<T> key, T value, Duration expiry) {
        metadata.put(key, AbilityMetadataValueHolder.of(key, value, expiry));
    }

    public <T> T get(AbilityMetadataKey<T> key) {
        removeExpired();

        AbilityMetadataValueHolder<?> holder = metadata.get(key);

        if (holder == null) {
            return key.getDefaultValue();
        }

        return (T) holder.getValue();
    }

    public <T> T getOrDefault(AbilityMetadataKey<T> key, T defaultValue) {
        T value = get(key);

        if (value == null) {
            return defaultValue;
        }

        return value;
    }

    public <T> void remove(AbilityMetadataKey<T> key) {
        metadata.remove(key);
    }

    public <T> void clear() {
        metadata.clear();
    }

    public <T> AbilityMetadataValueHolder<T> getHolder(AbilityMetadataKey<T> key) {
        return (AbilityMetadataValueHolder<T>) metadata.get(key);
    }

    public List<AbilityMetadataValueHolder<?>> getHolders() {
        return new ArrayList<>(metadata.values());
    }

    private void removeExpired() {
        metadata.entrySet().removeIf(entry -> entry.getValue().isExpired());
    }

    @Override
    public String toString() {
        return "AbilityMetadata{" +
            "metadata=" + metadata +
            '}';
    }
}
