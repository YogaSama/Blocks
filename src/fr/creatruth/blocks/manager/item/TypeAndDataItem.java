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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class TypeAndDataItem extends TypeItem {

    protected static final byte[] none = new byte[]{};

    private Map<Material, byte[]> map;
    private byte[]                  datas;

    public TypeAndDataItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public void onSwitch(Action action) {
        map = getMap();
        datas = map.get(item.getType());

        if (action == Action.RIGHT_CLICK_AIR) {

            if (datas.length == 0 || ib.data >= datas[datas.length - 1]) {
                updateData(action, false);
            }

            else {
                ib.setDataTable(datas);
                increment();
                updateName();
            }
        }

        else if (action == Action.LEFT_CLICK_AIR) {

            if (datas.length == 0 || ib.data == 0) {
                updateData(action, true);
            }

            else {
                ib.setDataTable(datas);
                decrement();
                updateName();
            }
        }
    }

    private void updateData(Action action, boolean max) {
        super.onSwitch(action);
        datas = map.get(item.getType());
        ib.setDataTable(datas);
        if (datas.length > 0) {
            ib.setData(max ? datas[datas.length - 1] : 0);
            updateName();
        }
    }

    abstract public HashMap<Material, byte[]> getMap();

    @Override
    public List<Material> getList() {
        Material[] materials = new Material[getMap().size()];
        return Arrays.asList(getMap().keySet().toArray(materials));
    }
}