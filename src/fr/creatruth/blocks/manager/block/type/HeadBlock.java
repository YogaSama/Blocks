/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.blocks.manager.item.PickableItem;
import fr.creatruth.blocks.manager.utils.SkullUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class HeadBlock extends BaseBlock {

    @Override
    public void onPick(Block target, InventoryCreativeEvent event) {
        super.onPick(target, event);

        if (cursor.getType() == Material.SKULL_ITEM && block.getType() == Material.SKULL) {
            BlockState state = block.getState();
            if (state instanceof Skull) {
                Skull skull = (Skull) state;
                if (skull.hasOwner())
                    SkullUtils.setHead(block, cursor);
            }
        }
    }
}
