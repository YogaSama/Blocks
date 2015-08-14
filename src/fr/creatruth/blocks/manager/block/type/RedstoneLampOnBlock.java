/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.RedstoneBlock;
import fr.creatruth.blocks.manager.item.BaseItem;

import fr.creatruth.blocks.runnable.TaskManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;

public class RedstoneLampOnBlock extends RedstoneBlock {

    public RedstoneLampOnBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        block.setType(Material.REDSTONE_LAMP_ON);
        block.setData(data);

        TaskManager.runLater(new Runnable() {
            @Override
            public void run() {
                if (block.getType() == Material.REDSTONE_LAMP_ON || block.getType() == Material.REDSTONE_LAMP_OFF) {
                    apply(Material.REDSTONE_LAMP_ON);
                    block.setData(data);
                }
            }
        }, 5L);
    }
}
