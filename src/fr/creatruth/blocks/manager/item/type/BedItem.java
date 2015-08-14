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
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class BedItem extends NoDataItem implements SpecialBase {

    public BedItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable();
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        BaseItem baseItem = specialItemBuilder(Material.WOOL, Material.BED_BLOCK, data);
        BedBlockItem.ajustItemDurability(baseItem);
        return baseItem;
    }
}
