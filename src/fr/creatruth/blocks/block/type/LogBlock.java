/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.block.BaseBlock;
import fr.creatruth.blocks.block.Pickable;
import fr.creatruth.blocks.block.Placeable;

public class LogBlock extends BaseBlock implements Placeable, Pickable {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        if (12 <= event.getData() && event.getData() <= 15)
            event.getBlock().setData(event.getData());
    }

    @Override
    public void onPick(PickBlockEvent event) {
        if (event.getTarget().getData() >= 12) {
            event.setCursor(itemManager().getBuilder(event.getTarget()).build());
        }
    }
}
