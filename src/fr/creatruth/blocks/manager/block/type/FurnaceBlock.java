/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.development.item.ItemBuilder;
import fr.creatruth.development.item.ItemManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;

public class FurnaceBlock extends BaseBlock {

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);
        byte data = block.getData();
        block.setType(builder.getKey().getMaterial());
        block.setData(data);
    }

    @Override
    public void onPick(Block target, InventoryCreativeEvent event) {
        super.onPick(target, event);

        if (cursor.getType() == Material.FURNACE && target.getType() == Material.BURNING_FURNACE) {
            event.setCursor(ItemManager.getInstance().getBuilder(target.getType(), (byte) 0).build());
        }
    }
}
