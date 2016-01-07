/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.development.item.ItemManager;

public class BaseBlock {

    protected ItemManager itemManager() {
        return ItemManager.getInstance();
    }
    public void onPlace(BlocksPlaceEvent event) {
    }

    public void onPick(PickBlockEvent event) {
    }
}
