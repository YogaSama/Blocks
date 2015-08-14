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

public class FoodItem extends MaterialDataItem {

    private static final MaterialDataList LIST;

    static {
        LIST = new MaterialDataList();
        LIST.add(Materials.APPLE);
        LIST.add(Materials.MUSHROOM_SOUP);
        LIST.add(Materials.BREAD);
        LIST.add(Materials.PORK);
        LIST.add(Materials.GRILLED_PORK);
        LIST.add(Materials.GOLDEN_APPLE);
        LIST.add(Materials.RAW_FISH);
        LIST.add(Materials.COOKED_FISH);
        LIST.add(Materials.COOKIE);
        LIST.add(Materials.MELON);
        LIST.add(Materials.RAW_BEEF);
        LIST.add(Materials.COOKED_BEEF);
        LIST.add(Materials.RAW_CHICKEN);
        LIST.add(Materials.COOKED_CHICKEN);
        LIST.add(Materials.ROTTEN_FLESH);
        LIST.add(Materials.SPIDER_EYE);
        LIST.add(Materials.BAKED_POTATO);
        LIST.add(Materials.POISONOUS_POTATO);
        LIST.add(Materials.GOLDEN_CARROT);
        LIST.add(Materials.PUMPKIN_PIE);
        LIST.add(Materials.COOKED_MUTTON);
        LIST.add(Materials.COOKED_RABBIT);
    }

    public FoodItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public MaterialDataList getList() {
        return LIST;
    }
}
