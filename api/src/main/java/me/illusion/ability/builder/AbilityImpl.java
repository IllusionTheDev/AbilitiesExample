package me.illusion.ability.builder;

import me.illusion.ability.Ability;
import me.illusion.ability.action.AbilityActionRegistry;
import me.illusion.ability.metadata.AbilityMetadataHolder;
import me.illusion.ability.player.AbilityPlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemoryConfiguration;

public class AbilityImpl implements Ability {

    private final String id;
    private final AbilityActionRegistry actionRegistry;
    private final AbilityMetadataHolder metadataHolder;

    private AbilityImpl(String id) {
        this.id = id;
        this.actionRegistry = AbilityActionRegistry.create(this);
        this.metadataHolder = new AbilityMetadataHolder();
    }

    static AbilityImpl create(String id) {
        return new AbilityImpl(id);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public AbilityActionRegistry getActionRegistry() {
        return actionRegistry;
    }

    @Override
    public AbilityMetadataHolder getGlobalMetadata() {
        return metadataHolder;
    }

    @Override
    public AbilityMetadataHolder getMetadata(AbilityPlayer player) {
        return player.getMetadata(this);
    }

    @Override
    public ConfigurationSection getConfiguration() {
        return new MemoryConfiguration(); // TODO: Homework for the user
    }
}
