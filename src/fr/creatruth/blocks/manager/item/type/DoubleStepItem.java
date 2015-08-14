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

public class DoubleStepItem extends PickableItem {

    public DoubleStepItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == Material.STEP && block.getType() == Material.DOUBLE_STEP) {
            event.setCursor(getItem(Material.DOUBLE_STEP, block.getData(), null));
        }
        else if (cursor.getType() == Material.WOOD_STEP && block.getType() == Material.WOOD_DOUBLE_STEP) {
            event.setCursor(getItem(Material.WOOD_DOUBLE_STEP, block.getData(), null));
        }
    }
}
