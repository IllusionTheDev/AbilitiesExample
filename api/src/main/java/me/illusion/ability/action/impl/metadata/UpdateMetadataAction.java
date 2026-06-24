package me.illusion.ability.action.impl.metadata;

import me.illusion.ability.Ability;
import me.illusion.ability.action.AbilityAction;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.metadata.AbilityMetadataKey;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;

public class UpdateMetadataAction<T> implements AbilityAction<AbilityPlayerContext> {

    private final AbilityMetadataKey<T> key;
    private final T value;

    private UpdateMetadataAction(AbilityMetadataKey<T> key, T value) {
        this.key = key;
        this.value = value;
    }

    public static <T> UpdateMetadataAction<T> create(AbilityMetadataKey<T> key, T value) {
        return new UpdateMetadataAction<>(key, value);
    }

    @Override
    public void handle(AbilityActionContext<AbilityPlayerContext> actionContext) {
        AbilityPlayerContext context = actionContext.getContext();
        Ability ability = actionContext.getAbility();

        ability.getMetadata(context.getPlayer()).set(key, value);
    }
}
