/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item;

import fr.creatruth.blocks.manager.Materials;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Rails;

import java.util.List;

abstract public class TypeItem extends NoDataItem {

    public TypeItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public void onSwitch(Action action) {
        int i = getList().indexOf(getItemBuilder().getMaterial());
        List<Material> list = getList();

        if (action == Action.LEFT_CLICK_AIR) {
            setType(list.get(--i < 0 ? list.size() - 1 : i));
        }
        else if (action == Action.RIGHT_CLICK_AIR) {
            setType(list.get(++i >= list.size() ? 0 : i));
        }
    }

    private void setType(Material material) {
        item.setType(material);
        ib.setMaterial(material);
        updateName();
    }

    abstract public List<Material> getList();
}
