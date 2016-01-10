/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block.type;

import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.block.BaseBlock;

import org.bukkit.Material;

public class DoubleStepBlock extends BaseBlock {

    @Override
    public void onPick(PickBlockEvent event) {
        if (    is(event, Material.STEP, Material.DOUBLE_STEP) ||
                is(event, Material.WOOD_STEP, Material.WOOD_DOUBLE_STEP) ||
                is(event, Material.STONE_SLAB2, Material.DOUBLE_STONE_SLAB2)    ) {

            event.setCursor(itemManager().getBuilder(event.getTarget()).build());
        }
    }

    private boolean is(PickBlockEvent event, Material step, Material double_step) {
        return event.isCursorType(step) && event.isTargetType(double_step);
    }
}
