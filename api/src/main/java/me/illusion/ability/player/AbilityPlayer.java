package me.illusion.ability.player;

import java.util.UUID;
import me.illusion.ability.Ability;
import me.illusion.ability.metadata.AbilityMetadataHolder;
import org.bukkit.entity.Player;

public interface AbilityPlayer {

    UUID getPlayerId();
    Player getBukkitPlayer();

    AbilityMetadataHolder getMetadata(Ability ability);

    static AbilityPlayer of(Player player) {
        // TODO: Leave this as homework :)
        return null;
    }
}
