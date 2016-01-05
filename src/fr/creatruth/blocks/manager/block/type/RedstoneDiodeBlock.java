/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.RedstoneBlock;
import fr.creatruth.development.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

public class RedstoneDiodeBlock extends RedstoneBlock {

    public RedstoneDiodeBlock() {
        this.on = Material.DIODE_BLOCK_OFF;
        this.off = Material.DIODE_BLOCK_ON;

        this.classicItem = Material.DIODE;
    }

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);

        if (material == classicItem) {
            if (data == 1) apply(off);
        }
        else {
            if (data == 0) block.setType(on);
            else           apply(off);

            block.setData(getOrientation(180));
        }
    }
}