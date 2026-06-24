package me.illusion.ability.restriction;

import java.util.function.Predicate;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.trigger.AbilityTriggerContext;

public interface AbilityRestriction<T extends AbilityTriggerContext> {

    boolean canTrigger(AbilityActionContext<T> context);

    default <V extends T> boolean can(AbilityActionContext<V> context) {
        return canTrigger((AbilityActionContext<T>) context); // Dirty hack, allows us to call this method with super types without making implementations a generic mess
    }

    static <T extends AbilityTriggerContext> AbilityRestriction<T> of(Predicate<T> predicate) {
        return (context) -> predicate.test(context.getContext());
    }

    default AbilityRestriction<T> negate() {
        return context -> !AbilityRestriction.this.canTrigger(context);
    }

}
