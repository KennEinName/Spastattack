package de.kenneinname.spastattack.commands;

import de.kenneinname.spastattack.Spastattack;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class TPACommand implements CommandExecutor {

    public HashMap<Player, ArrayList<Player>> anfrage = new HashMap<Player, ArrayList<Player>>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }

        final Player player = (Player) sender;

        if (args.length == 1) {
            Player to = Bukkit.getPlayer(args[0]);

            TextComponent annehmen = new TextComponent(Spastattack.PREFIX + "§aAnfrage annehmen");
            TextComponent ablehnen = new TextComponent(Spastattack.PREFIX + "§cAnfrage ablehnen");

            annehmen.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§a/tpa accept " + player.getName())));
            ablehnen.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§c/tpa deny " + player.getName())));

            annehmen.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpa accept " + player.getName()));
            ablehnen.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpa deny " + player.getName()));

            if (to == null) {
                player.sendMessage(Spastattack.PREFIX + "§cDer Spieler muss online sein!");

            } else if (to.getName() == player.getName()) {
                player.sendMessage(Spastattack.PREFIX + "§cDu kannst dir nicht selber eine Anfrage senden...");
            } else {
                if (anfrage.containsKey(to)) {
                    if (anfrage.get(to).contains(player)) {
                        player.sendMessage(Spastattack.PREFIX + "§cDu hast dem Spieler §e" + to.getName() + "§c bereits eine Anfrage gesendet!");
                    } else {
                        anfrage.get(to).add(player);
                        player.sendMessage(Spastattack.PREFIX + "§aDu hast eine §eTPA Anfrage an " + to.getName() + "§a gesendet!");
                        to.sendMessage(Spastattack.PREFIX + "§aDu hast eine §eTPA Anfrage von " + player.getName() + " §a erhalten.");

                        to.spigot().sendMessage(annehmen);
                        to.spigot().sendMessage(ablehnen);
                    }
                } else {
                    ArrayList<Player> request = new ArrayList<Player>();
                    request.add(player);
                    anfrage.put(to, request);
                    player.sendMessage(Spastattack.PREFIX + "§aDu hast eine §eTPA Anfrage von " + to.getName() + "§a gesendet!");
                    to.sendMessage(Spastattack.PREFIX + "§aDu hast eine §eTPA Anfrage von " + player.getName() + " §a erhalten.");
                    to.spigot().sendMessage(annehmen);
                    to.spigot().sendMessage(ablehnen);
                }
            }
        } else if (args.length == 2) {
            if (args[0].equalsIgnoreCase("accept")) {
                Player from = Bukkit.getPlayer(args[1]);

                if (from == null) {
                    player.sendMessage(Spastattack.PREFIX + "§cDer Spieler §e" + args[1] + " §cist offline");
                } else {
                    if (anfrage.containsKey(player)) {
                        if (anfrage.get(player).contains(from)) {
                            player.sendMessage(Spastattack.PREFIX + "§aDu hast die Anfrage von §e" + from.getName() + "§a angenommen!");
                            from.sendMessage(Spastattack.PREFIX + "§e" + player.getName() + "§a hat deine Anfrage angenommen!");
                            from.teleport(player);
                            anfrage.get(player).remove(from);
                        } else {
                            player.sendMessage(Spastattack.PREFIX + "§cDu hast keine Anfrage von diesem Spieler!");
                        }
                    } else {
                        player.sendMessage(Spastattack.PREFIX + "§cDu hast keine Anfrage von diesem Spieler!");
                    }
                }
            } else if (args[0].equalsIgnoreCase("deny")) {
                Player from = Bukkit.getPlayer(args[1]);

                if (from == null) {
                    player.sendMessage(Spastattack.PREFIX + "§cDer Spieler §e" + args[1] + " §cist offline");
                } else {
                    if (anfrage.containsKey(player)) {
                        if (anfrage.get(player).contains(from)) {
                            player.sendMessage(Spastattack.PREFIX + "§cDu hast die Anfrage von §e" + from.getName() + "§c abgelehnt!");
                            from.sendMessage(Spastattack.PREFIX + "§e" + player.getName() + "§c hat deine Anfrage abgelehnt!");
                            anfrage.get(player).remove(from);
                        } else {
                            player.sendMessage(Spastattack.PREFIX + "§cDu hast keine Anfrage von diesem Spieler!");
                        }
                    } else {
                        player.sendMessage(Spastattack.PREFIX + "§cDu hast keine Anfrage von diesem Spieler!");
                    }
                }
            } else {
                player.sendMessage(Spastattack.PREFIX + "§cBitte benutze §e/tpa accept [SPIELERNAME] §coder §e/tpa [SPIELERNAME] deny§c!");
            }
        } else {
            player.sendMessage(Spastattack.PREFIX + "§cBitte benutze §e/tpa [NAME]§c!");
        }

        return false;
    }
}
