/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.OrientableBlock;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.BMain;

import org.bukkit.event.block.BlockPlaceEvent;

public class LadderBlock extends OrientableBlock {

    public LadderBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        byte value;

        switch (BMain.getData(player).getLastBlockFace()) {
            case NORTH: value = 2; break;
            case EAST:  value = 5; break;
            case SOUTH: value = 3; break;
            case WEST:  value = 4; break;
            default: {
                switch (getOrientation()) {
                    case 0 : value = 2; break;
                    case 1 : value = 5; break;
                    case 2 : value = 3; break;
                    default: value = 4;
                }
            }
        }

        block.setType(material);
        block.setData(value);
    }
}
