/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.block.OrientableBlock;

public class LeverBlock extends OrientableBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        event.getBlock().setType(event.getMaterial());
        byte o = getOrientation(event.getPlayer());

        byte data, eventData = event.getData();
        switch (BMain.getData(event.getPlayer()).getLastBlockFace()) {
            case EAST:      data = ajustData(eventData, (byte) 1);     break;
            case WEST:      data = ajustData(eventData, (byte) 2);     break;
            case SOUTH:     data = ajustData(eventData, (byte) 3);     break;
            case NORTH:     data = ajustData(eventData, (byte) 4);     break;

            case UP: data = ajustData(eventData, (byte) ((o == 1 || o == 3) ? 6 : 5)); break;
            default: data = ajustData(eventData, (byte) ((o == 1 || o == 3) ? 0 : 7));
        }

        event.getBlock().setData(data);
    }

    private byte ajustData(byte eventData, byte data) {
        return (byte) (data + (eventData == 1 ? 8 : 0));
    }
}
