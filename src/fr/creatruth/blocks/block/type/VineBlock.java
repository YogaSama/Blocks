/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.blocks.block.OrientableBlock;
import fr.creatruth.blocks.BMain;

@Deprecated
public class VineBlock extends OrientableBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        byte value;

        switch (BMain.getData(event.getPlayer()).getLastBlockFace()) {
            case NORTH: value = 1; break;
            case EAST:  value = 2; break;
            case SOUTH: value = 4; break;
            case WEST:  value = 8; break;
            default:
                value = (byte) (1 << getOrientation(event.getPlayer()));
        }

        event.getBlock().setType(event.getMaterial());
        event.getBlock().setData(value);
    }
}