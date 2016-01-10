/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.blocks.block.BaseBlock;
import fr.creatruth.blocks.BMain;

public class ButtonBlock extends BaseBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        event.getBlock().setType(event.getMaterial());

        byte data = 0;
        if (event.getData() == 1) data = 8;
        switch (BMain.getData(event.getPlayer()).getLastBlockFace()) {
            case EAST:  data += 1; break;
            case WEST:  data += 2; break;
            case SOUTH: data += 3; break;
            case NORTH: data += 4; break;
        }
        event.getBlock().setData(data);
    }
}
