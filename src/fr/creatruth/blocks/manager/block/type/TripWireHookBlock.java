/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.manager.block.OrientableBlock;
import org.bukkit.Material;

public class TripWireHookBlock extends OrientableBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        super.onPlace(event);

        if (event.getMaterial() != Material.TRIPWIRE_HOOK) {

            event.getBlock().setType(event.getMaterial());
            byte data = 0;

            switch (BMain.getData(event.getPlayer()).getLastBlockFace()) {
                case UP:
                case DOWN:
                    data = getOrientation(event.getPlayer(), 180);
                    break;

                case EAST:  data = (byte) 3; break;
                case WEST:  data = (byte) 1; break;
                case SOUTH: data = (byte) 0; break;
                case NORTH: data = (byte) 2; break;
            }

            event.getBlock().setData(data);
        }
    }
}
