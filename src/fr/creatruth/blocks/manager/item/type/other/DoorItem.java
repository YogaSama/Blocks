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

public class DoorItem extends MaterialDataItem {

    private static final MaterialDataList LIST;

    static {
        LIST = new MaterialDataList();
        LIST.add(Materials.WOOD_DOOR);
        LIST.add(Materials.IRON_DOOR);
        LIST.add(Materials.SPRUCE_DOOR_ITEM);
        LIST.add(Materials.BIRCH_DOOR_ITEM);
        LIST.add(Materials.JUNGLE_DOOR_ITEM);
        LIST.add(Materials.ACACIA_DOOR_ITEM);
        LIST.add(Materials.DARK_OAK_DOOR_ITEM);
    }

    public DoorItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public MaterialDataList getList() {
        return LIST;
    }
}
