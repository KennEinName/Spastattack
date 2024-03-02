package de.kenneinname.spastattack.listeners;

import de.kenneinname.spastattack.Spastattack;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathEvent implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        event.setDeathMessage(Spastattack.PREFIX + "§c" + event.getDeathMessage());
    }
}
