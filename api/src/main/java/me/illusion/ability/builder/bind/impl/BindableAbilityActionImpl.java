package me.illusion.ability.builder.bind.impl;

import me.illusion.ability.action.AbilityActions;
import me.illusion.ability.builder.bind.BindableAbilityAction;
import me.illusion.ability.builder.bind.BoundAbilityAction;
import me.illusion.ability.restriction.AbilityRestriction;
import me.illusion.ability.restriction.AbilityRestrictions;
import me.illusion.ability.trigger.AbilityTriggerContext;
import me.illusion.ability.trigger.AbilityTriggerContextType;

public class BindableAbilityActionImpl<T extends AbilityTriggerContext> implements BindableAbilityAction<T> {

    private final String actionName;
    private final AbilityTriggerContextType<T> type;
    private final AbilityRestrictions<T> restrictions;
    private int firingOrder = 0;

    private BindableAbilityActionImpl(String actionName, AbilityTriggerContextType<T> type) {
        this.actionName = actionName;
        this.type = type;
        this.restrictions = new AbilityRestrictions<>();
    }

    public static BindableAbilityAction.Base createBase(String actionName) {
        return new Base(actionName);
    }

    @Override
    public BindableAbilityAction<T> withRestriction(AbilityRestriction<? super T> restriction) {
        restrictions.add(restriction);
        return this;
    }

    @Override
    public BindableAbilityAction<T> firingOrder(int priority) {
        firingOrder = priority;
        return this;
    }

    @Override
    public BoundAbilityAction<T> to(AbilityActions<? super T> delegate) {
        return new BoundAbilityActionImpl<>(this, delegate);
    }

    public AbilityRestrictions<T> getRestrictions() {
        return restrictions;
    }

    // Dependency inversion go brr

    public int getFiringOrder() {
        return firingOrder;
    }

    public String getActionName() {
        return actionName;
    }

    public AbilityTriggerContextType<T> getType() {
        return type;
    }

    private static class Base implements BindableAbilityAction.Base {

        private final String actionName;

        private Base(String actionName) {
            this.actionName = actionName;
        }

        @Override
        public <T extends AbilityTriggerContext> BindableAbilityAction<T> withType(AbilityTriggerContextType<T> type) {
            return new BindableAbilityActionImpl<>(actionName, type);
        }
    }
}
