/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

import fr.creatruth.development.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

public class DefaultRedstoneBlock extends RedstoneBlock {

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);

        apply(Material.REDSTONE_WIRE);
        block.setData(data);
    }
}
