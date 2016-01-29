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
import fr.creatruth.blocks.block.Pickable;
import fr.creatruth.blocks.block.Placeable;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class FurnaceBlock extends BaseBlock implements Placeable, Pickable {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        Block block = event.getBlock();
        block.setType(event.getMaterial());
        block.setData(block.getData());
    }

    @Override
    public void onPick(PickBlockEvent event) {
        if (event.isCursorType(Material.FURNACE) && event.isTargetType(Material.BURNING_FURNACE)) {
            event.setCursor(itemManager().getBuilder(event.getTarget().getType(), (byte) 0).build());
        }
    }
}
