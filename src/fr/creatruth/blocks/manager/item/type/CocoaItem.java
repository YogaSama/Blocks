/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.OrientableItem;

import fr.creatruth.blocks.manager.item.SpecialBase;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class CocoaItem extends OrientableItem implements SpecialBase {

    public CocoaItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == Material.INK_SACK && cursor.getDurability() == 3) {
            if (block.getType() == Material.COCOA) {
                event.setCursor(getItem(Material.COCOA, getOrientation(), null));
            }
        }
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        return specialItemBuilder(Material.COCOA, Material.COCOA, data);
    }
}
