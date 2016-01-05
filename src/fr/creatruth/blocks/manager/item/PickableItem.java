/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item;

import fr.creatruth.blocks.manager.utils.BlockUtils;
import org.bukkit.block.Block;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class PickableItem extends BaseItem {

    protected Block block;
    protected ItemStack cursor;

    protected PickableItem(ItemStack item) {
        super(item);
    }

    public void onPick(InventoryCreativeEvent event) {
        block = BlockUtils.getExactlyTargetBlock(event.getWhoClicked(), 5);
        cursor = event.getCursor();
    }
}
