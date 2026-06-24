package me.illusion.ability.trigger.impl.entity;

import me.illusion.ability.modifier.ValueModifier;
import me.illusion.ability.modifier.ValueModifiers;
import me.illusion.ability.trigger.AbilityTriggerContext;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public interface AbilityDamageContext extends AbilityTriggerContext {

    DamageCause getCause();

    double getDamage();

    void addDamageModifier(ValueModifier<Double> modifier);
    ValueModifiers<Double> getDamageModifiers();

    default double getFinalDamage() {
        return getDamageModifiers().getValue(getDamage());
    }
}
