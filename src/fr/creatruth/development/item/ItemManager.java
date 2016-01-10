package fr.creatruth.development.item;

import fr.creatruth.blocks.tools.ItemPattern;
import fr.creatruth.blocks.utils.ItemUtils;
import fr.creatruth.development.material.MatData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ItemManager {

    private static ItemManager     instance = new ItemManager();
    private Map<Integer, Map<Short, ItemList>> newMap;

    public ItemManager() {
        this.newMap = new HashMap<>();
    }

    public void load(List<String> strings) {
        ItemList list = new ItemList();

        for (String s : strings) {
            ItemBuilder builder = new ItemBuilder(s);
            int id = builder.getKey().getMaterial().getId();

            if (id > 0) {
                list.add(builder);
                if (!newMap.containsKey(id))
                    newMap.put(id, new HashMap<Short, ItemList>());

                newMap.get(id).put(builder.getKey().getData(), list);
            }
        }
    }

    public ItemList get(MatData md) {
        return get(md.getMaterial().getId(), md.getData());
    }

    public ItemList get(int id) {
        return get(id, (short) 0);
    }

    public ItemList get(int id, short data) {
        return newMap.get(id).get(data);
    }

    public ItemBuilder getBuilder(ItemStack item) {
        return getBuilder(ItemPattern.getMatData(ItemUtils.getDisplayName(item)));
    }

    public ItemBuilder getBuilder(Block block) {
        return getBuilder(block.getType(), block.getData());
    }

    public ItemBuilder getBuilder(Material material, short data) {
        Map<Short, ItemList> map = newMap.get(material.getId());
        if (map.keySet().contains(data))
            return map.get(data).getBuilder(material, data);

        int d       = Integer.MAX_VALUE;
        short nData = 0;
        for (Short s : map.keySet()) {
            if (Math.abs(s - data) < d) {
                d     = Math.abs(s - data);
                nData = s;
            }
        }
        return map.get(nData).getBuilder(material, nData);
    }

    public ItemBuilder getBuilder(MatData md) {
        return getBuilder(md.getMaterial(), md.getData());
    }

    public static ItemManager getInstance() {
        return instance;
    }
}
