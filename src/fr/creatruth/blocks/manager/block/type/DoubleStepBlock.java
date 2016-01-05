/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.BaseBlock;

import fr.creatruth.development.item.ItemManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.inventory.InventoryCreativeEvent;

public class DoubleStepBlock extends BaseBlock {

    @Override
    public void onPick(Block target, InventoryCreativeEvent event) {
        super.onPick(target, event);

        if (    is(target, Material.STEP, Material.DOUBLE_STEP) ||
                is(target, Material.WOOD_STEP, Material.WOOD_DOUBLE_STEP) ||
                is(target, Material.STONE_SLAB2, Material.DOUBLE_STONE_SLAB2)    ) {

            event.setCursor(ItemManager.getInstance().getBuilder(target).build());
        }
    }

    private boolean is(Block target, Material step, Material double_step) {
        return cursor.getType() == step && target.getType() == double_step;
    }
}
