/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type.redstone;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.PickableItem;
import fr.creatruth.blocks.manager.item.SpecialBase;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RedstoneWireItem extends PickableItem implements SpecialBase {

    public RedstoneWireItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        return specialItemBuilder(Material.REDSTONE_BLOCK, Material.REDSTONE_WIRE, data);
    }
}
