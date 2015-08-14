/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item;

import fr.creatruth.blocks.manager.Materials;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class SpecialPickableItem extends PickableItem {

    protected Material hand;
    protected Material target;

    public SpecialPickableItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == hand && block.getType() == target) {
            event.setCursor(getItem(hand, block.getData(), null));
        }
    }
}
