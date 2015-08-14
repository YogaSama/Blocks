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

public class PistonExtensionItem extends PickableItem implements SpecialBase {

    private static final byte[] DATA = new byte[]{0, 1};

    public PistonExtensionItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (block.getType() == Material.PISTON_EXTENSION) {
            if (cursor.getType() == Material.PISTON_BASE && block.getData() < 8) {
                event.setCursor(getSpecialBase((byte) 0).getItem());

            } else if (cursor.getType() == Material.PISTON_STICKY_BASE  && block.getData() >= 8) {
                event.setCursor(getSpecialBase((byte) 1).getItem());
            }
        }
    }

    @Override
    public void onSwitch(Action action) {
        Material mat = ib.getItem().getType() == Material.PISTON_BASE ? Material.PISTON_STICKY_BASE : Material.PISTON_BASE;
        if (action == Action.LEFT_CLICK_AIR) {
            ib.getItem().setType(mat);
            decrement();
            updateName();
        }
        else if (action == Action.RIGHT_CLICK_AIR) {
            ib.getItem().setType(mat);
            increment();
            updateName();
        }
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        return specialItemBuilder(data == 0 ? Material.PISTON_BASE : Material.PISTON_STICKY_BASE, Material.PISTON_EXTENSION, data);
    }
}
