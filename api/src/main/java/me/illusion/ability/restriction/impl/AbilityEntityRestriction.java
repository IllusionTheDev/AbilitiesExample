package me.illusion.ability.restriction.impl;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.restriction.AbilityRestriction;
import me.illusion.ability.trigger.impl.entity.AbilityEntityContext;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public class AbilityEntityRestriction implements AbilityRestriction<AbilityEntityContext> {

    private final Predicate<AbilityActionContext<AbilityEntityContext>> filter;

    private AbilityEntityRestriction(Predicate<AbilityActionContext<AbilityEntityContext>> filter) {
        this.filter = filter;
    }

    public static AbilityEntityRestriction of(Predicate<Entity> filter) {
        return new AbilityEntityRestriction(context -> filter.test(context.getContext().getTarget()));
    }

    public static AbilityEntityRestriction isType(Predicate<EntityType> filter) {
        return of(entity -> filter.test(entity.getType()));
    }

    public static AbilityEntityRestriction blacklist(EntityType... causes) {
        return blacklist(List.of(causes));
    }

    public static AbilityEntityRestriction blacklist(Collection<EntityType> causes) {
        return isType(damageCause -> !causes.contains(damageCause));
    }

    public static AbilityEntityRestriction whitelist(EntityType... causes) {
        return whitelist(List.of(causes));
    }

    public static AbilityEntityRestriction whitelist(Collection<EntityType> causes) {
        return isType(causes::contains);
    }

    public static AbilityEntityRestriction configurableWhitelist() {
        return new AbilityEntityRestriction(context -> {
            Entity entity = context.getContext().getTarget();
            ConfigurationSection config = context.getSettings();

            List<String> list = config.getStringList("allowed-types");

            return list.contains(entity.getType().name());
        });
    }

    @Override
    public boolean canTrigger(AbilityActionContext<AbilityEntityContext> actionContext) {
        return filter.test(actionContext);
    }
}
