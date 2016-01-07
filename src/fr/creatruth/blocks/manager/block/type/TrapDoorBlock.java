/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.manager.block.OrientableBlock;
import org.bukkit.entity.Player;

public class TrapDoorBlock extends OrientableBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        /*block.setType(material);
        byte value = 0;

        Player player = event.getPlayer();

        switch (BMain.getData(player).getLastBlockFace()) {
            case UP:    data = getDataByOrientation(player, event.getData());              break;
            case DOWN:  data = (byte) (getDataByOrientation(player, event.getData()) + 8); break;

            case NORTH: data = ajustData((byte) 0); break;
            case SOUTH: data = ajustData((byte) 1); break;
            case WEST:  data = ajustData((byte) 2); break;
            case EAST:  data = ajustData((byte) 3); break;
        }

        block.setData(data);*/
    }

    private byte getDataByOrientation(Player player, byte data)  {
        return (byte) (getOrientation(player, 180) + ((data == 1 || data == 3) ? 4 :0));
    }

    private byte ajustData(byte data, byte value) {
        return (byte) (value + (data == 1 ? 4 : data == 2 ? 8 : data == 3 ? 12 : 0));
    }

    @Override
    public void onPick(PickBlockEvent event) {
        /*if (event.isCursorType(event.getTarget().getType())) {
            byte data = (byte) (event.getTarget().getData() >> 4);

            event.setCursor(itemManager().getBuilder(event.getTarget().getType(), data).build());

            if (data < 4)
                event.setCursor(getItem(block.getType(), (byte) 0, null)); // Ouvert bas
            else if (data < 8)
                event.setCursor(getItem(block.getType(), (byte) 1, null)); // Ouvert haut
            else if (data < 12)
                event.setCursor(getItem(block.getType(), (byte) 2, null)); // Fermé bas
            else
                event.setCursor(getItem(block.getType(), (byte) 3, null)); // Fermé haut
        }*/
    }
}
