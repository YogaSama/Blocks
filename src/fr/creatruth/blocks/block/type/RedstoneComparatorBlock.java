/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.blocks.block.RedstoneBlock;
import org.bukkit.Material;

public class RedstoneComparatorBlock extends RedstoneBlock {

    public RedstoneComparatorBlock() {
        this.on = Material.REDSTONE_COMPARATOR_OFF;
        this.off = Material.REDSTONE_COMPARATOR_ON;

        this.classicItem = Material.REDSTONE_COMPARATOR;
    }

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        super.onPlace(event);

        if (event.getMaterial() == classicItem) {
            if (event.getData() == 1) apply(event.getBlock(), off);
        }
        else {
            if (event.getData() == 0) event.getBlock().setType(on);
            else           apply(event.getBlock(), off);

            event.getBlock().setData(getOrientation(event.getPlayer(), 180));
        }
    }
}