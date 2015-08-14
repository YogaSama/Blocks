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
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class BedBlockItem extends OrientableItem implements SpecialBase {

    public BedBlockItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA);
    }

    @Override
    public void onSwitch(Action action) {
        super.onSwitch(action);
        ajustItemDurability(this);
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        BaseItem baseItem = specialItemBuilder(Material.WOOL, Material.BED_BLOCK, data);
        ajustItemDurability(baseItem);
        return baseItem;
    }

    static void ajustItemDurability(BaseItem bedItem) {
        if (bedItem.getItemBuilder().getData() < 2)
            bedItem.getItem().setDurability((short) 14);
        else
            bedItem.getItem().setDurability((short) 0);
    }
}
