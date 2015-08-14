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

public class TrapDoorBlock extends OrientableBlock {

    public TrapDoorBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        if (baseItem.getItem().getType() != Material.TRAP_DOOR) {

            block.setType(material);
            byte data = 0;

            switch (BMain.getData(player).getLastBlockFace()) {
                case UP:    data = getDataByOrientation();              break;
                case DOWN:  data = (byte) (getDataByOrientation() + 8); break;

                case NORTH: data = ajustData((byte) 0); break;
                case SOUTH: data = ajustData((byte) 1); break;
                case WEST:  data = ajustData((byte) 2); break;
                case EAST:  data = ajustData((byte) 3); break;
            }

            block.setData(data);
        }
    }

    private byte getDataByOrientation()  {
        byte data = 0;
        switch (getOrientation()) {
            case 1:     data = 3;   break;
            case 2:     data = 1;   break;
            case 3:     data = 2;
        }
        byte itemData = baseItem.getItemBuilder().getData();
        return (byte) (data + ((itemData == 1 || itemData == 3) ? 4 :0));
    }

    private byte ajustData(byte data) {
        byte itemData = baseItem.getItemBuilder().getData();
        return (byte) (data + (itemData == 1 ? 4 : itemData == 2 ? 8 : itemData == 3 ? 12 : 0));
    }
}
