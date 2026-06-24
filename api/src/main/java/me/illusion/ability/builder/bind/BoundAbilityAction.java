package me.illusion.ability.builder.bind;

import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.trigger.AbilityTriggerContext;
import me.illusion.ability.trigger.AbilityTriggerContextType;

public interface BoundAbilityAction<T extends AbilityTriggerContext> {

    BindableAbilityAction<T> getAction();
    void handle(AbilityActionContext<T> context);

    String getId();
    AbilityTriggerContextType<T> getType();
    int getFiringOrder();

}
