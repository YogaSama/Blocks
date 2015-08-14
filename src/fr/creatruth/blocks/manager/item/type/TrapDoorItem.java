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
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class TrapDoorItem extends PickableItem implements SpecialBase {

    private static final byte[] DATA = new byte[]{0, 1, 2, 3};

    public TrapDoorItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA);
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        BaseItem baseItem = specialItemBuilder(Material.WOOD, Material.TRAP_DOOR, data);
        blockDurability(baseItem);
        return baseItem;
    }

    @Override
    public void onSwitch(Action action) {
        if (getItem().getType() != Material.TRAP_DOOR) {
            super.onSwitch(action);
            blockDurability(this);
        }
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == block.getType()) {
            byte data = block.getData();

            if (data < 4)
                event.setCursor(getItem(block.getType(), (byte) 0, null)); // Ouvert bas
            else if (data < 8)
                event.setCursor(getItem(block.getType(), (byte) 1, null)); // Ouvert haut
            else if (data < 12)
                event.setCursor(getItem(block.getType(), (byte) 2, null)); // Fermé bas
            else
                event.setCursor(getItem(block.getType(), (byte) 3, null)); // Fermé haut
        }
    }

    private void blockDurability(BaseItem baseItem) {
        baseItem.getItem().setDurability((short) 0);
    }
}
