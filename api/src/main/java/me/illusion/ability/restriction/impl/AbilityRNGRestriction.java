package me.illusion.ability.restriction.impl;

import java.util.concurrent.ThreadLocalRandom;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.action.value.AbilityActionValue;
import me.illusion.ability.restriction.AbilityRestriction;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;

public class AbilityRNGRestriction implements AbilityRestriction<AbilityPlayerContext> {

    private final AbilityActionValue<AbilityPlayerContext, Float> chance;

    private AbilityRNGRestriction(AbilityActionValue<AbilityPlayerContext, Float> chance) {
        this.chance = chance;
    }

    public static AbilityRNGRestriction configurable() {
        return new AbilityRNGRestriction(AbilityActionValue.configurable(config -> (float) config.getDouble("chance")));
    }

    public static AbilityRNGRestriction fixed(float chance) {
        return new AbilityRNGRestriction(AbilityActionValue.of(chance));
    }

    @Override
    public boolean canTrigger(AbilityActionContext<AbilityPlayerContext> context) {
        return ThreadLocalRandom.current().nextFloat() < this.chance.getValue(context);
    }
}
