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

public class StairItem extends MaterialDataItem {

    private static final MaterialDataList LIST;

    static {
        LIST = new MaterialDataList();
        LIST.add(Materials.WOOD_STAIRS);
        LIST.add(Materials.SPRUCE_WOOD_STAIRS);
        LIST.add(Materials.BIRCH_WOOD_STAIRS);
        LIST.add(Materials.JUNGLE_WOOD_STAIRS);
        LIST.add(Materials.ACACIA_STAIRS);
        LIST.add(Materials.DARK_OAK_STAIRS);
        LIST.add(Materials.BRICK_STAIRS);
        LIST.add(Materials.NETHER_BRICK_STAIRS);
        LIST.add(Materials.COBBLESTONE_STAIRS);
        LIST.add(Materials.SMOOTH_STAIRS);
        LIST.add(Materials.RED_SANDSTONE_STAIRS);
        LIST.add(Materials.SANDSTONE_STAIRS);
        LIST.add(Materials.QUARTZ_STAIRS);
    }

    public StairItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public MaterialDataList getList() {
        return LIST;
    }
}
