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

public class SeedsItem extends SeedItem {

    public SeedsItem(ItemStack item, Materials materials) {
        super(item, materials);
        this.seed = Material.SEEDS;
        this.material = Material.CROPS;
        ib.setDataTable(DATA);
    }
}
