/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type.redstone;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.RedstoneItem;
import fr.creatruth.blocks.manager.item.SpecialBase;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class RedstoneComparatorItem extends RedstoneItem implements SpecialBase {

    public RedstoneComparatorItem(ItemStack item, Materials materials) {
        super(item, materials);
        this.on = Material.REDSTONE_COMPARATOR_OFF;
        this.off = Material.REDSTONE_COMPARATOR_ON;

        this.classicItem = Material.REDSTONE_COMPARATOR;
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        Material material = getItemBuilder().getMaterial();
        data = (byte) (material == classicItem ? data : material == on ? 0 : 1);
        BaseItem baseItem = specialItemBuilder(Material.STEP, classicItem, data);
        blockDurability(baseItem);
        return baseItem;
    }

    @Override
    public void onSwitch(Action action) {
        super.onSwitch(action);
        blockDurability(this);
    }

    private void blockDurability(BaseItem baseItem) {
        baseItem.getItem().setDurability((short) 7);
    }
}

