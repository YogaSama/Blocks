/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type.redstone;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.ItemBuilder;
import fr.creatruth.blocks.manager.item.RedstoneItem;
import fr.creatruth.blocks.manager.item.SpecialBase;
import fr.creatruth.blocks.manager.tools.Attributes;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class RedstoneTorchItem extends RedstoneItem implements SpecialBase {

    public RedstoneTorchItem(ItemStack item, Materials materials) {
        super(item, materials);
        this.on = Material.REDSTONE_TORCH_ON;
        this.off = Material.REDSTONE_TORCH_OFF;
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        if (ib.getItem().getType() == off) {
            getItem().setType(on);
            ib.setData((byte) 1);
            updateName();
        }
        else {
            getItem().setType(Material.REDSTONE_ORE);
            ib.setAttributes(new Attributes(ItemBuilder.Type.SPECIAL));
        }
        return this;
    }
}
