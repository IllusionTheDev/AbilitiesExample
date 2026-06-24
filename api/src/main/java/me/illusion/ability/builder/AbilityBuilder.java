package me.illusion.ability.builder;

import java.util.ArrayList;
import java.util.List;
import me.illusion.ability.Ability;
import me.illusion.ability.action.AbilityActionRegistry;
import me.illusion.ability.builder.bind.BoundAbilityAction;

public class AbilityBuilder {

    private final String name;
    private final List<BoundAbilityAction<?>> boundActions;

    private AbilityBuilder(String name) {
        this.name = name;
        this.boundActions = new ArrayList<>();
    }

    public static AbilityBuilder create(String name) {
        return new AbilityBuilder(name);
    }

    @SafeVarargs
    public final AbilityBuilder withActions(BoundAbilityAction<?>... actions) {
        boundActions.addAll(List.of(actions));
        return this;
    }

    public Ability build() {
        Ability ability = AbilityImpl.create(name);
        AbilityActionRegistry actions = ability.getActionRegistry();

        for (BoundAbilityAction<?> handler : boundActions) {
            actions.register(handler);
        }

        return ability;
    }
}
