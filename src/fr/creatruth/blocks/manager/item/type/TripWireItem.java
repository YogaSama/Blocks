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

public class TripWireItem extends NoDataItem implements SpecialBase {

    public TripWireItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        return specialItemBuilder(Material.STRING, getItemBuilder().getMaterial(), data);
    }
}
