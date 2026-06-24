package me.illusion.ability.restriction.impl;

import java.util.Objects;
import java.util.function.Predicate;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.metadata.AbilityMetadataKey;
import me.illusion.ability.metadata.AbilityMetadataKeys;
import me.illusion.ability.metadata.AbilityMetadataValueHolder;
import me.illusion.ability.metadata.type.AbilityActivationState;
import me.illusion.ability.restriction.AbilityRestriction;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;

public class AbilityMetadataRestriction<T> implements AbilityRestriction<AbilityPlayerContext> {

    private final AbilityMetadataKey<T> key;
    private final Predicate<AbilityMetadataValueHolder<T>> predicate;

    private AbilityMetadataRestriction(AbilityMetadataKey<T> key, Predicate<AbilityMetadataValueHolder<T>> predicate) {
        this.key = key;
        this.predicate = predicate;
    }

    public static <T> AbilityMetadataRestriction<T> matches(AbilityMetadataKey<T> key, Predicate<T> predicate, boolean useDefaultValue) {
        return new AbilityMetadataRestriction<>(key, holder -> {
            if (holder == null) {
                if (!useDefaultValue) {
                    return false;
                }

                return predicate.test(key.getDefaultValue());
            }

            return predicate.test(holder.getValue());
        });
    }

    public static <T> AbilityMetadataRestriction<T> is(AbilityMetadataKey<T> key, T value, boolean useDefaultValue) {
        return matches(key, value::equals, useDefaultValue);
    }

    public static <T> AbilityMetadataRestriction<T> is(AbilityMetadataKey<T> key, T value) {
        return matches(key, value::equals, true);
    }

    public static <T> AbilityMetadataRestriction<T> has(AbilityMetadataKey<T> key) {
        return new AbilityMetadataRestriction<>(key, Objects::nonNull);
    }

    public static AbilityRestriction<? super AbilityPlayerContext> hasNot(AbilityMetadataKey<Boolean> key) {
        return new AbilityMetadataRestriction<>(key, Objects::isNull);
    }

    public static AbilityRestriction<? super AbilityPlayerContext> isActive() {
        return is(AbilityMetadataKeys.ACTIVATION_STATE, AbilityActivationState.ACTIVE);
    }

    public static AbilityRestriction<? super AbilityPlayerContext> isInactive() {
        return is(AbilityMetadataKeys.ACTIVATION_STATE, AbilityActivationState.INACTIVE);
    }

    @Override
    public boolean canTrigger(AbilityActionContext<AbilityPlayerContext> actionContext) {
        return predicate.test(actionContext.getAbility().getMetadata(actionContext.getContext().getPlayer()).getHolder(key));
    }

    public AbilityMetadataRestriction<T> negate() {
        return new AbilityMetadataRestriction<>(key, value -> !predicate.test(value));
    }
}
