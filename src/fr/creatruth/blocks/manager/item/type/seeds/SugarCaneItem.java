/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type.seeds;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class SugarCaneItem extends SeedItem  {

    public SugarCaneItem(ItemStack item, Materials materials) {
        super(item, materials);
        this.seed = Material.SUGAR_CANE;
        this.material = Material.SUGAR_CANE_BLOCK;
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        BaseItem baseItem = specialItemBuilder(Material.WOOL, Material.SUGAR_CANE_BLOCK, data);
        blockDurability(baseItem);
        return baseItem;
    }

    @Override
    public void onSwitch(Action action) {
        super.onSwitch(action);
        blockDurability(this);
    }

    private void blockDurability(BaseItem baseItem) {
        if (baseItem.getItem().getType() != Material.SUGAR_CANE)
            baseItem.getItem().setDurability((short) 5);

    }
}
