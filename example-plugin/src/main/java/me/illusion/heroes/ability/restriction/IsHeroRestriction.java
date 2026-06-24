package me.illusion.heroes.ability.restriction;

import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.restriction.AbilityRestriction;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;
import me.illusion.heroes.AbilityPlugin;
import me.illusion.heroes.data.HeroType;
import org.bukkit.entity.Player;

public class IsHeroRestriction implements AbilityRestriction<AbilityPlayerContext> {

    private final HeroType targetType; // Ideally use AbilityActionValue so you can make these configurable but whatever

    private IsHeroRestriction(HeroType targetType) {
        this.targetType = targetType;
    }

    public static IsHeroRestriction create(HeroType targetType) {
        return new IsHeroRestriction(targetType);
    }

    @Override
    public boolean canTrigger(AbilityActionContext<AbilityPlayerContext> context) {
        Player player = context.getContext().getBukkitPlayer();

        return AbilityPlugin.getInstance().getHeroTracker().getHero(player) == targetType;
    }
}
