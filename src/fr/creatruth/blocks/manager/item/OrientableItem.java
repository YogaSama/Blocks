/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item;

import fr.creatruth.blocks.manager.Materials;
import org.bukkit.inventory.ItemStack;

public class OrientableItem extends PickableItem {

    protected static final byte[] DATA = new byte[]{0, 1, 2, 3};

    public OrientableItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    /**
     * @return Orientation entre 0 et 3.
     */
    public byte getOrientation() {
        return (byte) (block.getData() / 4);
    }
}
