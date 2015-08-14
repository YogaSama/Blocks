/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.PickableItem;
import fr.creatruth.blocks.manager.item.SpecialBase;
import fr.creatruth.blocks.manager.utils.SkullUtils;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.block.Skull;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class HeadItem extends PickableItem implements SpecialBase {

    private static final byte[] DATA = new byte[]{0, 1, 2, 3, 4};

    public HeadItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == Material.SKULL_ITEM && block.getType() == Material.SKULL) {
            BlockState state = block.getState();
            if (state instanceof Skull) {
                Skull skull = (Skull) state;
                if (skull.hasOwner())
                    SkullUtils.setHead(block, cursor);
            }
        }
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        return specialItemBuilder(Material.SKULL_ITEM, Material.SKULL_ITEM, data);
    }
}
