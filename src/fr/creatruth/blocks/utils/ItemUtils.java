/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ItemUtils {

    public static boolean hasName(ItemStack item) {
        return item != null && item.hasItemMeta() && item.getItemMeta().hasDisplayName();
    }

    public static boolean hasLore(ItemStack item) {
        return item != null && item.hasItemMeta() && item.getItemMeta().hasLore();
    }

    public static String getDisplayName(ItemStack item) {
        return hasName(item) ? item.getItemMeta().getDisplayName() : "";
    }

    public static List<String> getLore(ItemStack item) {
        return hasLore(item) ? item.getItemMeta().getLore() : null;
    }

    public static void setName(ItemStack item, String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        item.setItemMeta(meta);
    }

    public static void setLore(ItemStack item, String... array) {
        ArrayList<String> lore = new ArrayList<>();
        Collections.addAll(lore, array);
        setLore(item, lore);
    }

    public static void setLore(ItemStack item, List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
    }

    public static ItemStack setItem(Material material, String name, String... desc) {
        ItemStack item = new ItemStack(material);
        ItemMeta itemMeta = item.getItemMeta();
        if (name != null) itemMeta.setDisplayName(name);
        if (desc.length > 0) {
            List<String> itemDesc = new ArrayList<>();
            itemDesc.addAll(Arrays.asList(desc));
            itemMeta.setLore(itemDesc);
        }
        item.setItemMeta(itemMeta);
        return item;
    }
}