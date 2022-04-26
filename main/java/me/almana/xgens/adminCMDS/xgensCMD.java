package me.almana.xgens.adminCMDS;

import me.almana.xgens.XGens;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class xgensCMD implements TabExecutor {

    Plugin plugin = XGens.getPlugin();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {

            Player p = (Player) sender;
            if (p.hasPermission("xgens.setmax")) {

                if (args.length > 0) {

                    if (args[0].equalsIgnoreCase("maxgens")) {

                        if (args.length > 1) {

                            int maxgens = Integer.parseInt(args[1]);
                            plugin.getConfig().set("MAX_GENS", maxgens);
                        }
                    }
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {

        if (args.length > 0) {

            if (args.length == 1) {

                List<String> subCommands = new ArrayList<>();
                subCommands.add("maxgens");

                return subCommands;
            }
        }

        return null;
    }
}
