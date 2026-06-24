package me.illusion.ability.trigger.impl.generic;

import me.illusion.ability.trigger.AbilityTriggerContext;
import org.bukkit.inventory.ItemStack;

public interface AbilityItemContext extends AbilityTriggerContext {

    ItemStack getItem();
}
