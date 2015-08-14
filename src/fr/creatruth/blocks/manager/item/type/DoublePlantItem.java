/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.SpecialBase;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class DoublePlantItem extends BaseItem implements SpecialBase {

    private static final byte[] DATA = new byte[]{0, 1, 2, 3, 4, 5};

    public DoublePlantItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA);
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        BaseItem baseItem = specialItemBuilder(Material.STAINED_CLAY, getItemBuilder().getMaterial(), data);
        blockDurability(baseItem);
        return baseItem;
    }

    @Override
    public void onSwitch(Action action) {
        super.onSwitch(action);
        blockDurability(this);
    }

    private void blockDurability(BaseItem baseItem) {
        if (baseItem.getItem().getType() != Material.DOUBLE_PLANT) {
            ItemStack item = baseItem.getItem();

            switch (baseItem.getItemBuilder().getData()) {
                case 0:
                    item.setType(Material.WOOL); // Jaune
                    item.setDurability((short) 4);
                    break;
                case 1:
                    item.setType(Material.STAINED_CLAY); // Rose
                    item.setDurability((short) 2);
                    break;
                case 2:
                    item.setType(Material.WOOL); // Vert clair
                    item.setDurability((short) 5);
                    break;
                case 3:
                    item.setType(Material.STAINED_CLAY); // Vert clair
                    item.setDurability((short) 5);
                    break;
                case 4:
                    item.setType(Material.WOOL); // Rouge
                    item.setDurability((short) 14);
                    break;
                default:
                    item.setType(Material.WOOL); // Rose / Blanc
                    item.setDurability((short) 6);
            }
        }
    }
}
