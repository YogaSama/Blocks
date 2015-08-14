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
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class LongGrassItem extends DefaultPickableItem implements SpecialBase {

    public LongGrassItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        BaseItem baseItem = specialItemBuilder(Material.LEAVES, Material.LONG_GRASS, data);
        blockDurability(baseItem);
        return baseItem;
    }

    @Override
    public void onSwitch(Action action) {
        super.onSwitch(action);
        blockDurability(this);
    }

    private void blockDurability(BaseItem baseItem) {
        if (baseItem.getItem().getType() != Material.LONG_GRASS) {
            ItemStack item = baseItem.getItem();
            byte durability = baseItem.getItemBuilder().getData();

            switch (baseItem.getItemBuilder().getData()) {
                case 0:
                    item.setType(Material.HARD_CLAY);
                    break;
                case 1:
                case 2:
                    item.setType(Material.LEAVES);
                    break;
                default:
                    item.setType(Material.STAINED_CLAY);
                    item.setDurability((short) 12);
                    return;
            }

            item.setDurability(durability);
        }
    }
}
