/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.PickableItem;

import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class PistonBaseItem extends PickableItem {

    private static final byte[] DATA = new byte[]{0, 6, 7, 14, 15};

    public PistonBaseItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == block.getType()) {
            event.setCursor(getItem(block.getType(), block.getData() < 6 ? (byte) 0 : block.getData(), null));
        }
    }
}
