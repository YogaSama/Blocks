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
public class LadderBlock extends OrientableBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        byte value;

        switch (BMain.getData(event.getPlayer()).getLastBlockFace()) {
            case NORTH: value = 2; break;
            case EAST:  value = 5; break;
            case SOUTH: value = 3; break;
            case WEST:  value = 4; break;
            default: {
                switch (getOrientation(event.getPlayer())) {
                    case 0 : value = 2; break;
                    case 1 : value = 5; break;
                    case 2 : value = 3; break;
                    default: value = 4;
                }
            }
        }

        event.getBlock().setType(event.getMaterial());
        event.getBlock().setData(value);
    }
}
