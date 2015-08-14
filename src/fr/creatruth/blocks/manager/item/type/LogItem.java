/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.PickableItem;

import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class LogItem extends PickableItem {

    public LogItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == block.getType() && block.getData() >= 12) {
            event.setCursor(getItem(block.getType(), block.getData(), null));
        }
    }
}
