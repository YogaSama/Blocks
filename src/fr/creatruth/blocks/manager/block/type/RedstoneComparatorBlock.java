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

public class RedstoneComparatorBlock extends RedstoneBlock {

    public RedstoneComparatorBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        if (getBaseItem().getItem().getType() == Material.REDSTONE_COMPARATOR) {
            if (data == 1)
                apply(Material.REDSTONE_COMPARATOR_ON);
        }
        else {
            if (data == 0)
                block.setType(Material.REDSTONE_COMPARATOR_OFF);
            else
                apply(Material.REDSTONE_COMPARATOR_ON);

            block.setData(getOrientation(180));
        }
    }
}