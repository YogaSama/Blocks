package fr.creatruth.blocks.manager.item.type.other;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.MaterialDataItem;
import fr.creatruth.blocks.manager.tools.MaterialDataList;
import org.bukkit.inventory.ItemStack;

public class PlantItem extends MaterialDataItem {

    private static final MaterialDataList LIST;

    static {
        LIST = new MaterialDataList();
        LIST.add(Materials.LONG_GRASS);
        LIST.add(Materials.DEAD_BUSH);
        LIST.add(Materials.YELLOW_FLOWER);
        LIST.add(Materials.RED_ROSE);
        LIST.add(Materials.BROWN_MUSHROOM);
        LIST.add(Materials.RED_MUSHROOM);
    }

    public PlantItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public MaterialDataList getList() {
        return LIST;
    }
}