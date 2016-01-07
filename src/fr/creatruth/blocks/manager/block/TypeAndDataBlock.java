/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

import fr.creatruth.api.event.BlocksPlaceEvent;

public class TypeAndDataBlock extends BaseBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        event.getBlock().setType(event.getMaterial());
        event.getBlock().setData(event.getData());
    }
}

