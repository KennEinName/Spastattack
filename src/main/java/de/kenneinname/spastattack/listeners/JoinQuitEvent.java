package de.kenneinname.spastattack.listeners;

import de.kenneinname.spastattack.Spastattack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;
import java.util.Map;

public class JoinQuitEvent implements Listener {

    public static final Map<String, Long> joinTimeMap = new HashMap<>();

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        event.setJoinMessage(Spastattack.PREFIX + "§e" + event.getPlayer().getName() + " §ahat den Server betreten.");

        Spastattack.getSpastattack().getTablistManager().setPlayerList(event.getPlayer());

        if (!(Spastattack.getSpastattack().getConfig().contains(player.getDisplayName() + ".PlayTime"))) {
            Spastattack.getSpastattack().getConfig().set(player.getDisplayName() + ".PlayTime", 0);
        }

        Spastattack.getSpastattack().saveConfig();

        long joinTime = System.currentTimeMillis();

        joinTimeMap.put(player.getDisplayName(), joinTime);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        Player player = event.getPlayer();

        event.setQuitMessage(Spastattack.PREFIX + "§e" + event.getPlayer().getName() + " §chat den Server verlassen.");

        long joinTime = joinTimeMap.get(event.getPlayer().getDisplayName());
        long disconnectTime = System.currentTimeMillis();
        long playtimeSeconds = (disconnectTime - joinTime) / 1000;

        long oldPlayTime = Spastattack.getSpastattack().getConfig().getLong(player.getDisplayName() + ".PlayTime");

        Bukkit.getConsoleSender().sendMessage(Spastattack.PREFIX + "§6Alte Spielzeit: §b" + oldPlayTime + " §6in Sekunden §a(" + player.getDisplayName() + ")");
        Bukkit.getConsoleSender().sendMessage(Spastattack.PREFIX + "§6Session Playtime: §b" + playtimeSeconds + " §6in Sekunden §a(" + player.getDisplayName() + ")");
        Bukkit.getConsoleSender().sendMessage(Spastattack.PREFIX + "§6Ganze Spielzeit: §b" + (oldPlayTime + playtimeSeconds) + " §6in Sekunden §a(" + player.getDisplayName() + ")");

        Spastattack.getSpastattack().getConfig().set(player.getDisplayName() + ".PlayTime", oldPlayTime + playtimeSeconds);
        Spastattack.getSpastattack().saveConfig();
        joinTimeMap.remove(player.getDisplayName());

    }

}
