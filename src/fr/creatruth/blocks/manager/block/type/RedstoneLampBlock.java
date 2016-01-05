/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.RedstoneBlock;

import fr.creatruth.blocks.runnable.TaskManager;
import fr.creatruth.development.item.ItemBuilder;
import fr.creatruth.development.item.ItemManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;

public class RedstoneLampBlock extends RedstoneBlock {

    @Override
    public void onPick(Block target, InventoryCreativeEvent event) {
        super.onPick(target, event);

        if (cursor.getType() == Material.REDSTONE_LAMP_OFF) {
            event.setCursor(ItemManager.getInstance().getBuilder(Material.REDSTONE_LAMP_OFF, target.getData()).build());
        }
    }

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);

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
