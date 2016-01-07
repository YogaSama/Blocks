/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.manager.block.RedstoneBlock;
import org.bukkit.Material;

public class RedstoneTorchBlock extends RedstoneBlock {

    public RedstoneTorchBlock() {
        this.on  = Material.REDSTONE_TORCH_ON;
        this.off = Material.REDSTONE_TORCH_OFF;
    }

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        if (event.getMaterial() == on) {
            if (event.getData() == 1) apply(event.getBlock(), off);
        }
        else {
            if (event.getData() == 0) event.getBlock().setType(on);
            else                      apply(event.getBlock(), off);

            byte data = 0;
            switch (BMain.getData(event.getPlayer()).getLastBlockFace()) {
                case UP:
                case DOWN:  data = (byte) 5; break;

                case EAST:  data = (byte) 1; break;
                case WEST:  data = (byte) 2; break;
                case SOUTH: data = (byte) 3; break;
                case NORTH: data = (byte) 4; break;
            }

            event.getBlock().setData(data);
        }
    }
}
