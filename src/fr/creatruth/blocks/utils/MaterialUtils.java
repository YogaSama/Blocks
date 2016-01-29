/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.utils;

import fr.creatruth.blocks.block.material.MatData;
import org.bukkit.Material;

public class MaterialUtils {

    /**
     * Récupère un Material avec un ID ou bien un String.
     * @param s Un nom de matériaux ou son ID.
     * @return un Material si le paramétre été valide, sinon null.
     */
    public static Material getMaterial(String s, Material defaultValue) {
        if (s == null) return defaultValue;
        Material mat = getMatByName(s, null);
        return mat == null ? getMatById(NumberUtils.getInteger(s, 0)) : mat;
    }

    public static Material getMatByName(String s, Material defaultMat) {
        if (s == null) return defaultMat;
        try {
            return Material.valueOf(s.toUpperCase());
        } catch (IllegalArgumentException e) {
            return defaultMat;
        }
    }

    public static Material getMatById(int id) {
        return getMatById(id, null);
    }

    public static Material getMatById(int id, Material defaultMat) {
        Material mat = Material.getMaterial(id);
        return mat == null ? defaultMat : mat;
    }

    public static Material getMaterialWithData(String s, Material defaultValue) {
        Material material = null;
        if (s != null) {
            if (s.contains(":")) {
                String[] split = s.split(":");
                if (split.length > 0)
                    material = MaterialUtils.getMaterial(split[0], null);
            }
            else {
                material = MaterialUtils.getMaterial(s, null);
            }

        }
        return material == null ? defaultValue : material;
    }

    public static MatData getMatData(String s, MatData defaultValue) {
        if (s == null) return defaultValue;
        MatData md = null;

        if (s.contains(":")) {
            String[] split  = s.split(":");
            if (split.length > 1) {
                Material mat = MaterialUtils.getMaterial(split[0], Material.AIR);
                short data = NumberUtils.getShort(split[1], (short) 0);
                md = new MatData(mat, data);
            }
        }
        else {
            Material mat = MaterialUtils.getMaterial(s, Material.AIR);
            md = new MatData(mat);
        }
        return md;
    }

    public static byte getData(String s, byte data) {
        if (s != null) {
            if (s.contains(":")) {
                String[] split = s.split(":");
                if (split.length > 1)
                    return NumberUtils.getByte(split[1], (byte) 0);
            }
        }
        return data;
    }
}
