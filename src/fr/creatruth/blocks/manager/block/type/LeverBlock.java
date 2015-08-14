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
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Lever;

public class LeverBlock extends OrientableBlock {

    public LeverBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        if (!(getBaseItem().getItem().getData() instanceof Lever)) {

            block.setType(getBaseItem().getItemBuilder().getMaterial());
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
        else {
            block.setData(ajustData(block.getData()));
        }
    }

    private byte ajustData(byte data) {
        byte itemData = baseItem.getItemBuilder().getData();
        return (byte) (data + (itemData == 1 ? 8 : 0));
    }
}
