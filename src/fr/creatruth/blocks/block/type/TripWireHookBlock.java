/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.block.OrientableBlock;
import fr.creatruth.blocks.block.Placeable;
import org.bukkit.Material;

@Deprecated
public class TripWireHookBlock extends OrientableBlock implements Placeable {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
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
