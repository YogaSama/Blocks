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

public class OreBlockItem extends MaterialDataItem {

    private static final MaterialDataList LIST;

    static {
        LIST = new MaterialDataList();
        LIST.add(Materials.EMERALD_ORE);
        LIST.add(Materials.REDSTONE_ORE);
        LIST.add(Materials.LAPIS_ORE);
        LIST.add(Materials.COAL_ORE);
        LIST.add(Materials.IRON_ORE);
        LIST.add(Materials.GOLD_ORE);
        LIST.add(Materials.DIAMOND_ORE);
    }

    public OreBlockItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public MaterialDataList getList() {
        return LIST;
    }
}