package org.iridescent.nightmare;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.iridescent.nightmare.commands.AllCommands;
import org.iridescent.nightmare.event.MainListener;

import java.util.Random;

import static org.iridescent.nightmare.Until.rollChance;

public final class IridescentNightmare extends JavaPlugin {

    public final static String PLUGIN_NAME = "Iridescent.Nightmare";
    public final static Random random = new Random();

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new MainListener(), this);
        Bukkit.getPluginCommand("nightmare").setExecutor(new AllCommands());
        this.getLogger().info(PLUGIN_NAME + ": " + ChatColor.DARK_GREEN + "Enable" + ".");
    }

    @Override
    public void onDisable() {
        this.getLogger().info(PLUGIN_NAME + ": " + ChatColor.DARK_RED + "Disable" + ".");
        Bukkit.getScheduler().cancelTasks(this);
    }

    public static Plugin getThisPlugin() {
        return IridescentNightmare.getPlugin(IridescentNightmare.class);
    }

}
