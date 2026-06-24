package me.illusion.ability.trigger.impl.entity;

import me.illusion.ability.trigger.AbilityTriggerContext;
import org.bukkit.entity.Entity;

public interface AbilityEntityContext extends AbilityTriggerContext {

    Entity getTarget();
}
