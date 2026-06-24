package me.illusion.ability.action;

import me.illusion.ability.player.AbilityPlayer;
import me.illusion.ability.trigger.AbilityTriggerContext;
import me.illusion.ability.trigger.AbilityTriggerContextType;
import me.illusion.ability.trigger.AbilityTriggerContextTypes;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;

public interface AbilityCastingTarget {

    default void cast(AbilityPlayer player) {
        trigger(AbilityTriggerContextTypes.ABILITY_ACTIVATE, AbilityPlayerContext.of(player));
    }

    default void manualTrigger(AbilityPlayer player) {
        trigger(AbilityTriggerContextTypes.MANUAL_ACTIVATION, AbilityPlayerContext.of(player));
    }

    // Change this to AbilityPlayerContext if you're introducing player levels in the context and need a player to pull from
    <T extends AbilityTriggerContext> void trigger(AbilityTriggerContextType<T> type, T context);

}
