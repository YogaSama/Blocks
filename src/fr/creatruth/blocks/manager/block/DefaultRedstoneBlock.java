/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

import fr.creatruth.api.event.BlocksPlaceEvent;
import org.bukkit.Material;

public class DefaultRedstoneBlock extends RedstoneBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        apply(event.getBlock(), Material.REDSTONE_WIRE);
        event.getBlock().setData(event.getData());
    }
}
