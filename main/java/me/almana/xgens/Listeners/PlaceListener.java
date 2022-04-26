package me.almana.xgens.Listeners;

import me.almana.xgens.XGens;
import net.kyori.adventure.text.Component;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scoreboard.*;

import java.util.List;


public class PlaceListener implements Listener {

    List<Location> gens;
    Player p;
    NamespacedKey maxGens = new NamespacedKey(XGens.getPlugin(), "MGens");
    NamespacedKey gen = new NamespacedKey(XGens.getPlugin(), "gen");
    int maxGensInt = XGens.getPlugin().getConfig().getInt("MAX_GENS");

    @EventHandler
    public void genPlace(BlockPlaceEvent e){

        p = e.getPlayer();
        Material block = e.getBlock().getType();
        ItemStack i = e.getPlayer().getInventory().getItemInMainHand();
        ItemMeta im = i.getItemMeta();
        im.getPersistentDataContainer().set(gen, PersistentDataType.STRING, "1b");


        if (block == Material.HAY_BLOCK || block == Material.GRAY_WOOL || block == Material.COAL_BLOCK || block == Material.IRON_BLOCK || block == Material.GOLD_BLOCK || block == Material.LAPIS_BLOCK || block == Material.REDSTONE_BLOCK || block == Material.QUARTZ_BLOCK || block == Material.DIAMOND_BLOCK || block == Material.NETHER_BRICK || block == Material.NETHERITE_BLOCK || block == Material.BEACON ){

            PersistentDataContainer pdc = e.getPlayer().getPersistentDataContainer();
            int max = pdc.get(maxGens, PersistentDataType.INTEGER);
            if (max > gens.size()){

                gens.add(e.getBlock().getLocation());
                p.sendMessage("Placed a gen.");
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Placed a damn gen."));
            } else {

                e.setCancelled(true);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cToo many gens"));
            }
        }
    }

    @EventHandler
    public void playerJoin(PlayerJoinEvent e){

        if (e.getPlayer().getPersistentDataContainer().has(maxGens, PersistentDataType.INTEGER)) {

            e.getPlayer().getPersistentDataContainer().set(maxGens, PersistentDataType.INTEGER, maxGensInt);
            Component c = Component.text(ChatColor.translateAlternateColorCodes('&', "&aFirst Join. MaxGens: " + e.getPlayer().getPersistentDataContainer().get(maxGens, PersistentDataType.INTEGER) + " &6Present Gens: " + gens.size()));
            e.getPlayer().sendMessage(c);
        } else {

            while (e.getPlayer().isOnline()){

                Bukkit.getScheduler().runTaskTimer(XGens.getPlugin(), new GenRunner(), 20L, 20L);
                Bukkit.getScheduler().runTaskLater(XGens.getPlugin(), () -> scoreboard(e.getPlayer()), 20L);
            }
        }
    }

    public void scoreboard(Player p) {

        ScoreboardManager sm = Bukkit.getScoreboardManager();
        Scoreboard sb = sm.getNewScoreboard();

        Objective objective = sb.registerNewObjective("Title", "dummy", ChatColor.translateAlternateColorCodes('&', "&a3X&bGens"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        Score line6 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&7----------"));
        Score line5 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&bMax Gens&7: &3" + p.getPersistentDataContainer().get(maxGens, PersistentDataType.INTEGER)));
        Score line4 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&bPresent Gens&7: &3" + gens.size()));
        Score line3 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&7----------"));
        Score line2 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&bTPS&7: &3" + Math.round(Bukkit.getTPS()[0])));
        Score line1 = objective.getScore(ChatColor.translateAlternateColorCodes('&', "&bOnline Players&7: &3" + Bukkit.getOnlinePlayers().size()));

        line6.setScore(6);
        line5.setScore(5);
        line4.setScore(4);
        line3.setScore(3);
        line2.setScore(2);
        line1.setScore(1);
    }

    public List<Location> getGens() {
        return gens;
    }
}
