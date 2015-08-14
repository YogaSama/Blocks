/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.BMain;

import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockPlaceEvent;

public class PistonExtensionBlock extends BaseBlock {

    public PistonExtensionBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        BlockFace face = BMain.getData(event.getPlayer()).getLastBlockFace();

        data = data == 0 ? (byte) 0 : 8;

        switch (face) {
            case DOWN:  data += 0;   break;
            case UP:    data += 1;   break;
            case NORTH: data += 2;   break;
            case SOUTH: data += 3;   break;
            case WEST:  data += 4;   break;
            case EAST:  data += 5;   break;
        }

        block.setType(material);
        block.setData(data);
    }
}
