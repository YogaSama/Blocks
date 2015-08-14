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
import org.bukkit.inventory.ItemStack;

public class RedstoneDiodeItem extends RedstoneItem implements SpecialBase {

    public RedstoneDiodeItem(ItemStack item, Materials materials) {
        super(item, materials);
        this.on = Material.DIODE_BLOCK_OFF;
        this.off = Material.DIODE_BLOCK_ON;

        this.classicItem = Material.DIODE;
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
        baseItem.getItem().setDurability((short) 0);
    }
}