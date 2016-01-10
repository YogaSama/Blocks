/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.block.RedstoneBlock;

import fr.creatruth.blocks.runnable.TaskManager;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class RedstoneLampBlock extends RedstoneBlock {

    @Override
    public void onPick(PickBlockEvent event) {
        if (event.isTargetType(Material.REDSTONE_LAMP_ON)) {
            event.setCursor(itemManager().getBuilder(Material.REDSTONE_LAMP_OFF, event.getTarget().getData()).build());
        }
    }

    @Override
    public void onPlace(final BlocksPlaceEvent event) {
        if (event.getMaterial() != Material.REDSTONE_LAMP_ON) return;

        final Block block = event.getBlock();

        block.setType(Material.REDSTONE_LAMP_ON);
        block.setData(event.getData());

        TaskManager.runLater(new Runnable() {
            @Override
            public void run() {
                if (block.getType() == Material.REDSTONE_LAMP_ON || block.getType() == Material.REDSTONE_LAMP_OFF) {
                    apply(block, Material.REDSTONE_LAMP_ON);
                    block.setData(event.getData());
                }
            }
        }, 5L);
    }
}
