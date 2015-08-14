package fr.creatruth.blocks.manager.item;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.tools.MaterialDataList;
import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;

abstract public class MaterialDataItem extends BaseItem {

    public MaterialDataItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public void onSwitch(Action action) {
        int i = getList().indexOf(getItemBuilder().getMaterial(), getItemBuilder().getData());
        MaterialDataList list = getList();

        if (action == Action.LEFT_CLICK_AIR) {
            setTypeAndData(list.get(--i < 0 ? list.size() - 1 : i));
        }
        else if (action == Action.RIGHT_CLICK_AIR) {
            setTypeAndData(list.get(++i >= list.size() ? 0 : i));
        }
    }

    private void setTypeAndData(MaterialData md) {
        Material mat = md.getItemType();
        item.setType(mat);
        ib.setMaterial(mat);
        Materials m = Materials.getMaterials(mat);
        if (m != null) ib.setDataTable(m.getDataTable());
        ib.setData(md.getData());
        updateName();
    }

    abstract public MaterialDataList getList();
}
