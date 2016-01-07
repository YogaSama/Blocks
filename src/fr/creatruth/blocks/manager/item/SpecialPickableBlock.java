/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

@Deprecated
public class SpecialPickableBlock extends PickableItem {

    protected Material hand;
    protected Material target;

    public SpecialPickableBlock(ItemStack item) {
        super(item);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == hand && block.getType() == target) {
            event.setCursor(getItem(hand, block.getData(), null));
        }
    }
}
