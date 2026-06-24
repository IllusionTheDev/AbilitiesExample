package me.illusion.heroes.logic;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import me.illusion.heroes.data.HeroType;
import org.bukkit.entity.Player;

public class HeroTracker {

    private final Map<UUID, HeroType> playerHeroes = new HashMap<>();

    public void setHero(UUID uuid, HeroType type) {
        if (type == null) {
            playerHeroes.remove(uuid);
        } else {
            playerHeroes.put(uuid, type);
        }
    }

    public HeroType getHero(UUID uuid) {
        return playerHeroes.get(uuid);
    }

    public HeroType getHero(Player player) {
        return getHero(player.getUniqueId());
    }

    public void unloadPlayer(Player player) {
        setHero(player.getUniqueId(), null);
    }
}
