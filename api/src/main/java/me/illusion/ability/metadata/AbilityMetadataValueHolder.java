package me.illusion.ability.metadata;

import java.time.Duration;

public class AbilityMetadataValueHolder<T> {

    private final AbilityMetadataKey<T> key;
    private final long timestamp;
    private final long expiryTimestamp;
    private final T value;

    private AbilityMetadataValueHolder(AbilityMetadataKey<T> key, T value, long timestamp, long expiryTimestamp) {
        this.timestamp = timestamp;
        this.expiryTimestamp = expiryTimestamp;
        this.key = key;
        this.value = value;
    }

    public static <T> AbilityMetadataValueHolder<T> of(AbilityMetadataKey<T> key, T value) {
        return of(key, value, 0);
    }

    public static <T> AbilityMetadataValueHolder<T> of(AbilityMetadataKey<T> key, T value, long expiryTimestamp) {
        return new AbilityMetadataValueHolder<>(key, value, System.currentTimeMillis(), expiryTimestamp);
    }

    public static <T> AbilityMetadataValueHolder<T> of(AbilityMetadataKey<T> key, T value, Duration expiry) {
        long now = System.currentTimeMillis();
        long expiryTimestamp = expiry.toMillis() + now;
        return of(key, value, expiryTimestamp);
    }

    public AbilityMetadataValueHolder<T> updateSilently(T value) {
        return new AbilityMetadataValueHolder<>(key, value, timestamp, expiryTimestamp);
    }

    public AbilityMetadataKey<T> getKey() {
        return key;
    }

    public T getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public boolean isExpired() {
        return expiryTimestamp != 0 && expiryTimestamp < System.currentTimeMillis();
    }

    public long getExpiryTimestamp() {
        return expiryTimestamp;
    }

    @Override
    public String toString() {
        return value == null ? "null" : value.toString();
    }
}
