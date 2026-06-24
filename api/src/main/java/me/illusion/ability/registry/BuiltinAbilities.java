package me.illusion.ability.registry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import me.illusion.ability.Ability;
import me.illusion.ability.trigger.AbilityTriggerContext;
import me.illusion.ability.trigger.AbilityTriggerContextType;

public final class BuiltinAbilities {

    private static final Map<String, Ability> ABILITIES = new ConcurrentHashMap<>();

    private BuiltinAbilities() {

    }

    public static Ability register(Ability ability) {
        ABILITIES.put(ability.getId(), ability);
        return ability;
    }

    public static Ability get(String name) {
        return ABILITIES.get(name);
    }


    public static List<Ability> getAll() {
        return new ArrayList<>(ABILITIES.values());
    }

    public static List<String> getNames() {
        return new ArrayList<>(ABILITIES.keySet());
    }

    public static <T extends AbilityTriggerContext> void trigger(AbilityTriggerContextType<T> type, T context) {
        for (Ability ability : BuiltinAbilities.getAll()) {
            ability.trigger(type, context);
        }
    }

}