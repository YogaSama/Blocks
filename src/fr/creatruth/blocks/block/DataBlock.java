/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block;

import fr.creatruth.api.event.BlocksPlaceEvent;

public class DataBlock extends BaseBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        event.getBlock().setData(event.getData());
    }
}
