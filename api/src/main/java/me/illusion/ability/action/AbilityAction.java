package me.illusion.ability.action;

import me.illusion.ability.trigger.AbilityTriggerContext;

public interface AbilityAction<T extends AbilityTriggerContext> {

    void handle(AbilityActionContext<T> actionContext);

    default <V extends T> void call(AbilityActionContext<V> actionContext) {
        handle((AbilityActionContext<T>) actionContext); // Dirty hack, allows us to call this method with super types without making implementations a generic mess
    }
}
