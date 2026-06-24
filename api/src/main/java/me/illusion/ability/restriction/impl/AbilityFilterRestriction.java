package me.illusion.ability.restriction.impl;

import java.util.function.Predicate;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.restriction.AbilityRestriction;
import me.illusion.ability.trigger.AbilityTriggerContext;

public class AbilityFilterRestriction<T extends AbilityTriggerContext> implements AbilityRestriction<T> {

    private final Predicate<T> predicate;

    private AbilityFilterRestriction(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    public static <T extends AbilityTriggerContext> AbilityFilterRestriction<T> of(Predicate<T> predicate) {
        return new AbilityFilterRestriction<>(predicate);
    }

    @Override
    public boolean canTrigger(AbilityActionContext<T> actionContext) {
        return predicate.test(actionContext.getContext());
    }
}
