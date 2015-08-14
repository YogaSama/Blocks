/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.DefaultPickableItem;
import fr.creatruth.blocks.manager.item.SpecialBase;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public class PlateItem extends DefaultPickableItem implements SpecialBase {

    public PlateItem(ItemStack item, Materials materials) {
        super(item, materials);
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
        short dura = 0;
        switch (baseItem.getItemBuilder().getMaterial()) {
            case WOOD_PLATE:    dura = 0;   break;
            case STONE_PLATE:   dura = 3;   break;
            case IRON_PLATE:    dura = 7;   break;
            case GOLD_PLATE:    dura = 1;   break;
        }
        baseItem.getItem().setDurability(dura);
    }

    private Material getHandMaterialByPlate() {
        switch (getItemBuilder().getMaterial()) {
            case WOOD_PLATE:    return Material.WOOD_STEP;
            case STONE_PLATE:   return Material.STEP;
            case IRON_PLATE:    return Material.STEP;
            case GOLD_PLATE:    return Material.STEP;
        }
        return getItemBuilder().getMaterial();
    }
}
