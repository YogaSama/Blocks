/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item;

import fr.creatruth.blocks.manager.Materials;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class NoDataItem extends PickableItem {

    public NoDataItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable();
    }

    @Override
    public void onSwitch(Action action) {}
}
