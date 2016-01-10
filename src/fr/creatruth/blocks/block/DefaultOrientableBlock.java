/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.blocks.BMain;

public class DefaultOrientableBlock extends OrientableBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        super.onPlace(event);

        byte value;

        switch (BMain.getData(event.getPlayer()).getLastBlockFace()) {
            case NORTH: value = 0; break;
            case EAST:  value = 1; break;
            case SOUTH: value = 2; break;
            case WEST:  value = 3; break;
            default:    value = getOrientation(event.getPlayer());
        }

        event.getBlock().setData((byte) ((event.getData() * 4) + value));
    }
}
