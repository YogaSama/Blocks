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
import fr.creatruth.development.material.MatData;
import org.bukkit.Material;

public class BrewingStandBlock extends BaseBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        event.getBlock().setData(event.getData());
    }

    @Override
    public void onPick(PickBlockEvent event) {
        if (event.isTargetType(Material.BREWING_STAND)) {
            MatData md = new MatData(event.getCursor().getType(), event.getTarget().getData());
            event.setCursor(itemManager().getBuilder(md).build());
        }
    }
}