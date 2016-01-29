package fr.creatruth.blocks.block.item;

import fr.creatruth.blocks.tools.ItemPattern;
import fr.creatruth.blocks.utils.ItemUtils;
import fr.creatruth.blocks.block.material.MatData;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

public class ItemList {

    private List<ItemBuilder> list;

    public ItemList() {
        this.list = new LinkedList<>();
    }

    public void add(ItemBuilder builder) {
        this.list.add(builder);
    }

    public ItemStack nextItem(ItemStack source) {
        int index =      getItemIndex(source);
        if (index == -1) return source;

        if (index >= list.size() - 1) index = 0;
        else                          index++;

        ItemStack build = list.get(index).build();
        ItemEncoder.encodeInName(build, index);
        return build;
    }

    public ItemStack previousItem(ItemStack source) {
        int index =      getItemIndex(source);
        if (index == -1) return source;

        if (index <= 0)               index = list.size() - 1;
        else                          index--;

        ItemStack build = list.get(index).build();
        ItemEncoder.encodeInName(build, index);
        return build;
    }

    private int getItemIndex(ItemStack item) {
        if (ItemEncoder.hasCodeInName(ItemUtils.getDisplayName(item)))
            return ItemEncoder.decodeId(item);

        if (ItemPattern.hasPattern(ItemPattern.P_BLOCK, item)) {
            MatData matData = ItemPattern.getMatData(ItemUtils.getDisplayName(item));
            for (int i = 0; i < list.size(); i++) {
                MatData element = list.get(i).getKey();
                if (element.equals(matData))
                    return i;
            }
            return -1;
        }

        for (int i = 0; i < list.size(); i++) {
            MatData matData = list.get(i).getKey();
            if (    item.getType()       == matData.getMaterial() &&
                    item.getDurability() == matData.getData()    ) {
                return i;
            }
        }
        return -1;
    }

    public ItemBuilder getBuilder(MatData md) {
        for (ItemBuilder ib : list) {
            if (ib.getKey().equals(md))
                return ib;
        }
        return null;
    }

    public ItemBuilder getBuilder(Material material, short data) {
        for (ItemBuilder ib : list) {
            if (    ib.getKey().getMaterial().getId() == material.getId() &&
                    ib.getKey().getData()             == data)
                return ib;
        }
        return null;
    }

    public List<ItemBuilder> getList() {
        return list;
    }
}
