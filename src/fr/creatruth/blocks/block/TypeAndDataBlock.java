/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.api.event.PickBlockEvent;

public class TypeAndDataBlock extends BaseBlock {

    @Override
    public void onPick(PickBlockEvent event) {
        if (event.isCursorType(event.getTarget().getType())) {
            event.setCursor(itemManager().getBuilder(event.getTarget()).build());
        }
    }

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        event.getBlock().setType(event.getMaterial());
        event.getBlock().setData(event.getData());
    }
}

