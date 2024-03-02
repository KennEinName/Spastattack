package de.kenneinname.spastattack;

import de.kenneinname.spastattack.commands.*;
import de.kenneinname.spastattack.listeners.DeathEvent;
import de.kenneinname.spastattack.listeners.JoinQuitEvent;
import de.kenneinname.spastattack.scoreboards.TablistManager;
import de.kenneinname.spastattack.utils.RecipeLoader;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Spastattack extends JavaPlugin {

    public static final String PREFIX = "§7[§eSpast§7-§eAttack§7] ";

    private TablistManager tablistManager;
    private static Spastattack spastattack;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§a§eSpast§7-§eAttack§a ist jetzt aktiv!");

        spastattack = this;
        tablistManager = new TablistManager();

        new RecipeLoader().registerRecipes();

        // Registering Commands und Listeners
        this.registerCommands();
        this.registerListeners();

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(PREFIX + "§c§eSpast§7-§eAttack§c ist nicht mehr aktiv!");

    }

    private void registerCommands() {
        this.getCommand("sethome").setExecutor(new SetHomeCommand());
        this.getCommand("home").setExecutor(new HomeCommand());
        this.getCommand("tpa").setExecutor(new TPACommand());
        this.getCommand("playtime").setExecutor(new PlaytimeCommand());
        this.getCommand("tphere").setExecutor(new TPHereCommand());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new JoinQuitEvent(), this);
        Bukkit.getPluginManager().registerEvents(new DeathEvent(), this);
    }

    public static Spastattack getSpastattack() {
        return spastattack;
    }

    public TablistManager getTablistManager() {
        return tablistManager;
    }

}
