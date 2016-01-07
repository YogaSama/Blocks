/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item;

import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

@Deprecated
public class DefaultOrientableItem extends PickableItem {

    public DefaultOrientableItem(ItemStack item) {
        super(item);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == block.getType()) {
            event.setCursor(getItem(cursor.getType(), (byte) (block.getData() / 4), null));
        }
    }
}