package me.illusion.ability.action.impl.metadata;


import me.illusion.ability.Ability;
import me.illusion.ability.action.AbilityAction;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.metadata.AbilityMetadataKey;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;

public class RemoveMetadataAction implements AbilityAction<AbilityPlayerContext> {

    private final AbilityMetadataKey<?> key;

    private RemoveMetadataAction(AbilityMetadataKey<?> key) {
        this.key = key;
    }

    public static RemoveMetadataAction create(AbilityMetadataKey<?> key) {
        return new RemoveMetadataAction(key);
    }

    @Override
    public void handle(AbilityActionContext<AbilityPlayerContext> actionContext) {
        AbilityPlayerContext context = actionContext.getContext();
        Ability ability = actionContext.getAbility();

        ability.getMetadata(context.getPlayer()).remove(key);
    }
}
