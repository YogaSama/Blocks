/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.BMain;

import org.bukkit.event.block.BlockPlaceEvent;

public class DefaultOrientableBlock extends OrientableBlock {

    public DefaultOrientableBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        byte value;

        switch (BMain.getData(player).getLastBlockFace()) {
            case NORTH: value = 0; break;
            case EAST:  value = 1; break;
            case SOUTH: value = 2; break;
            case WEST:  value = 3; break;
            default:    value = getOrientation();
        }

        block.setData((byte) ((data * 4) + value));
    }
}
