package me.illusion.ability.action;

import me.illusion.ability.Ability;
import me.illusion.ability.player.AbilityPlayer;
import me.illusion.ability.trigger.AbilityTriggerContext;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Holds all the context for an ability action call.
 * You're welcome to add more fields to this class, like ability levels, for example
 * @param <T> The type of ability trigger context
 */
public class AbilityActionContext<T extends AbilityTriggerContext> {

    private final T context;
    private final Ability ability;

    private AbilityActionContext(T context, Ability ability) {
        this.context = context;
        this.ability = ability;
    }

    public static <T extends AbilityTriggerContext> AbilityActionContext<T> of(T context, Ability ability) {
        return new AbilityActionContext<>(context, ability);
    }

    public static AbilityActionContext<AbilityPlayerContext> of(AbilityPlayer player, Ability ability) {
        return AbilityActionContext.of(AbilityPlayerContext.of(player), ability);
    }

    public T getContext() {
        return context;
    }

    public Ability getAbility() {
        return ability;
    }

    public ConfigurationSection getSettings() {
        return ability.getGlobalSettings(); // For example, you can make this class return the settings for the player's ability level
    }

}
