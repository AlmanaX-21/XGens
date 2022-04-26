package me.almana.xgens;

import me.almana.xgens.Listeners.PlaceListener;
import me.almana.xgens.adminCMDS.xgensCMD;
import me.almana.xgens.utils.PlayerInfoUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;
import java.io.IOException;

public final class XGens extends JavaPlugin {

    static XGens plugin;

    @Override
    public void onEnable() {

        plugin = this;
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        try {
            PlayerInfoUtils.loadPlayerInfo();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getServer().getPluginManager().registerEvents(new PlaceListener(), this);
        getServer().getPluginCommand("xgens").setExecutor(new xgensCMD());

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {

            try {
                PlayerInfoUtils.savePlayerInfo();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                PlayerInfoUtils.loadPlayerInfo();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }, 3600L, 1L);
    }

    public static XGens getPlugin() {
        return plugin;
    }
}
