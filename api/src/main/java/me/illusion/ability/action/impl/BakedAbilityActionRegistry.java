package me.illusion.ability.action.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import me.illusion.ability.Ability;
import me.illusion.ability.action.AbilityActionContext;
import me.illusion.ability.action.AbilityActionRegistry;
import me.illusion.ability.builder.bind.BoundAbilityAction;
import me.illusion.ability.trigger.AbilityTriggerContext;
import me.illusion.ability.trigger.AbilityTriggerContextType;

public class BakedAbilityActionRegistry implements AbilityActionRegistry {

    private final Map<String, BoundAbilityAction<?>> actionsByName = new ConcurrentHashMap<>();
    private final Map<AbilityTriggerContextType<?>, List<BoundAbilityAction<?>>> actionsByType = new ConcurrentHashMap<>();

    private final Ability parent;

    private BakedAbilityActionRegistry(Ability parent) {
        this.parent = parent;
    }

    public static AbilityActionRegistry create(Ability parent) {
        return new BakedAbilityActionRegistry(parent);
    }

    @Override
    public void register(BoundAbilityAction<?> action) {
        actionsByName.put(action.getId(), action);

        List<BoundAbilityAction<?>> actions = actionsByType.computeIfAbsent(action.getType(), k -> new ArrayList<>());
        actions.add(action);
        actions.sort(Comparator.comparingInt(BoundAbilityAction::getFiringOrder)); // Sort on insertion to lessen the load of calling abilities
    }

    @Override
    public <T extends AbilityTriggerContext> void triggerAction(String actionId, AbilityTriggerContextType<T> type, T context) {
        BoundAbilityAction<?> action = actionsByName.get(actionId);

        if (action == null) {
            return;
        }

        if (action.getType() != type) {
            return;
        }

        try {
            call(action, context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public <T extends AbilityTriggerContext> void trigger(AbilityTriggerContextType<T> type, T context) {
        List<BoundAbilityAction<T>> actions = getBakedActions(type);

        if (actions.isEmpty()) {
            return;
        }

        AbilityActionContext<T> contextWrapper = AbilityActionContext.of(context, parent);

        for (BoundAbilityAction<T> action : actions) {
            try {
                action.handle(contextWrapper);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private <T extends AbilityTriggerContext> void call(BoundAbilityAction<?> action, T context) {
        BoundAbilityAction<T> casted = (BoundAbilityAction<T>) action; // Yeah dirty hack whatever
        casted.handle(AbilityActionContext.of(context, parent));
    }

    private <T extends AbilityTriggerContext> List<BoundAbilityAction<T>> getBakedActions(AbilityTriggerContextType<T> type) {
        List<BoundAbilityAction<?>> actions = actionsByType.get(type);

        if (actions == null) {
            return Collections.emptyList();
        }

        List<BoundAbilityAction<T>> castedActions = new ArrayList<>();

        for (BoundAbilityAction<?> action : actions) {
            castedActions.add((BoundAbilityAction<T>) action);
        }

        return castedActions;
    }
}
