package me.illusion.ability.action.value;

import java.util.function.Function;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.trigger.AbilityTriggerContext;
import org.bukkit.configuration.ConfigurationSection;

/**
 * Represents a "variable" that's context-dependent.
 * @param <C> The context type that determines the value
 * @param <V> The value type
 */
public interface AbilityActionValue<C extends AbilityTriggerContext, V> {

    V getValue(AbilityActionContext<C> context);

    static <C extends AbilityTriggerContext, V> AbilityActionValue<C, V> of(V value) {
        return (actionContext) -> value;
    }

    static <C extends AbilityTriggerContext, V> AbilityActionValue<C, V> configurable(Function<ConfigurationSection, V> function) {
        return (actionContext) -> function.apply(actionContext.getSettings());
    }
}
