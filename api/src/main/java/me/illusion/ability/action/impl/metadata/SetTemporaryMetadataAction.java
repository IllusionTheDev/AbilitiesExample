package me.illusion.ability.action.impl.metadata;

import java.time.Duration;
import me.illusion.ability.Ability;
import me.illusion.ability.action.AbilityAction;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.action.value.AbilityActionValue;
import me.illusion.ability.metadata.AbilityMetadataKey;
import me.illusion.ability.metadata.AbilityMetadataKeys;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;

public class SetTemporaryMetadataAction<T> implements AbilityAction<AbilityPlayerContext> {

    private final AbilityMetadataKey<T> key;
    private final AbilityActionValue<AbilityPlayerContext, T> value;

    private final AbilityActionValue<AbilityPlayerContext, Duration> time;

    private SetTemporaryMetadataAction(AbilityMetadataKey<T> key, AbilityActionValue<AbilityPlayerContext, T> value, AbilityActionValue<AbilityPlayerContext, Duration> duration) {
        this.key = key;
        this.value = value;
        this.time = duration;
    }

    public static <T> SetTemporaryMetadataAction<T> create(AbilityMetadataKey<T> key, T value, Duration duration) {
        return new SetTemporaryMetadataAction<>(key, AbilityActionValue.of(value), AbilityActionValue.of(duration));
    }

    public static <T> SetTemporaryMetadataAction<T> create(AbilityMetadataKey<T> key, AbilityActionValue<AbilityPlayerContext, T> value, AbilityActionValue<AbilityPlayerContext, Duration> duration) {
        return new SetTemporaryMetadataAction<>(key, value, duration);
    }

    public static SetTemporaryMetadataAction<Long> createCooldown(Duration duration) {
        return create(AbilityMetadataKeys.COOLDOWN, (ignored) -> System.currentTimeMillis() + duration.toMillis(), AbilityActionValue.of(duration));
    }

    @Override
    public void handle(AbilityActionContext<AbilityPlayerContext> actionContext) {
        AbilityPlayerContext context = actionContext.getContext();
        Ability ability = actionContext.getAbility();

        ability.getMetadata(context.getPlayer()).setTemporary(key, value.getValue(actionContext), time.getValue(actionContext));
    }
}
