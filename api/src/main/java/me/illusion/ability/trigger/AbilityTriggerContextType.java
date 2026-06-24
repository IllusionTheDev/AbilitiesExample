package me.illusion.ability.trigger;

/**
 * Tag interface. Holds a context type
 * @param <T> The context it holds
 */
public interface AbilityTriggerContextType<T extends AbilityTriggerContext> {

    static <T extends AbilityTriggerContext> AbilityTriggerContextType<T> create() {
        return new AbilityTriggerContextType<>() {};
    }
}
