/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.tools.ItemPattern;
import fr.creatruth.blocks.manager.utils.ItemUtils;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class BaseItem {

    /**
     * Convertir l'item en item de base pour le plugin.
     * @param item L'item à transformer.
     * @return L'item spécial.
     */
    public static BaseItem toBaseItem(ItemStack item) {
        String name = ItemUtils.getDisplayName(item);
        Material material = ItemPattern.getMaterial(name);
        if (material == null) material = item.getType();
        return Materials.getBaseItem(material, item);
    }

    public static BaseItem create(Material material, byte data, ItemBuilder.Type type) {
        ItemBuilder ib = new ItemBuilder("", material, data, type);
        return Materials.getBaseItem(ib.getMaterial(), ib.createItem());
    }

    protected ItemBuilder   ib;
    protected Materials     materials;
    protected String        name;
    protected ItemStack     item;

    public BaseItem(ItemStack item, Materials materials) {
        this.name =     ItemUtils.getDisplayName(item);
        this.materials = materials;
        this.item =     item;

        ib = new ItemBuilder(name, ItemPattern.getMaterial(name), item.getType());
        ib.setItem(item);
        ib.setDataTable(materials.getDataTable());
        ib.setData(ItemPattern.getData(name));
    }

    public ItemBuilder getItemBuilder() {
        return ib;
    }

    public Materials getMaterials() {
        return materials;
    }

    public ItemStack getItem() {
        return item;
    }

    public static ItemStack getItem(Material material, byte data, ItemBuilder.Type type) {
        return BaseItem.create(material, data, type).getItem();
    }

    public static BaseItem specialItemBuilder(Material hand, Material material, byte data) {
        BaseItem bi = BaseItem.create(material, data, ItemBuilder.Type.SPECIAL);
        bi.getItemBuilder().ajustData(data);
        bi.updateName();
        bi.getItem().setType(hand);
        return bi;
    }

    public void increment() {
        ib.setData(ib.nextData());
    }

    public void decrement() {
        ib.setData(ib.previousData());
    }

    public void updateName() {
        ItemUtils.setName(item, ib.getName());
    }

    public void onPick(InventoryCreativeEvent event) {
    }

    public void onSwitch(Action action) {
        if (getItemBuilder().getDataTable().length > 0) {
            if (action == Action.LEFT_CLICK_AIR) {
                decrement();
                updateName();
            } else if (action == Action.RIGHT_CLICK_AIR) {
                increment();
                updateName();
            }
        }
    }
}