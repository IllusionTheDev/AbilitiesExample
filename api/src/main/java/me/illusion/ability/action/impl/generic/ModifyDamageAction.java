package me.illusion.ability.action.impl.generic;

import me.illusion.ability.action.AbilityAction;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.action.value.AbilityActionValue;
import me.illusion.ability.modifier.ValueModifier;
import me.illusion.ability.trigger.impl.entity.AbilityDamageContext;

public class ModifyDamageAction implements AbilityAction<AbilityDamageContext> {

    private final AbilityActionValue<AbilityDamageContext, ValueModifier<Double>> valueModifier;

    private ModifyDamageAction(AbilityActionValue<AbilityDamageContext, ValueModifier<Double>> valueModifier) {
        this.valueModifier = valueModifier;
    }

    public static ModifyDamageAction of(AbilityActionValue<AbilityDamageContext, ValueModifier<Double>> valueModifier) {
        return new ModifyDamageAction(valueModifier);
    }

    public static ModifyDamageAction of(ValueModifier<Double> modifier) {
        return ModifyDamageAction.of(AbilityActionValue.of(modifier));
    }

    @Override
    public void handle(AbilityActionContext<AbilityDamageContext> actionContext) {
        actionContext.getContext().addDamageModifier(valueModifier.getValue(actionContext));
    }
}
