package org.iridescent.nightmare.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.iridescent.nightmare.tables.SpecialMobType;
import org.jetbrains.annotations.NotNull;

public class AllCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player) {
            if (command.getName().equals("nightmare")) {
                Player player = (Player) sender;
                Location playerLoc = player.getLocation();
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.RED + "Invalid.");
                    return true;
                } else {
                    if (args[0].equals("summon")) {
                        String mobRegName = args[1];
                        boolean summoned = false;
                        for (SpecialMobType mobType: SpecialMobType.values()) {
                            if (mobType.getRegName().equals(mobRegName)) {
                                mobType.spawnEntity(playerLoc);
                                summoned = true;
                            }
                        }
                        if (summoned) sender.sendMessage(ChatColor.GREEN + "Entity summoned!");
                        else sender.sendMessage(ChatColor.RED + "Invalid entity name.");
                    } else {
                        sender.sendMessage(ChatColor.RED + "Invalid operation.");
                    }
                }
                return true;
            }

        }
        return false;
    }
}
