/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.manager.block.OrientableBlock;
import fr.creatruth.development.item.ItemBuilder;
import org.bukkit.event.block.BlockPlaceEvent;

public class LeverBlock extends OrientableBlock {

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);

        block.setType(material);
        byte data;
        byte o = getOrientation();

        switch (BMain.getData(player).getLastBlockFace()) {
            case EAST:      data = ajustData((byte) 1);      break;
            case WEST:      data = ajustData((byte) 2);     break;
            case SOUTH:     data = ajustData((byte) 3);     break;
            case NORTH:     data = ajustData((byte) 4);     break;

            case UP: data = ajustData((byte) ((o == 1 || o == 3) ? 6 : 5)); break;
            default: data = ajustData((byte) ((o == 1 || o == 3) ? 0 : 7));
        }

        block.setData(data);
    }

    private byte ajustData(byte data) {
        return (byte) (data + (this.data == 1 ? 8 : 0));
    }
}
