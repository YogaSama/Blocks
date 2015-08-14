/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.manager.block.RedstoneBlock;
import fr.creatruth.blocks.manager.item.BaseItem;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

public class RedstoneTorchBlock extends RedstoneBlock {

    public RedstoneTorchBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        if (getBaseItem().getItem().getType() == Material.REDSTONE_TORCH_ON) {
            if (data == 1)
                apply(Material.REDSTONE_TORCH_OFF);
        }
        else {
            if (data == 0)
                block.setType(Material.REDSTONE_TORCH_ON);
            else
                apply(Material.REDSTONE_TORCH_OFF);
            byte data = 0;

            switch (BMain.getData(player).getLastBlockFace()) {
                case UP:
                case DOWN:  data = (byte) 5;    break;

                case EAST:  data = (byte) 1; break;
                case WEST:  data = (byte) 2; break;
                case SOUTH: data = (byte) 3; break;
                case NORTH: data = (byte) 4; break;
            }

            block.setData(data);
        }
    }
}
