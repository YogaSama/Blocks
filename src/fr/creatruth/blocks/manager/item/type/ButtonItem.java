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

public class ButtonItem extends PickableItem implements SpecialBase {

    private static final byte[] DATA = new byte[]{0, 1};

    public ButtonItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == block.getType()) {
            byte data = 9 <= block.getData() && block.getData() <= 12 ? (byte) 1 : 0;
            event.setCursor(getItem(block.getType(), data, null));
        }
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        BaseItem baseItem = specialItemBuilder(getHandMaterialByPlate(), getItemBuilder().getMaterial(), data);
        blockDurability(baseItem);
        return baseItem;
    }

    @Override
    public void onSwitch(Action action) {
        super.onSwitch(action);
        blockDurability(this);
    }

    private void blockDurability(BaseItem baseItem) {
        baseItem.getItem().setDurability((byte) 0);
    }

    private Material getHandMaterialByPlate() {
        switch (getItemBuilder().getMaterial()) {
            case WOOD_BUTTON:    return Material.WOOD;
            case STONE_BUTTON:   return Material.STONE;
        }
        return getItemBuilder().getMaterial();
    }
}
