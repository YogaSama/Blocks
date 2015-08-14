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

public class PistonBaseBlock extends BaseBlock {

    public PistonBaseBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        if (data == 6 || data == 7 || data == 14 || data == 15)
            block.setData(data);
    }
}
