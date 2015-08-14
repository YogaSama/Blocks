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

public class MelonSeedsItem extends SeedItem {

    public MelonSeedsItem(ItemStack item, Materials materials) {
        super(item, materials);
        this.seed = Material.MELON_SEEDS;
        this.material = Material.MELON_STEM;
        ib.setDataTable(DATA);
    }
}
