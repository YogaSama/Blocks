/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.item.OrientableItem;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class CocoaItem extends OrientableItem {

    public CocoaItem(ItemStack item) {
        super(item);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == Material.INK_SACK && cursor.getDurability() == 3) {
            if (block.getType() == Material.COCOA) {
                event.setCursor(getItem(Material.COCOA, getOrientation(), null));
            }
        }
    }
}
