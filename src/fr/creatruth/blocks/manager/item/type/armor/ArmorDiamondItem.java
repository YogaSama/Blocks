/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type.armor;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.TypeItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ArmorDiamondItem extends TypeItem {

    private static final List<Material> ARMOR;

    static {
        ARMOR = new ArrayList<>();
        ARMOR.add(Material.DIAMOND_HELMET);
        ARMOR.add(Material.DIAMOND_CHESTPLATE);
        ARMOR.add(Material.DIAMOND_LEGGINGS);
        ARMOR.add(Material.DIAMOND_BOOTS);
    }

    public ArmorDiamondItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public List<Material> getList() {
        return ARMOR;
    }
}
