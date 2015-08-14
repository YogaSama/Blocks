package fr.creatruth.blocks.manager.tools;

import fr.creatruth.blocks.manager.Materials;
import org.bukkit.Material;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.List;

public class MaterialDataList {

    private final List<MaterialData> LIST = new ArrayList<>();

    public void add(Materials m) {
        if (m.getDataTable().length > 0) {
            for (byte data : m.getDataTable()) {
                LIST.add(new MaterialData(m.getId(), data));
            }
        }
        else
            LIST.add(new MaterialData(m.getId()));
    }

    public int indexOf(Material material, byte data) {
        for (int i = 0; i < LIST.size(); i++) {
            MaterialData md = LIST.get(i);
            if (md.getItemType().equals(material) && md.getData() == data)
                return i;
        }
        return 0;
    }

    public MaterialData get(int index) {
        return LIST.get(index);
    }

    public int size() {
        return LIST.size();
    }
}
