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

public class HayItem extends PickableItem {

    private static final byte[] DATA = new byte[]{0, 1, 2, 3, 5, 6, 7, 9, 10, 11, 12, 13, 14, 15};

    public HayItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == block.getType() && !ib.containsData(block.getData()) && block.getData() > 0) {
            event.setCursor(getItem(block.getType(), block.getData(), null));
        }
    }
}
