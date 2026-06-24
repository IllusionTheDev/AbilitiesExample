package me.illusion.ability.action;

import java.util.Iterator;
import java.util.List;
import me.illusion.ability.trigger.AbilityTriggerContext;
import org.jetbrains.annotations.NotNull;

/**
 * Represents a bulk list of actions that are of the type (or supertype) T
 * @param <T> The base type of every action in this list
 */
public class AbilityActions<T extends AbilityTriggerContext> implements Iterable<AbilityAction<? super T>>, AbilityAction<T> {

    private final List<AbilityAction<? super T>> actions;

    private AbilityActions(List<AbilityAction<? super T>> actions) {
        this.actions = List.copyOf(actions);
    }

    public static <T extends AbilityTriggerContext> AbilityActions<T> of(List<AbilityAction<? super T>> actions) {
        return new AbilityActions<>(actions);
    }

    public static <T extends AbilityTriggerContext> AbilityActions<T> of(AbilityAction<? super T>... actions) {
        return of(List.<AbilityAction<? super T>>of(actions));
    }

    public List<AbilityAction<? super T>> getActions() {
        return actions;
    }

    @Override
    public @NotNull Iterator<AbilityAction<? super T>> iterator() {
        return actions.iterator();
    }


    @Override
    public void handle(AbilityActionContext<T> context) {
        for (AbilityAction<? super T> action : actions) {
            action.call(context);
        }
    }
}
