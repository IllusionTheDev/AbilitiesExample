package me.illusion.heroes.listener;

import me.illusion.heroes.AbilityPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLifecycleListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        AbilityPlugin.getInstance().getHeroTracker().unloadPlayer(event.getPlayer());
    }
}
