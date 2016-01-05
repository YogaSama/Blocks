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

public class SeedBlock extends BaseBlock {

    protected Material seed;
    protected Material material;

    public SeedBlock(Material seed, Material material) {
        this.seed = seed;
        this.material = material;
    }

    @Override
    public void onPick(Block target, InventoryCreativeEvent event) {
        super.onPick(target, event);

        if (cursor.getType() == seed && target.getType() == material) {
            event.setCursor(ItemManager.getInstance().getBuilder(seed, target.getData()).build());
        }
    }

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);

        block.setType(material);
        block.setData(data);
    }
}
