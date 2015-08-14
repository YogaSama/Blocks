/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type.other;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.MaterialDataItem;
import fr.creatruth.blocks.manager.item.TypeItem;

import fr.creatruth.blocks.manager.tools.MaterialDataList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RecordItem extends MaterialDataItem {
    
    private static final MaterialDataList CD;
    
    static {
        CD = new MaterialDataList();
        CD.add(Materials.GOLD_RECORD);
        CD.add(Materials.GREEN_RECORD);
        CD.add(Materials.RECORD_3);
        CD.add(Materials.RECORD_4);
        CD.add(Materials.RECORD_5);
        CD.add(Materials.RECORD_6);
        CD.add(Materials.RECORD_7);
        CD.add(Materials.RECORD_8);
        CD.add(Materials.RECORD_9);
        CD.add(Materials.RECORD_10);
        CD.add(Materials.RECORD_11);
        CD.add(Materials.RECORD_12);
    }

    public RecordItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public MaterialDataList getList() {
        return CD;
    }
}
