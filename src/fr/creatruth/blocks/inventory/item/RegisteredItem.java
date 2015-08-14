/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.inventory.item;

import fr.creatruth.blocks.manager.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class RegisteredItem {

    private static ItemStack previous;
    private static ItemStack next;
    private static ItemStack close;

    public static ItemStack getPrevious() {
        if (previous == null) {
            String name = CustomItem.encodeInName("§c◄◄ §7Page précédente", ItemType.PREVIOUS_PAGE);
            previous = ItemUtils.setItem(Material.GHAST_TEAR, name);
        }
        return previous;
    }

    public static ItemStack getNext() {
        if (next == null) {
            String name = CustomItem.encodeInName("§7Page Suivante §a►►", ItemType.NEXT_PAGE);
            next = ItemUtils.setItem(Material.GOLD_NUGGET, name);
        }
        return next;
    }

    public static ItemStack getClose() {
        if (close == null) {
            String name = CustomItem.encodeInName("§4◆ §4§lFermer §4◆", ItemType.CLOSE);
            close = ItemUtils.setItem(Material.IRON_DOOR, name);
        }
        return close;
    }
}
