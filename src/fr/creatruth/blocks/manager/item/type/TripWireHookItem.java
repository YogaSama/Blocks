/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.NoDataItem;
import fr.creatruth.blocks.manager.item.SpecialBase;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class TripWireHookItem extends NoDataItem implements SpecialBase {

    public TripWireHookItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        BaseItem baseItem = specialItemBuilder(Material.SMOOTH_BRICK, getItemBuilder().getMaterial(), data);
        blockDurability(baseItem);
        return baseItem;
    }

    private void blockDurability(BaseItem baseItem) {
        baseItem.getItem().setDurability((short) 3);
    }
}
