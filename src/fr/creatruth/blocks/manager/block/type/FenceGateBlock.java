/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.blocks.manager.block.OrientableBlock;

import org.bukkit.Material;

public class FenceGateBlock extends OrientableBlock {

    private Material material;

    public FenceGateBlock(Material material) {
        this.material = material;
    }

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        event.getBlock().setType(material);
        event.getBlock().setData((byte) (getOrientation(event.getPlayer()) + event.getData()));
    }
}
