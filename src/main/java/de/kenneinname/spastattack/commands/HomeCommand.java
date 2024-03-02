package de.kenneinname.spastattack.commands;

import de.kenneinname.spastattack.Spastattack;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class HomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        final Player player = (Player) sender;

        if (args.length == 0) {
            FileConfiguration fileConfiguration = Spastattack.getSpastattack().getConfig();

            if (!fileConfiguration.contains(player.getName() + ".World")) {

                player.sendMessage(Spastattack.PREFIX + "§cBitte benutze zuerst §e/sethome§c.");
                return false;
            }

            World world = Bukkit.getWorld(fileConfiguration.getString(player.getName() + ".World"));
            double x = fileConfiguration.getDouble(player.getName() + ".X");
            double y = fileConfiguration.getDouble(player.getName() + ".Y");
            double z = fileConfiguration.getDouble(player.getName() + ".Z");

            Location location = new Location(world, x, y, z);
            Location highestLocation = new Location(world, x, player.getWorld().getHighestBlockYAt(location) + 1, z);

            if (location.getBlock().getType() == Material.AIR) {
                player.teleport(location);
                player.sendMessage(Spastattack.PREFIX + "§aDu wurdest zu deinem Home Teleportiert!");
            } else {
                player.teleport(highestLocation);
                player.sendMessage(Spastattack.PREFIX + "§aDu wurdest zu deinem Home Teleportiert!");
                player.sendMessage(Spastattack.PREFIX + "§cDein normaler Home Spawn wurde zugebaut, deswegen wurdest du auf dem höchsten Block über ihm Teleportiert!");
            }


        } else {
            player.sendMessage(Spastattack.PREFIX + "§cBitte benutze §e/sethome§c!");
        }

        return false;
    }

}
