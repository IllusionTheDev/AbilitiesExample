package me.illusion.ability.trigger.impl.player;

import java.util.ArrayList;
import java.util.List;
import me.illusion.ability.modifier.ValueModifier;
import me.illusion.ability.modifier.ValueModifiers;
import me.illusion.ability.player.AbilityPlayer;
import me.illusion.ability.trigger.impl.entity.AbilityDamageContext;
import me.illusion.ability.trigger.impl.entity.AbilityEntityContext;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class AbilityPlayerDamageEntityContext implements AbilityPlayerContext, AbilityEntityContext, AbilityDamageContext {

    private final AbilityPlayer player;
    private final Entity target;
    private final DamageCause cause;
    private final double damage;

    private final List<ValueModifier<Double>> modifiers;

    public AbilityPlayerDamageEntityContext(AbilityPlayer player, Entity target, DamageCause cause, double damage) {
        this.player = player;
        this.target = target;
        this.cause = cause;
        this.damage = damage;
        this.modifiers = new ArrayList<>();
    }

    @Override
    public Entity getTarget() {
        return target;
    }

    @Override
    public AbilityPlayer getPlayer() {
        return player;
    }

    @Override
    public double getDamage() {
        return damage;
    }

    @Override
    public void addDamageModifier(ValueModifier<Double> modifier) {
        modifiers.add(modifier);
    }

    @Override
    public ValueModifiers<Double> getDamageModifiers() {
        return ValueModifiers.of(modifiers);
    }

    @Override
    public DamageCause getCause() {
        return cause;
    }
}
