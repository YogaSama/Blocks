/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.manager.block.RedstoneBlock;
import fr.creatruth.development.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

public class RedstoneTorchBlock extends RedstoneBlock {

    public RedstoneTorchBlock() {
        this.on  = Material.REDSTONE_TORCH_ON;
        this.off = Material.REDSTONE_TORCH_OFF;
    }

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);

        if (material == on) {
            if (data == 1) apply(off);
        }
        else {
            if (data == 0) block.setType(on);
            else           apply(off);

            byte data = 0;
            switch (BMain.getData(player).getLastBlockFace()) {
                case UP:
                case DOWN:  data = (byte) 5; break;

                case EAST:  data = (byte) 1; break;
                case WEST:  data = (byte) 2; break;
                case SOUTH: data = (byte) 3; break;
                case NORTH: data = (byte) 4; break;
            }

            block.setData(data);
        }
    }
}
