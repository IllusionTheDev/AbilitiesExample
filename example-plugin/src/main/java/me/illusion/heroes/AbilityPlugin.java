package me.illusion.heroes;

import me.illusion.ability.AbilitySystem;
import me.illusion.heroes.ability.HulkAbilities;
import me.illusion.heroes.ability.IronManAbilities;
import me.illusion.heroes.command.HeroCommand;
import me.illusion.heroes.logic.HeroTracker;
import org.bukkit.plugin.java.JavaPlugin;

public final class AbilityPlugin extends JavaPlugin {

    private static AbilityPlugin instance;

    private HeroTracker tracker;
    
    @Override
    public void onEnable() {
        instance = this;
        AbilitySystem.init(this);

        // Plugin startup logic
        tracker = new HeroTracker();

        // Register abilities
        HulkAbilities.register();
        IronManAbilities.register();

        // Register command
        HeroCommand heroCommand = new HeroCommand();
        getCommand("hero").setExecutor(heroCommand);
        getCommand("hero").setTabCompleter(heroCommand);
    }

    public static AbilityPlugin getInstance() {
        return instance;
    }

    public HeroTracker getHeroTracker() {
        return tracker;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
