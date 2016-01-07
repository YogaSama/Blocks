/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.blocks.BMain;

import org.bukkit.block.BlockFace;

public class PistonExtensionBlock extends BaseBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        super.onPlace(event);

        BlockFace face = BMain.getData(event.getPlayer()).getLastBlockFace();
        byte data = event.getData() == 0 ? (byte) 0 : 8;

        switch (face) {
            case DOWN:  data += 0;   break;
            case UP:    data += 1;   break;
            case NORTH: data += 2;   break;
            case SOUTH: data += 3;   break;
            case WEST:  data += 4;   break;
            case EAST:  data += 5;   break;
        }

        event.getBlock().setType(event.getMaterial());
        event.getBlock().setData(data);
    }
}
