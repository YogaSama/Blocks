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
import org.bukkit.event.block.BlockPlaceEvent;

public class ButtonBlock extends BaseBlock {

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);
        block.setType(material);

        byte data = 0;
        if (this.data == 1) data = 8;
        switch (BMain.getData(player).getLastBlockFace()) {
            case EAST:  data += 1; break;
            case WEST:  data += 2; break;
            case SOUTH: data += 3; break;
            case NORTH: data += 4; break;
        }
        block.setData(data);
    }
}
