/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.block.BaseBlock;

import org.bukkit.event.block.BlockPlaceEvent;

public class HayBlock extends BaseBlock {

    public HayBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        if (data != 0)
            block.setData(data);
    }
}
