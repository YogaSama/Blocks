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

public class RedMushroomItem extends DefaultPickableItem implements SpecialBase {

    public RedMushroomItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        return specialItemBuilder(Material.HUGE_MUSHROOM_2, Material.RED_MUSHROOM, data);
    }
}
