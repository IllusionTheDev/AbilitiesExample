package me.illusion.ability.trigger.impl.generic;

import me.illusion.ability.player.AbilityPlayer;
import me.illusion.ability.trigger.impl.entity.AbilityEntityContext;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;
import org.bukkit.entity.Entity;

public class AbilityPlayerKillEntityContext implements AbilityPlayerContext, AbilityEntityContext {

    private final AbilityPlayer player;
    private final Entity target;

    public AbilityPlayerKillEntityContext(AbilityPlayer player, Entity target) {
        this.player = player;
        this.target = target;
    }

    @Override
    public Entity getTarget() {
        return target;
    }

    @Override
    public AbilityPlayer getPlayer() {
        return player;
    }
}
