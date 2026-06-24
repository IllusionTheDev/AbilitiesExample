package me.illusion.ability.trigger.impl.player;

import me.illusion.ability.player.AbilityPlayer;
import me.illusion.ability.trigger.AbilityTriggerContext;
import org.bukkit.entity.Player;

public interface AbilityPlayerContext extends AbilityTriggerContext {

    AbilityPlayer getPlayer();

    default Player getBukkitPlayer() {
        return getPlayer().getBukkitPlayer();
    }

    static AbilityPlayerContext of(AbilityPlayer player) {
        return new SimpleAbilityPlayerContext(player);
    }

    static AbilityPlayerContext of(Player player) {
        return AbilityPlayerContext.of(AbilityPlayer.of(player));
    }
}
