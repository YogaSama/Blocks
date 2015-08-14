/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.blocks.manager.item.BaseItem;

import org.bukkit.event.block.BlockPlaceEvent;

public class FurnaceBlock extends BaseBlock {

    public FurnaceBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        if (data < 2 || data > 5)
            block.setData(data);
    }
}
