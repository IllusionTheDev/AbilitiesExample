package me.illusion.ability.restriction.impl;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.restriction.AbilityRestriction;
import me.illusion.ability.trigger.impl.generic.AbilityItemContext;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public class AbilityItemRestriction implements AbilityRestriction<AbilityItemContext> {

    private final Predicate<AbilityActionContext<AbilityItemContext>> filter;

    private AbilityItemRestriction(Predicate<AbilityActionContext<AbilityItemContext>> filter) {
        this.filter = filter;
    }

    public static AbilityItemRestriction of(Predicate<ItemStack> filter) {
        return new AbilityItemRestriction(context -> filter.test(context.getContext().getItem()));
    }

    public static AbilityItemRestriction isType(Predicate<Material> filter) {
        return of(Item -> filter.test(Item.getType()));
    }

    public static AbilityItemRestriction blacklist(Material... causes) {
        return blacklist(List.of(causes));
    }

    public static AbilityItemRestriction blacklist(Collection<Material> causes) {
        return isType(damageCause -> !causes.contains(damageCause));
    }

    public static AbilityItemRestriction isType(Material... causes) {
        return isType(List.of(causes));
    }

    public static AbilityItemRestriction isType(Collection<Material> causes) {
        return isType(causes::contains);
    }

    public static AbilityItemRestriction configurableWhitelist() {
        return new AbilityItemRestriction(context -> {
            ItemStack item = context.getContext().getItem();
            ConfigurationSection config = context.getSettings();

            return config.getStringList("allowed-types").contains(item.getType().name());
        });
    }

    @Override
    public boolean canTrigger(AbilityActionContext<AbilityItemContext> actionContext) {
        return filter.test(actionContext);
    }
}
