/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type.armor.horse;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.TypeItem;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BardingItem extends TypeItem {

    private static final List<Material> BARDING;

    static {
        BARDING = new ArrayList<>();
        BARDING.add(Material.IRON_BARDING);
        BARDING.add(Material.GOLD_BARDING);
        BARDING.add(Material.DIAMOND_BARDING);
    }

    public BardingItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public List<Material> getList() {
        return BARDING;
    }
}