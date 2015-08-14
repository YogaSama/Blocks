/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import org.bukkit.inventory.ItemStack;

public class PortalItem extends BaseItem {

    private static final byte[] DATA = new byte[]{0, 3, 4, 7, 8, 11, 12, 15};

    public PortalItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA);
    }
}
