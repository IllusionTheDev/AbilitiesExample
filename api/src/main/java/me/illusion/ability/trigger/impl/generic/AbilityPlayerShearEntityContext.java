package me.illusion.ability.trigger.impl.generic;

import io.papermc.paper.entity.Shearable;
import me.illusion.ability.player.AbilityPlayer;
import me.illusion.ability.trigger.impl.entity.AbilityEntityContext;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;

public class AbilityPlayerShearEntityContext implements AbilityPlayerContext, AbilityEntityContext {

    private final AbilityPlayer player;
    private final Shearable target;

    public AbilityPlayerShearEntityContext(AbilityPlayer player, Shearable target) {
        this.player = player;
        this.target = target;
    }

    @Override
    public Shearable getTarget() {
        return target;
    }

    @Override
    public AbilityPlayer getPlayer() {
        return player;
    }
}
