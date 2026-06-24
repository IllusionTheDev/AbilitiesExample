package me.illusion.ability.builder.bind.impl;

import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.action.AbilityActions;
import me.illusion.ability.builder.bind.BindableAbilityAction;
import me.illusion.ability.builder.bind.BoundAbilityAction;
import me.illusion.ability.trigger.AbilityTriggerContext;
import me.illusion.ability.trigger.AbilityTriggerContextType;

public class BoundAbilityActionImpl<T extends AbilityTriggerContext> implements BoundAbilityAction<T> {

    private final BindableAbilityActionImpl<T> action;
    private final AbilityActions<? super T> handlers;

    BoundAbilityActionImpl(BindableAbilityActionImpl<T> action, AbilityActions<? super T> handlers) {
        this.action = action;
        this.handlers = handlers;
    }

    @Override
    public String getId() {
        return action.getActionName();
    }

    @Override
    public AbilityTriggerContextType<T> getType() {
        return action.getType();
    }

    @Override
    public BindableAbilityAction<T> getAction() {
        return action;
    }

    @Override
    public int getFiringOrder() {
        return action.getFiringOrder();
    }

    @Override
    public void handle(AbilityActionContext<T> context) {
        if (!action.getRestrictions().canTrigger(context)) {
            return;
        }

        handlers.call(context);
    }
}
