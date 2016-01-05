/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item;

import org.bukkit.inventory.ItemStack;

public class OrientableItem extends PickableItem {

    public OrientableItem(ItemStack item) {
        super(item);
    }

    /**
     * @return Orientation entre 0 et 3.
     */
    public byte getOrientation() {
        return (byte) (block.getData() / 4);
    }
}
