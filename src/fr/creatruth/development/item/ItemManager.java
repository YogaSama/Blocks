package fr.creatruth.development.item;

import fr.creatruth.blocks.manager.tools.ItemPattern;
import fr.creatruth.blocks.manager.utils.ItemUtils;
import fr.creatruth.development.material.MatData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class ItemManager {

    private static ItemManager     instance = new ItemManager();
    private Map<String, ItemList>  map;

    public ItemManager() {
        this.map = new HashMap<>();
    }

    public void load(List<String> strings) {
        ItemList list = new ItemList();

        for (String s : strings) {
            ItemBuilder builder = new ItemBuilder(s);

            if (builder.getKey().getMaterial().getId() > 0) {
                list.add(builder);
                ItemList old = this.map.put(builder.getKey().toString(), list);

                if (old != null && old != list) {
                    List<ItemBuilder> removed = new ArrayList<>();
                    for (ItemBuilder oldBuilder : old.getList()) {
                        if (oldBuilder.getKey().equals(builder.getKey()))
                            removed.add(oldBuilder);
                    }
                    old.getList().removeAll(removed);
                }
            }
        }
    }

    public ItemList get(int id) {
        return get(id, 0);
    }

    public ItemList get(int id, int data) {
        return get(id + ":" + data);
    }

    public ItemList get(String input) {
        return map.get(input);
    }

    public ItemBuilder getBuilder(ItemStack item) {
        return getBuilder(ItemPattern.getMatData(ItemUtils.getDisplayName(item)));
    }

    public ItemBuilder getBuilder(Block block) {
        return getBuilder(block.getType(), block.getData());
    }

    public ItemBuilder getBuilder(Material material, byte data) {
        return getBuilder(new MatData(material, data));
    }

    public ItemBuilder getBuilder(MatData md) {
        ItemList list = map.get(md.toString());
        return list  != null ? list.getBuilder(md) : null;
    }

    public static ItemManager getInstance() {
        return instance;
    }
}
