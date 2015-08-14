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

public class PortalBlock extends OrientableBlock {

    public PortalBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        if ((data + 1) % 4 == 0)
            block.setData(data);

        else {
            byte o = getOrientation(90);
            block.setData((byte) ((data) + (o == 3 ? 1 : o)));
        }
    }
}
