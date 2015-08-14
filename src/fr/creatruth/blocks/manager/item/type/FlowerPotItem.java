/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.PickableItem;
import fr.creatruth.blocks.manager.item.SpecialBase;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class FlowerPotItem extends PickableItem implements SpecialBase {

    private static final List<Material> MATERIALS;

    static {
        MATERIALS = new ArrayList<>();
        MATERIALS.add(Material.FLOWER_POT_ITEM);
        MATERIALS.add(Material.SAPLING);
        MATERIALS.add(Material.DEAD_BUSH);
        MATERIALS.add(Material.LONG_GRASS);
        MATERIALS.add(Material.CACTUS);
        MATERIALS.add(Material.YELLOW_FLOWER);
        MATERIALS.add(Material.RED_MUSHROOM);
        MATERIALS.add(Material.BROWN_MUSHROOM);
        MATERIALS.add(Material.RED_ROSE);
    }

    public FlowerPotItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (MATERIALS.contains(cursor.getType()) && block.getType() == Material.FLOWER_POT) {
            event.setCursor(getItem(Material.FLOWER_POT_ITEM, block.getData(), null));
        }
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        return specialItemBuilder(Material.HARD_CLAY, Material.FLOWER_POT, data);
    }
}
