/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.DataBlock;
import fr.creatruth.development.item.ItemManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.inventory.InventoryCreativeEvent;

public class BrewingStandBlock extends DataBlock {

    @Override
    public void onPick(Block target, InventoryCreativeEvent event) {
        super.onPick(target, event);

        if (cursor.getType() == Material.BREWING_STAND_ITEM && target.getType() == Material.BREWING_STAND) {
            event.setCursor(ItemManager.getInstance().getBuilder(cursor.getType(), target.getData()).build());
        }
    }
}
