package me.illusion.ability.builder.bind;

import me.illusion.ability.action.AbilityAction;
import me.illusion.ability.action.AbilityActions;
import me.illusion.ability.builder.bind.impl.BindableAbilityActionImpl;
import me.illusion.ability.restriction.AbilityRestriction;
import me.illusion.ability.trigger.AbilityTriggerContext;
import me.illusion.ability.trigger.AbilityTriggerContextType;
import org.jetbrains.annotations.Contract;

public interface BindableAbilityAction<T extends AbilityTriggerContext> {

    static Base bind(String name) {
        return BindableAbilityActionImpl.createBase(name);
    }

    // Mutable builder
    @Contract("_ -> this") // I'm sorry
    BindableAbilityAction<T> withRestriction(AbilityRestriction<? super T> restriction);

    @Contract("_ -> this") // Mutability bad
    BindableAbilityAction<T> firingOrder(int priority); // Alternative universe: Tie this to an EventPriority and rework how event calls are made.

    // Transform into an immutable class (but still mirrors the mutable builder because I'm lazy)
    BoundAbilityAction<T> to(AbilityActions<? super T> delegate);

    default BoundAbilityAction<T> to(AbilityAction<? super T>... actions) {
        return to(AbilityActions.of(actions));
    }

    interface Base {

        <T extends AbilityTriggerContext> BindableAbilityAction<T> withType(AbilityTriggerContextType<T> type);

    }
}
