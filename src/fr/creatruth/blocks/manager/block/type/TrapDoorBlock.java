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
import fr.creatruth.development.item.ItemManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;

public class TrapDoorBlock extends OrientableBlock {

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);

        if (material != Material.TRAP_DOOR) {

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
            case 1:
                data = 3;
                break;
            case 2:
                data = 1;
                break;
            case 3:
                data = 2;
        }
        return (byte) (data + ((this.data == 1 || this.data == 3) ? 4 :0));
    }

    private byte ajustData(byte data) {
        return (byte) (data + (this.data == 1 ? 4 : this.data == 2 ? 8 : this.data == 3 ? 12 : 0));
    }

    @Override
    public void onPick(Block target, InventoryCreativeEvent event) {
        super.onPick(target, event);

        if (cursor.getType() == target.getType()) {
            byte data = (byte) Math.floor(target.getData() / 4D);

            event.setCursor(ItemManager.getInstance().getBuilder(target.getType(), data).build());

            /*if (data < 4)
                event.setCursor(getItem(block.getType(), (byte) 0, null)); // Ouvert bas
            else if (data < 8)
                event.setCursor(getItem(block.getType(), (byte) 1, null)); // Ouvert haut
            else if (data < 12)
                event.setCursor(getItem(block.getType(), (byte) 2, null)); // Fermé bas
            else
                event.setCursor(getItem(block.getType(), (byte) 3, null)); // Fermé haut*/
        }
    }
}
