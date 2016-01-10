/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.blocks.block.OrientableBlock;

public class BedBlock extends OrientableBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        byte data = event.getData();
        if (data == 0) data += getOrientation(event.getPlayer());
        else           data += getOrientation(event.getPlayer(), 180);

        event.getBlock().setType(event.getMaterial());
        event.getBlock().setData(data);
    }
}
