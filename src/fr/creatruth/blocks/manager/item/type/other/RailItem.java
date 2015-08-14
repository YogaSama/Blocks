/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type.other;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.MaterialDataItem;
import fr.creatruth.blocks.manager.item.SpecialBase;
import fr.creatruth.blocks.manager.item.TypeItem;

import fr.creatruth.blocks.manager.tools.MaterialDataList;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RailItem extends MaterialDataItem implements SpecialBase {

    private static final MaterialDataList LIST;

    static {
        LIST = new MaterialDataList();
        LIST.add(Materials.RAILS);
        LIST.add(Materials.ACTIVATOR_RAIL);
        LIST.add(Materials.DETECTOR_RAIL);
        LIST.add(Materials.POWERED_RAIL);
    }

    public RailItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public MaterialDataList getList() {
        return LIST;
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        return specialItemBuilder(Material.IRON_FENCE, getItemBuilder().getMaterial(), (byte) 0);
    }
}
