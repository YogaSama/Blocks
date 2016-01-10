/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class SkullUtils {

    public static ItemStack setHead(Block block, ItemStack item) {
        return getHead(((Skull) block.getState()).getOwner(), item);
    }

    public static ItemStack getHead(String playerName) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
        return getHead(playerName, item);
    }

    public static ItemStack getHead(String playerName, ItemStack item) {
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(playerName);
        meta.setDisplayName("§4Pseudo: §c" + playerName);
        item.setItemMeta(meta);
        return item;
    }
}
