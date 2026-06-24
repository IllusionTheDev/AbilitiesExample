package me.illusion.ability;

import me.illusion.ability.listener.AbilityTriggerListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AbilitySystem {

    private static boolean INITIALIZED = false;

    public static void init(JavaPlugin plugin) {
        if (INITIALIZED) {
            return;
        }

        Bukkit.getPluginManager().registerEvents(new AbilityTriggerListener(), plugin);
        // Load ability configs all of that
        INITIALIZED = true;
    }
}
