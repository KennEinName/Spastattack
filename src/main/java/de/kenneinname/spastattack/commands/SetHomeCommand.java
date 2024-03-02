package de.kenneinname.spastattack.commands;

import de.kenneinname.spastattack.Spastattack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        final Player player = (Player) sender;

        if (args.length == 0) {
            FileConfiguration fileConfiguration = Spastattack.getSpastattack().getConfig();

            fileConfiguration.set(player.getName() + ".World", player.getWorld().getName());
            fileConfiguration.set(player.getName() + ".X", player.getLocation().getX());
            fileConfiguration.set(player.getName() + ".Y", player.getLocation().getY());
            fileConfiguration.set(player.getName() + ".Z", player.getLocation().getZ());

            Spastattack.getSpastattack().saveConfig();

            player.sendMessage(Spastattack.PREFIX + "§aDu hast dein Home gesetzt.");
        } else {
            player.sendMessage(Spastattack.PREFIX + "§cBitte benutze §e/sethome§c!");
        }

        return false;
    }
}
