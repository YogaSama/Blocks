package fr.creatruth.blocks.block.material;

import org.bukkit.Material;

public class MatData {

    private Material material;
    private short    data;

    public MatData(Material material, short data) {
        this.material   = material;
        this.data       = data;
    }

    public MatData(int id) {
        this(Material.getMaterial(id), (short) 0);
    }

    public MatData(Material material) {
        this(material.getId(), (short) 0);
    }

    public MatData(int id, short data) {
        this(Material.getMaterial(id), data);
    }

    public Material getMaterial() {
        return material;
    }

    public short getData() {
        return data;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;

        if (object instanceof MatData) {
            MatData md = (MatData) object;
            return  md.material   == this.material &&
                    md.data       == this.data;
        }
        return false;
    }

    @Override
    public String toString() {
        return material.getId() + ":" + data;
    }
}
