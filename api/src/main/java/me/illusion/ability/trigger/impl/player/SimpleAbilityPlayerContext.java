package me.illusion.ability.trigger.impl.player;

import me.illusion.ability.player.AbilityPlayer;

public class SimpleAbilityPlayerContext implements AbilityPlayerContext {

    private final AbilityPlayer player;

    SimpleAbilityPlayerContext(AbilityPlayer player) {
        this.player = player;
    }

    @Override
    public AbilityPlayer getPlayer() {
        return player;
    }
}
