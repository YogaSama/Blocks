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

public class LogBlock extends BaseBlock {

    public LogBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        if (12 <= data && data <= 15)
            block.setData(data);
    }
}
