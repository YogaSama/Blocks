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

public class SaplingItem extends DefaultPickableItem implements SpecialBase {

    public SaplingItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        BaseItem baseItem = specialItemBuilder(Material.LOG, getItemBuilder().getMaterial(), data);
        blockDurability(baseItem);
        return baseItem;
    }

    @Override
    public void onSwitch(Action action) {
        super.onSwitch(action);
        blockDurability(this);
    }

    private void blockDurability(BaseItem baseItem) {
        if (baseItem.getItem().getType() != Material.SAPLING) {
            ItemStack item = baseItem.getItem();

            switch (baseItem.getItemBuilder().getData()) {
                case 0:
                case 8:
                    item.setType(Material.LOG);
                    item.setDurability((short) 0);
                    break;
                case 1:
                case 9:
                    item.setType(Material.LOG);
                    item.setDurability((short) 1);
                    break;
                case 2:
                case 10:
                    item.setType(Material.LOG);
                    item.setDurability((short) 2);
                    break;
                case 3:
                case 11:
                    item.setType(Material.LOG);
                    item.setDurability((short) 3);
                    break;
                case 4:
                case 12:
                    item.setType(Material.LOG_2);
                    item.setDurability((short) 0);
                    break;
                default:
                    item.setType(Material.LOG_2);
                    item.setDurability((short) 1);
            }
        }
    }
}
