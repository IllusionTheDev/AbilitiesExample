package me.illusion.ability.action;

import me.illusion.ability.Ability;
import me.illusion.ability.builder.bind.BoundAbilityAction;
import me.illusion.ability.action.impl.BakedAbilityActionRegistry;
import me.illusion.ability.trigger.AbilityTriggerContext;
import me.illusion.ability.trigger.AbilityTriggerContextType;

public interface AbilityActionRegistry extends AbilityCastingTarget {

    static AbilityActionRegistry create(Ability parent) {
        return BakedAbilityActionRegistry.create(parent);
    }

    void register(BoundAbilityAction<?> action);

    <T extends AbilityTriggerContext> void triggerAction(String actionId, AbilityTriggerContextType<T> type, T context);

    @Override
    <T extends AbilityTriggerContext> void trigger(AbilityTriggerContextType<T> type, T context);

}
