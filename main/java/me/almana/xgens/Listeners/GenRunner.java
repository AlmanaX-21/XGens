package me.almana.xgens.Listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;

public class GenRunner extends BukkitRunnable {

    PlaceListener pl = new PlaceListener();
    List<Location> gens = pl.getGens();

    @Override
    public void run() {

        ItemStack wheat = new ItemStack(Material.WHEAT);
        ItemMeta wm = wheat.getItemMeta();
        wm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Wheat"));
        wheat.setItemMeta(wm);

        ItemStack pebble = new ItemStack(Material.GRAY_DYE);
        ItemMeta pm = pebble.getItemMeta();
        pm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Pebble"));
        pebble.setItemMeta(pm);

        ItemStack coal = new ItemStack(Material.COAL);
        ItemMeta cm = coal.getItemMeta();
        cm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Coal"));
        coal.setItemMeta(cm);

        ItemStack iron = new ItemStack(Material.IRON_INGOT);
        ItemMeta im = iron.getItemMeta();
        cm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Iron"));
        iron.setItemMeta(im);

        ItemStack gold = new ItemStack(Material.GOLD_INGOT);
        ItemMeta gm = gold.getItemMeta();
        gm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Gold"));
        gold.setItemMeta(gm);

        ItemStack lapis = new ItemStack(Material.LAPIS_LAZULI);
        ItemMeta lm = lapis.getItemMeta();
        lm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Lapis Lazuli"));
        lapis.setItemMeta(lm);

        ItemStack redstone = new ItemStack(Material.REDSTONE);
        ItemMeta rm = redstone.getItemMeta();
        rm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Redstone Dust"));
        redstone.setItemMeta(rm);

        ItemStack quartz = new ItemStack(Material.QUARTZ);
        ItemMeta qm = quartz.getItemMeta();
        qm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Quartz"));
        quartz.setItemMeta(qm);

        ItemStack diamond = new ItemStack(Material.DIAMOND);
        ItemMeta dm = diamond.getItemMeta();
        dm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Diamond"));
        diamond.setItemMeta(dm);

        ItemStack netherBrick = new ItemStack(Material.NETHER_BRICK);
        ItemMeta nbm = netherBrick.getItemMeta();
        nbm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Raw Netherite Ingot"));
        netherBrick.setItemMeta(nbm);

        ItemStack netheriteIngot = new ItemStack(Material.NETHERITE_INGOT);
        ItemMeta nm = netheriteIngot.getItemMeta();
        nm.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Perfect Netherite Ingot"));
        netheriteIngot.setItemMeta(nm);

        ItemStack iron1 = new ItemStack(Material.IRON_INGOT);
        ItemMeta im1 = iron1.getItemMeta();
        im1.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Crystal Ingot"));
        im1.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
        im1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        iron.setItemMeta(im);

        for (Location gen: gens) {

            switch (gen.getBlock().getType()) {

                case HAY_BLOCK:
                    Bukkit.getWorld(gen.getWorld().getName()).dropItem(gen.add(0, 1, 0), wheat);
                case GRAY_WOOL:
                    Bukkit.getWorld(gen.getWorld().getName()).dropItem(gen.add(0, 1, 0), pebble);
                case COAL_BLOCK:
                    Bukkit.getWorld(gen.getWorld().getName()).dropItem(gen.add(0, 1, 0), coal);
                case IRON_BLOCK:
                    Bukkit.getWorld(gen.getWorld().getName()).dropItem(gen.add(0, 1, 0), iron);
                case GOLD_BLOCK:
                    Bukkit.getWorld(gen.getWorld().getName()).dropItem(gen.add(0, 1, 0), gold);
                case LAPIS_BLOCK:
                    Bukkit.getWorld(gen.getWorld().getName()).dropItem(gen.add(0, 1, 0), lapis);
                case REDSTONE_BLOCK:
                    Bukkit.getWorld(gen.getWorld().getName()).dropItem(gen.add(0, 1, 0), redstone);
                case QUARTZ_BLOCK:
                    Bukkit.getWorld(gen.getWorld().getName()).dropItem(gen.add(0, 1, 0), quartz);
                case DIAMOND_BLOCK:
                    Bukkit.getWorld(gen.getWorld().getName()).dropItem(gen.add(0, 1, 0), diamond);
                case NETHER_BRICK:
                    Bukkit.getWorld(gen.getWorld().getName()).dropItem(gen.add(0, 1, 0), netherBrick);
                case NETHERITE_BLOCK:
                    Bukkit.getWorld(gen.getWorld().getName()).dropItem(gen.add(0, 1, 0), netheriteIngot);
                case BEACON:
                    Bukkit.getWorld(gen.getWorld().getName()).dropItem(gen.add(0, 1, 0), iron1);
            }
        }
    }
}
