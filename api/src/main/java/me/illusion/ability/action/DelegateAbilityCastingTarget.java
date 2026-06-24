package me.illusion.ability.action;

import me.illusion.ability.trigger.AbilityTriggerContext;
import me.illusion.ability.trigger.AbilityTriggerContextType;

/**
 * Represents an ability action registry that delegates to another registry.
 * Helps deduplicate code and improve readability.
 */
public interface DelegateAbilityCastingTarget extends AbilityCastingTarget {

    AbilityCastingTarget getActionRegistry();

    @Override
    default <T extends AbilityTriggerContext> void trigger(AbilityTriggerContextType<T> type, T context) {
        getActionRegistry().trigger(type, context);
    }
}
