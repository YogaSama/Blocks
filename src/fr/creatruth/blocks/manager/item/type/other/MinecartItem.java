/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type.other;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.MaterialDataItem;

import fr.creatruth.blocks.manager.tools.MaterialDataList;
import org.bukkit.inventory.ItemStack;

public class MinecartItem extends MaterialDataItem {

    private static final MaterialDataList LIST;

    static {
        LIST = new MaterialDataList();
        LIST.add(Materials.MINECART);
        LIST.add(Materials.STORAGE_MINECART);
        LIST.add(Materials.POWERED_MINECART);
        LIST.add(Materials.EXPLOSIVE_MINECART);
        LIST.add(Materials.HOPPER_MINECART);
        LIST.add(Materials.COMMAND_MINECART);
    }

    public MinecartItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public MaterialDataList getList() {
        return LIST;
    }
}
