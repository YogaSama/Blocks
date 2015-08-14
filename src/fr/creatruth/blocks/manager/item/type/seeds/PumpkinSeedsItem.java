/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type.seeds;

import fr.creatruth.blocks.manager.Materials;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class PumpkinSeedsItem extends SeedItem {

    public PumpkinSeedsItem(ItemStack item, Materials materials) {
        super(item, materials);
        this.seed = Material.PUMPKIN_SEEDS;
        this.material = Material.PUMPKIN_STEM;
        ib.setDataTable(DATA);
    }
}
