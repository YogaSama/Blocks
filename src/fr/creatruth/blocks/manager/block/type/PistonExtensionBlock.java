/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.blocks.BMain;

import fr.creatruth.development.item.ItemBuilder;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockPlaceEvent;

public class PistonExtensionBlock extends BaseBlock {

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);

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
