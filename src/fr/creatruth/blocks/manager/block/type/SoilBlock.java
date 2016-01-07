/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.manager.block.TypeAndDataBlock;

public class SoilBlock extends TypeAndDataBlock {

    @Override
    public void onPick(PickBlockEvent event) {
        super.onPick(event);
        event.setCursor(itemManager().getBuilder(event.getTarget()).build());
    }
}
