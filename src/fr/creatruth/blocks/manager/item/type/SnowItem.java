/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.DefaultPickableItem;
import fr.creatruth.blocks.manager.item.SpecialBase;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class SnowItem extends DefaultPickableItem implements SpecialBase {

    private static final byte[] DATA = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};

    public SnowItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA);
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        return specialItemBuilder(Material.SNOW_BLOCK, getItemBuilder().getMaterial(), data);
    }
}
