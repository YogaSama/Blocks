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

public class DispenserItem extends PickableItem {

    private static final byte[] DATA = new byte[]{0, 1};

    public DispenserItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA); // 0 = Normal, 1 = Plein
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == block.getType()) {
            event.setCursor(getItem(block.getType(), block.getData() > 5 ? (byte) 1 : 0, null));
        }
    }
}
