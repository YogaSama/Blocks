/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

import fr.creatruth.blocks.manager.item.BaseItem;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

public class DefaultRedstoneBlock extends RedstoneBlock {

    public DefaultRedstoneBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        apply(Material.REDSTONE_WIRE);
        block.setData(data);
    }
}
