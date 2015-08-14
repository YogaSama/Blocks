/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.OrientableBlock;
import fr.creatruth.blocks.manager.item.BaseItem;

import org.bukkit.event.block.BlockPlaceEvent;

public class FenceGateBlock extends OrientableBlock {

    public FenceGateBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        if (getBaseItem().getItem().getType() != getBaseItem().getItemBuilder().getMaterial()) {
            super.onPlace(event);

            block.setType(getBaseItem().getItemBuilder().getMaterial());
            block.setData((byte) (getOrientation() + (data * 4)));
        }
    }
}
