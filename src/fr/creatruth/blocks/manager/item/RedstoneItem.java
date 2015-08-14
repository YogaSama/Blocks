/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item;

import fr.creatruth.blocks.manager.Materials;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class RedstoneItem extends PickableItem {

    private static final byte[] DATA = new byte[]{0, 1};

    protected Material on;
    protected Material off;
    protected Material classicItem;

    public RedstoneItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == on)
            setCursor(event, on);

        else if (cursor.getType() == classicItem)
            setCursor(event, classicItem);
    }

    private void setCursor(InventoryCreativeEvent event, Material material) {
        if (block.getType() == on)
            event.setCursor(getItem(material, (byte) 0, null));

        else if (block.getType() == off)
            event.setCursor(getItem(material, (byte) 1, null));
    }
}
