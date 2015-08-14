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

public class OreItem extends MaterialDataItem {

    private static final MaterialDataList LIST;

    static {
        LIST = new MaterialDataList();
        LIST.add(Materials.COAL);
        LIST.add(Materials.IRON_INGOT);
        LIST.add(Materials.GOLD_INGOT);
        LIST.add(Materials.DIAMOND);
        LIST.add(Materials.EMERALD);
    }

    public OreItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public MaterialDataList getList() {
        return LIST;
    }
}
