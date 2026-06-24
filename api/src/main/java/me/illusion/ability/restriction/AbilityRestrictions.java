package me.illusion.ability.restriction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.trigger.AbilityTriggerContext;
import org.jetbrains.annotations.NotNull;

public class AbilityRestrictions<T extends AbilityTriggerContext> implements Iterable<AbilityRestriction<? super T>>, AbilityRestriction<T> {

    private final List<AbilityRestriction<? super T>> restrictions;

    private AbilityRestrictions(List<AbilityRestriction<? super T>> restrictions) {
        this.restrictions = restrictions;
    }

    public AbilityRestrictions() {
        this(new ArrayList<>());
    }

    public static <T extends AbilityTriggerContext> AbilityRestrictions<? super T> create() {
        return new AbilityRestrictions<>(new ArrayList<>());
    }

    @SafeVarargs // Lets us have mixed inheritance types, it's okay. 
    public static <T extends AbilityTriggerContext> AbilityRestrictions<T> create(AbilityRestriction<? super T>... restrictions) {
        return new AbilityRestrictions<>(List.<AbilityRestriction<? super T>>of(restrictions));
    }

    public AbilityRestrictions<T> add(AbilityRestriction<? super T> restriction) {
        restrictions.add(restriction);
        return this;
    }

    @SafeVarargs
    public final AbilityRestrictions<T> add(AbilityRestriction<? super T>... restrictions) {
        Collections.addAll(this.restrictions, restrictions);
        return this;
    }

    public List<AbilityRestriction<? super T>> getRestrictions() {
        return List.copyOf(restrictions);
    }

    public AbilityRestrictions<T> and(AbilityRestriction<? super T> restriction) {
        List<AbilityRestriction<? super T>> newRestrictions = new ArrayList<>(restrictions);
        newRestrictions.add(restriction);
        return new AbilityRestrictions<>(newRestrictions);
    }

    @Override
    public boolean canTrigger(AbilityActionContext<T> context) {
        for (AbilityRestriction<? super T> restriction : restrictions) {
            if (!restriction.can(context)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public @NotNull Iterator<AbilityRestriction<? super T>> iterator() {
        return restrictions.iterator();
    }
}
