package de.kenneinname.spastattack.commands;

import de.kenneinname.spastattack.Spastattack;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.DecimalFormat;

public class PlaytimeCommand implements CommandExecutor {

    int seconds;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        final Player player = (Player) sender;

        if (args.length == 0) {

            long totalPlaytime = Spastattack.getSpastattack().getConfig().getLong(player.getDisplayName() + ".PlayTime");

             seconds = (int) totalPlaytime;

            // Umrechnung in Tage, Stunden, Minuten und Sekunden
            long days = totalPlaytime / (24 * 60 * 60);
            long hours = (totalPlaytime % (24 * 60 * 60)) / (60 * 60);
            long minutes = ((totalPlaytime % (24 * 60 * 60)) % (60 * 60)) / 60;
            long seconds = ((totalPlaytime % (24 * 60 * 60)) % (60 * 60)) % 60;

            // Formatierung
            DecimalFormat df = new DecimalFormat("00");
            String timeDays = df.format(days) + ":" + df.format(hours) + ":" + df.format(minutes) + ":" + df.format(seconds);
            String timeHours = df.format(hours) + ":" + df.format(minutes) + ":" + df.format(seconds);
            String timeMinutes = df.format(minutes) + ":" + df.format(seconds);
            String timeSeconds = df.format(seconds);

            if (totalPlaytime <= 59) {
                player.sendMessage(Spastattack.PREFIX + "§eDu Spielst schon seit §a" + timeSeconds + " Sekunden §eauf dem Spastattack Server");
            } else if (totalPlaytime <= 3599) {
                player.sendMessage(Spastattack.PREFIX + "§eDu Spielst schon seit §a" + timeMinutes + " Minuten §eauf dem Spastattack Server");
            } else if (totalPlaytime <= 86399) {
                player.sendMessage(Spastattack.PREFIX + "§eDu Spielst schon seit §a" + timeHours + " Stunden §eauf dem Spastattack Server");
            } else if (totalPlaytime > 86400) {
                player.sendMessage(Spastattack.PREFIX + "§eDu Spielst schon seit §a" + timeDays + " Tagen §eauf dem Spastattack Server");
            }


        } else if (args.length == 1) {

            final Player to = Bukkit.getPlayer(args[0]);

            long totalPlaytime = Spastattack.getSpastattack().getConfig().getLong(player.getDisplayName() + ".PlayTime");

            seconds = (int) totalPlaytime;

            // Umrechnung in Tage, Stunden, Minuten und Sekunden
            long days = totalPlaytime / (24 * 60 * 60);
            long hours = (totalPlaytime % (24 * 60 * 60)) / (60 * 60);
            long minutes = ((totalPlaytime % (24 * 60 * 60)) % (60 * 60)) / 60;
            long seconds = ((totalPlaytime % (24 * 60 * 60)) % (60 * 60)) % 60;

            // Formatierung
            DecimalFormat df = new DecimalFormat("00");
            String timeDays = df.format(days) + ":" + df.format(hours) + ":" + df.format(minutes) + ":" + df.format(seconds);
            String timeHours = df.format(hours) + ":" + df.format(minutes) + ":" + df.format(seconds);
            String timeMinutes = df.format(minutes) + ":" + df.format(seconds);
            String timeSeconds = df.format(seconds);

            if (Spastattack.getSpastattack().getConfig().contains(to.getName() + ".PlayTime")) {
                if (totalPlaytime <= 59) {
                    player.sendMessage(Spastattack.PREFIX + "§eDie Spielzeit von §a" + to.getName() + " §ebeträgt§a " + timeSeconds + " Sekunden§e!");
                } else if (totalPlaytime <= 3599) {
                    player.sendMessage(Spastattack.PREFIX + "§eDie Spielzeit von §a" + to.getName() + " §ebeträgt§a " + timeMinutes + " Minuten§e!");
                } else if (totalPlaytime <= 86399) {
                    player.sendMessage(Spastattack.PREFIX + "§eDie Spielzeit von §a" + to.getName() + " §ebeträgt§a " + timeHours + " Stunden§e!");
                } else if (totalPlaytime > 86400) {
                    player.sendMessage(Spastattack.PREFIX + "§eDie Spielzeit von §a" + to.getName() + " §ebeträgt§a " + timeDays + " Tage§e!");
                }
            } else {
                player.sendMessage(Spastattack.PREFIX + "§cDer Spieler war noch nie auf dem Server!");
            }

        }

        return false;
    }
}
