package me.illusion.ability.trigger.impl.player;

import java.util.ArrayList;
import java.util.List;
import me.illusion.ability.modifier.ValueModifier;
import me.illusion.ability.modifier.ValueModifiers;
import me.illusion.ability.player.AbilityPlayer;
import me.illusion.ability.trigger.impl.entity.AbilityDamageContext;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class AbilityPlayerDamageContext implements AbilityPlayerContext, AbilityDamageContext {

    private final AbilityPlayer player;
    private final DamageCause cause;
    private final List<ValueModifier<Double>> modifiers;
    private final double damage;
    private boolean cancelled = false;

    public AbilityPlayerDamageContext(AbilityPlayer player, DamageCause cause, double damage) {
        this.player = player;
        this.cause = cause;
        this.damage = damage;
        this.modifiers = new ArrayList<>();
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
    public AbilityPlayer getPlayer() {
        return player;
    }

    @Override
    public DamageCause getCause() {
        return cause;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
