/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item;

import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.manager.tools.Attributes;
import fr.creatruth.blocks.manager.tools.ItemPattern;
import fr.creatruth.blocks.manager.utils.ItemUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder {

    public static final byte[] DATA = new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    private ItemStack   item;
    private byte[]      dataTable;

    private Material    material;
    byte                min;
    byte                max;
    byte                data;
    private Attributes  attributes;

    public ItemBuilder(String name, Material material, Material defaultMat) {
        this(name, material == null ? defaultMat : material, (byte) 0, null);
    }

    public ItemBuilder(String name, Material material, byte data, Type type) {
        this.material = material;
        setDataTable(DATA);
        this.data = data;
        if (type == null)
            setAttributesByName(name);
        else {
            attributes = new Attributes(type);
        }
    }

    public ItemStack getItem() {
        return item;
    }

    public byte[] getDataTable() {
        return dataTable;
    }

    public Material getMaterial() {
        return material;
    }

    public byte getMin() {
        return min;
    }

    public byte getMax() {
        return max;
    }

    public byte getData() {
        return data;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void setDataTable(byte... table) {
        dataTable = table;
        if (dataTable.length > 0) {
            min = dataTable[0];
            max = dataTable[dataTable.length - 1];
        }
        else {
            min = 0;
            max = 0;
        }
    }

    public boolean containsData(byte data) {
        return indexOf(data) < 0;
    }

    public void setData(byte data) {
        if (data < 0)       this.data = (byte) item.getDurability();
        else                this.data = data;

        item.setDurability( this.data);
        item.setAmount(     this.data <= 0 ? 1 : this.max > 64 ? 1 : this.data);
    }

    public byte nextData() {
        if (dataTable.length == 0)          return data = 0;
        if (data < min || max < data)       return data = dataTable[0];
        int i = indexOf(data);
        if (i < 0 || i + 1 >= dataTable.length)  return data = min;
        else                                return data = dataTable[i + 1];
    }

    public byte previousData() {
        if (dataTable.length == 0)          return data = 0;
        if (data < min || max < data)       return data = dataTable[dataTable.length - 1];
        int i = indexOf(data);
        if (i < 0 || i - 1 < 0)             return data = max;
        else                                return data = dataTable[i - 1];
    }

    public void ajustData(byte data) {
        this.data = data;

        if (dataTable.length == 0)
            data = 0;

        else if (indexOf(data) < 0) {
            int min = Integer.MAX_VALUE;
            for (byte d : dataTable) {
                int ecart = Math.abs(d - this.data);
                if (ecart < min) {
                    min = ecart;
                    data = d;
                }
            }
        }
        setData(data);
    }

    private int indexOf(byte data) {
        for (int i = 0; i < dataTable.length; i++) {
            if (dataTable[i] == data) {
                return i;
            }
        }
        return -1;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public void setAttributesByName(String name) {
        this.attributes = ItemPattern.getAttributes(name);
    }

    public ItemStack createItem() {
        item = new ItemStack(this.material);
        setData(data);
        ItemUtils.setName(item, getName());

        return item;
    }

    public String getName() {
        String material =   String.format(ItemPattern.BLOCK_MAT, Config.getItemById() ? getMaterial().getId() : getMaterial());
        String data =       dataTable.length != 0 ? String.format(ItemPattern.BLOCK_DATA, getData()) : "";
        return material + data + getAttributes().toString();
    }

    public enum Type {

        SPECIAL(""),
        WATER(""), // TODO
        LINE(" §7Size:§f%s §7Dir:§e%s");

        String format;

        Type(String format) {
            this.format = format;
        }

        public String getFormat() {
            return " §7Type:§a" + this + format;
        }

        public static Type get(String s) {
            try {
                return valueOf(s.toUpperCase());
            } catch (Exception e) {
                return null;
            }
        }
    }
}
