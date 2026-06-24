package me.illusion.ability;

import me.illusion.ability.action.DelegateAbilityCastingTarget;
import me.illusion.ability.builder.AbilityBuilder;
import me.illusion.ability.action.AbilityActionRegistry;
import me.illusion.ability.metadata.AbilityMetadataHolder;
import me.illusion.ability.player.AbilityPlayer;
import org.bukkit.configuration.ConfigurationSection;

public interface Ability extends DelegateAbilityCastingTarget {

    static AbilityBuilder builder(String id) {
        return AbilityBuilder.create(id);
    }

    String getId();

    AbilityActionRegistry getActionRegistry();

    AbilityMetadataHolder getGlobalMetadata();
    AbilityMetadataHolder getMetadata(AbilityPlayer player);

    ConfigurationSection getConfiguration();
}
