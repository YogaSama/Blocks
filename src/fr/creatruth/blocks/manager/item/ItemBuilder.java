/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item;

import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.manager.tools.Attribute;
import fr.creatruth.blocks.manager.tools.ItemPattern;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder {

    private ItemStack   item;

    private Material    material;
    byte                max;
    byte                data;
    private Attribute   attribute;

    public ItemBuilder(String name, Material material, Material defaultMat) {
        this(name, material == null ? defaultMat : material, (byte) 0, null);
    }

    public ItemBuilder(String name, Material material, byte data, Attribute.Type type) {
        this.material = material;
        this.data = data;
        if (type == null)
            attribute = ItemPattern.getAttributes(name);
        else {
            attribute = new Attribute(type);
        }
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public void setData(byte data) {
        if (data < 0)      this.data = (byte) item.getDurability();
        else               this.data = data;

        item.setDurability(this.data);
        item.setAmount    (this.data <= 0 ? 1 : this.max > 64 ? 1 : this.data);
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public String getName() {
        String material =   String.format(ItemPattern.BLOCK_MAT, Config.getItemById() ? this.material.getId() : this.material);
        String data =       String.format(ItemPattern.BLOCK_DATA, this.data);
        return material + data + getAttribute().toString();
    }
}