package me.illusion.ability.restriction.impl;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.restriction.AbilityRestriction;
import me.illusion.ability.trigger.impl.entity.AbilityDamageContext;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class PlayerDamageTypeRestriction implements AbilityRestriction<AbilityDamageContext> {

    private final Predicate<DamageCause> filter;

    private PlayerDamageTypeRestriction(Predicate<DamageCause> filter) {
        this.filter = filter;
    }

    public static PlayerDamageTypeRestriction of(Predicate<DamageCause> filter) {
        return new PlayerDamageTypeRestriction(filter);
    }

    public static PlayerDamageTypeRestriction blacklist(DamageCause... causes) {
        return blacklist(List.of(causes));
    }

    public static PlayerDamageTypeRestriction blacklist(Collection<DamageCause> causes) {
        return of(damageCause -> !causes.contains(damageCause));
    }

    public static PlayerDamageTypeRestriction whitelist(DamageCause... causes) {
        return whitelist(List.of(causes));
    }

    public static PlayerDamageTypeRestriction whitelist(Collection<DamageCause> causes) {
        return of(causes::contains);
    }

    @Override
    public boolean canTrigger(AbilityActionContext<AbilityDamageContext> actionContext) {
        AbilityDamageContext context = actionContext.getContext();
        DamageCause cause = context.getCause();
        return filter.test(cause);
    }
}
