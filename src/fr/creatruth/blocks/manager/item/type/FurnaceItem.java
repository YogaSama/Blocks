/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.PickableItem;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class FurnaceItem extends PickableItem {

    public FurnaceItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == Material.FURNACE && (block.getType() == Material.FURNACE || block.getType() == Material.BURNING_FURNACE)) {
            event.setCursor(getItem(block.getType(), block.getData(), null));
        }
    }
}
