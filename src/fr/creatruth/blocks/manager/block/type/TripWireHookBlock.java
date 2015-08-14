/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.manager.block.OrientableBlock;
import fr.creatruth.blocks.manager.item.BaseItem;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

public class TripWireHookBlock extends OrientableBlock {

    public TripWireHookBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        if (getBaseItem().getItem().getType() != Material.TRIPWIRE_HOOK) {

            block.setType(material);
            byte data = 0;

            switch (BMain.getData(player).getLastBlockFace()) {
                case UP:
                case DOWN:
                    data = getOrientation(180);
                    break;

                case EAST:  data = (byte) 3; break;
                case WEST:  data = (byte) 1; break;
                case SOUTH: data = (byte) 0; break;
                case NORTH: data = (byte) 2; break;
            }

            block.setData(data);
        }
    }
}