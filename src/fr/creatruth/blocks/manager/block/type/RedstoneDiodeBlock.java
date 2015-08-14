/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.RedstoneBlock;
import fr.creatruth.blocks.manager.item.BaseItem;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

public class RedstoneDiodeBlock extends RedstoneBlock {

    public RedstoneDiodeBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        if (getBaseItem().getItem().getType() == Material.DIODE) {
            if (data == 1)
                apply(Material.DIODE_BLOCK_ON);
        }
        else {
            if (data == 0)
                block.setType(Material.DIODE_BLOCK_OFF);
            else
                apply(Material.DIODE_BLOCK_ON);

            block.setData(getOrientation(180));
        }
    }
}