/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.PickableItem;
import org.bukkit.inventory.ItemStack;

public class QuartzItem extends PickableItem {

    private static final byte[] DATA = new byte[]{0, 1, 2, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    public QuartzItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA);
    }
}
