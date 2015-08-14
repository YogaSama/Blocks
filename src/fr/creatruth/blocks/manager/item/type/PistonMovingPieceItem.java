/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.*;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class PistonMovingPieceItem extends NoDataItem implements SpecialBase {

    public PistonMovingPieceItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == block.getType()) {
            event.setCursor(getItem(block.getType(), block.getData(), ItemBuilder.Type.SPECIAL));
        }
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        return specialItemBuilder(Material.GLASS, Material.PISTON_MOVING_PIECE, data);
    }
}
