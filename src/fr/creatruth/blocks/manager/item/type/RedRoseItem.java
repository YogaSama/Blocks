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

public class RedRoseItem extends DefaultPickableItem implements SpecialBase {

    public RedRoseItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        BaseItem baseItem = specialItemBuilder(Material.STAINED_CLAY, Material.RED_ROSE, data);
        blockDurability(baseItem);
        return baseItem;
    }

    @Override
    public void onSwitch(Action action) {
        super.onSwitch(action);
        blockDurability(this);
    }

    private void blockDurability(BaseItem baseItem) {
        if (baseItem.getItem().getType() != Material.RED_ROSE) {
            ItemStack item = baseItem.getItem();

            switch (baseItem.getItemBuilder().getData()) {
                case 1:
                    item.setType(Material.WOOL);
                    item.setDurability((short) 3);
                    break;
                case 2:
                    item.setType(Material.WOOL);
                    item.setDurability((short) 2);
                    break;
                case 3:
                    item.setType(Material.SNOW_BLOCK);
                    item.setDurability((short) 0);
                    break;
                case 4:
                    item.setType(Material.STAINED_CLAY);
                    item.setDurability((short) 6);
                    break;
                case 5:
                    item.setType(Material.STAINED_CLAY);
                    item.setDurability((short) 1);
                    break;
                case 6:
                    item.setType(Material.WOOL);
                    item.setDurability((short) 0);
                    break;
                case 7:
                    item.setType(Material.WOOL);
                    item.setDurability((short) 6);
                    break;
                case 8:
                    item.setType(Material.STAINED_CLAY);
                    item.setDurability((short) 0);
                    break;
                default:
                    item.setType(Material.STAINED_CLAY);
                    item.setDurability((short) 14);
            }
        }
    }
}
