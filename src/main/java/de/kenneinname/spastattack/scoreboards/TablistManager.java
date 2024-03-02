package de.kenneinname.spastattack.scoreboards;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TablistManager {

    public void setPlayerList(Player player) {
        player.setPlayerListHeaderFooter("§7" + ChatColor.STRIKETHROUGH + "          " + ChatColor.RESET + "§7[ §eSpast§7-§eAttack §7]" + ChatColor.STRIKETHROUGH + "          " + ChatColor.RESET + "\n",
                "\n§7[§9Discord§7]: §7https://discord.gg/BzzZgAkBry");
    }

}
