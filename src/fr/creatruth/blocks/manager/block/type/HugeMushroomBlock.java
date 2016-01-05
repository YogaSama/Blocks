/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.TypeAndDataBlock;
import fr.creatruth.development.item.ItemManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.inventory.InventoryCreativeEvent;

public class HugeMushroomBlock extends TypeAndDataBlock {

    private Material material;

    public HugeMushroomBlock(Material material) {
        this.material = material;
    }

    @Override
    public void onPick(Block target, InventoryCreativeEvent event) {
        super.onPick(target, event);

        if (target.getType() == material) {
            event.setCursor(ItemManager.getInstance().getBuilder(target).build());
        }
    }
}