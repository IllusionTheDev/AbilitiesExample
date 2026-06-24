package me.illusion.ability.listener;

import com.destroystokyo.paper.event.server.ServerTickEndEvent;
import me.illusion.ability.modifier.ValueModifiers;
import me.illusion.ability.player.AbilityPlayer;
import me.illusion.ability.registry.BuiltinAbilities;
import me.illusion.ability.trigger.AbilityTriggerContext;
import me.illusion.ability.trigger.AbilityTriggerContextTypes;
import me.illusion.ability.trigger.impl.generic.AbilityPlayerKillEntityContext;
import me.illusion.ability.trigger.impl.player.AbilityEntityDamagePlayerContext;
import me.illusion.ability.trigger.impl.player.AbilityPlayerContext;
import me.illusion.ability.trigger.impl.player.AbilityPlayerDamageContext;
import me.illusion.ability.trigger.impl.player.AbilityPlayerDamageEntityContext;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class AbilityTriggerListener implements Listener {
    

    @EventHandler
    public void onTick(ServerTickEndEvent event) {
        boolean second = event.getTickNumber() % 20 == 0;

        BuiltinAbilities.trigger(AbilityTriggerContextTypes.EVERY_SERVER_TICK, AbilityTriggerContext.EMPTY_CONTEXT);

        if (second) {
            BuiltinAbilities.trigger(AbilityTriggerContextTypes.EVERY_SERVER_SECOND, AbilityTriggerContext.EMPTY_CONTEXT);
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (second) {
                BuiltinAbilities.trigger(AbilityTriggerContextTypes.EVERY_PLAYER_SECOND, AbilityPlayerContext.of(player));
            }

            BuiltinAbilities.trigger(AbilityTriggerContextTypes.EVERY_PLAYER_TICK, AbilityPlayerContext.of(player));
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        BuiltinAbilities.trigger(AbilityTriggerContextTypes.PLAYER_JOIN, AbilityPlayerContext.of(event.getPlayer()));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        BuiltinAbilities.trigger(AbilityTriggerContextTypes.PLAYER_QUIT, AbilityPlayerContext.of(event.getPlayer()));
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity target = event.getEntity();

        DamageCause cause = event.getCause();
        double damage = event.getDamage();

        if (damager instanceof Player player) {
            AbilityPlayerDamageEntityContext context = new AbilityPlayerDamageEntityContext(AbilityPlayer.of(player), target, cause, damage);
            BuiltinAbilities.trigger(AbilityTriggerContextTypes.PLAYER_DAMAGE_ENTITY, context);

            ValueModifiers<Double> damageModifiers = context.getDamageModifiers();

            if (!damageModifiers.isEmpty()) {
                event.setDamage(context.getFinalDamage());
            }
        }

        if (target instanceof Player player) {
            AbilityEntityDamagePlayerContext context = new AbilityEntityDamagePlayerContext(AbilityPlayer.of(player), damager, cause, damage);
            BuiltinAbilities.trigger(AbilityTriggerContextTypes.ENTITY_DAMAGE_PLAYER, context);

            ValueModifiers<Double> damageModifiers = context.getDamageModifiers();

            if (!damageModifiers.isEmpty()) {
                event.setDamage(context.getFinalDamage());
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent event) {
        Entity target = event.getEntity();

        if (!(target instanceof Player player)) {
            return;
        }

        AbilityPlayerDamageContext context = new AbilityPlayerDamageContext(AbilityPlayer.of(player), event.getCause(), event.getDamage());
        BuiltinAbilities.trigger(AbilityTriggerContextTypes.PLAYER_TAKE_DAMAGE, context);

        if (context.isCancelled()) {
            event.setCancelled(true);
        }

        event.setDamage(context.getDamageModifiers().getValue(context.getDamage()));
    }

    @EventHandler
    public void onPlayerKillEntity(EntityDeathEvent event) {
        LivingEntity target = event.getEntity();
        Player killer = target.getKiller();

        if (killer == null) {
            return;
        }

        AbilityPlayerKillEntityContext context = new AbilityPlayerKillEntityContext(AbilityPlayer.of(killer), target);
        BuiltinAbilities.trigger(AbilityTriggerContextTypes.PLAYER_KILL_ENTITY, context);
    }


}
