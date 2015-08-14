/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type.liquid;

import fr.creatruth.blocks.manager.Materials;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class WaterItem extends LiquidItem {

    public WaterItem(ItemStack item, Materials materials) {
        super(item, materials);
        this.texture = Material.STAINED_GLASS_PANE;
        this.durabilility = 9;
    }
}
