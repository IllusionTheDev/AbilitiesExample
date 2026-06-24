package me.illusion.ability.trigger;

/**
 * Tag interface. Makes the generics a bit more restrictive.
 * You're welcome to refactor the whole system to get rid of this, but it introduces the opportunity for abuse.
 * Basically wraps whatever event / action triggers an ability.
 */
public interface AbilityTriggerContext {

    AbilityTriggerContext EMPTY_CONTEXT = new AbilityTriggerContext() {};
}
