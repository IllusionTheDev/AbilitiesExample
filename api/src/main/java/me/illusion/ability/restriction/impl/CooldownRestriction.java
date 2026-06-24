package me.illusion.ability.restriction.impl;


import me.illusion.ability.Ability;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.metadata.AbilityMetadataHolder;
import me.illusion.ability.metadata.AbilityMetadataKeys;
import me.illusion.ability.restriction.AbilityRestriction;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;

public class CooldownRestriction implements AbilityRestriction<AbilityPlayerContext> {

    private static final CooldownRestriction INSTANCE = new CooldownRestriction();

    public static CooldownRestriction isOffCooldown() {
        return INSTANCE;
    }

    @Override
    public boolean canTrigger(AbilityActionContext<AbilityPlayerContext> actionContext) {
        AbilityPlayerContext context = actionContext.getContext();
        Ability ability = actionContext.getAbility();

        AbilityMetadataHolder metadata = ability.getMetadata(context.getPlayer());
        long expiry = metadata.get(AbilityMetadataKeys.COOLDOWN);
        long millisLeft = expiry - System.currentTimeMillis();

        if (expiry == 0) {
            return true;
        }

        return millisLeft <= 0;
    }
}
