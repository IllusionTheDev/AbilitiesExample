package me.illusion.heroes.command;

import java.util.ArrayList;
import java.util.List;
import me.illusion.heroes.AbilityPlugin;
import me.illusion.heroes.data.HeroType;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class HeroCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Usage: /hero <type|none>");
            return true;
        }

        String heroName = args[0];

        if (heroName.equalsIgnoreCase("none")) {
            AbilityPlugin.getInstance().getHeroTracker().setHero(player.getUniqueId(), null);
            player.sendMessage(ChatColor.GREEN + "Hero removed!");
            return true;
        }

        try {
            HeroType type = HeroType.valueOf(heroName);
            AbilityPlugin.getInstance().getHeroTracker().setHero(player.getUniqueId(), type);
            player.sendMessage(ChatColor.GREEN + "Hero set to: " + ChatColor.GOLD + type.name());
        } catch (IllegalArgumentException e) {
            player.sendMessage(ChatColor.RED + "Invalid hero type: " + heroName);
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> completions = new ArrayList<>();
            String input = args[0].toLowerCase();

            for (HeroType type : HeroType.values()) {
                if (type.name().toLowerCase().startsWith(input)) {
                    completions.add(type.name().toLowerCase());
                }
            }

            if ("none".startsWith(input)) {
                completions.add("none");
            }

            return completions;
        }
        return new ArrayList<>();
    }
}
