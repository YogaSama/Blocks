/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.OrientableBlock;
import fr.creatruth.blocks.BMain;
import fr.creatruth.development.item.ItemBuilder;
import org.bukkit.event.block.BlockPlaceEvent;

public class VineBlock extends OrientableBlock {

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);

        byte value;

        switch (BMain.getData(player).getLastBlockFace()) {
            case NORTH: value = 1; break;
            case EAST:  value = 2; break;
            case SOUTH: value = 4; break;
            case WEST:  value = 8; break;
            default: {
                switch (getOrientation()) {
                    case 0 : value = 1; break;
                    case 1 : value = 2; break;
                    case 2 : value = 4; break;
                    default: value = 8;

                }
            }
        }

        block.setType(material);
        block.setData(value);
    }
}