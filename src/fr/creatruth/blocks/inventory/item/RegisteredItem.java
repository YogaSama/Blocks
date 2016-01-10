/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.inventory.item;

import fr.creatruth.blocks.utils.ItemUtils;
import fr.creatruth.development.item.ItemEncoder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RegisteredItem {

    private static ItemStack previous;
    private static ItemStack next;
    private static ItemStack close;

    public static ItemStack getPrevious() {
        if (previous == null) {
            previous = ItemUtils.setItem(Material.GHAST_TEAR, "§c◄◄ §7Page précédente");
            ItemEncoder.encodeInName(previous, ItemType.PREVIOUS_PAGE);
        }
        return previous;
    }

    public static ItemStack getNext() {
        if (next == null) {
            next = ItemUtils.setItem(Material.GOLD_NUGGET, "§7Page Suivante §a►►");
            ItemEncoder.encodeInName(next, ItemType.NEXT_PAGE);
        }
        return next;
    }

    public static ItemStack getClose() {
        if (close == null) {
            close = ItemUtils.setItem(Material.IRON_DOOR, "§4◆ §4§lFermer §4◆");
            ItemEncoder.encodeInName(close, ItemType.CLOSE);
        }
        return close;
    }
}
