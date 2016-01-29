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

@Deprecated
public class RedstoneDiodeBlock extends RedstoneBlock {

    public RedstoneDiodeBlock() {
        this.on = Material.DIODE_BLOCK_OFF;
        this.off = Material.DIODE_BLOCK_ON;

        this.classicItem = Material.DIODE;
    }

    @Override
    public void onPlace(BlocksPlaceEvent event) {
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