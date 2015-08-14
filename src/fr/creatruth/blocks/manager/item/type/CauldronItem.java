/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.SpecialPickableItem;
import fr.creatruth.blocks.manager.item.SpecialBase;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class CauldronItem extends SpecialPickableItem implements SpecialBase {

    public CauldronItem(ItemStack item, Materials materials) {
        super(item, materials);
        this.hand = Material.CAULDRON_ITEM;
        this.target = Material.CAULDRON;
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        return specialItemBuilder(hand, target, data);
    }
}
