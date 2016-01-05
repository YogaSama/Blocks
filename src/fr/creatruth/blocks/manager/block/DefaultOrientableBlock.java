/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

import fr.creatruth.blocks.BMain;

import fr.creatruth.development.item.ItemBuilder;
import org.bukkit.event.block.BlockPlaceEvent;

public class DefaultOrientableBlock extends OrientableBlock {

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);

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
