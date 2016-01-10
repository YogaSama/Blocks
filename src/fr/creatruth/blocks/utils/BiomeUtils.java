/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.utils;

import org.bukkit.block.Biome;

import java.util.ArrayList;
import java.util.List;

public class BiomeUtils {

    public static Biome get(String s, Biome defaultValue) {
        if (s == null) return defaultValue;

        try {
            return Biome.valueOf(s.toUpperCase());
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public static List<Biome> getList(String s) {
        List<Biome> biomes = new ArrayList<>();
        if (s == null) return biomes;

        s = s.replace("§6", "").replace("§8", "");

        if (s.contains(",")) {
            String[] split = s.split(",");
            if (split.length > 0) {
                for (String str : split) {
                    Biome b = get(str, null);
                    if (b != null) biomes.add(b);
                }
            }
        }
        else {
            Biome b = get(s, null);
            if (b != null) biomes.add(b);
        }
        return biomes;
    }

    public static String toString(List<Biome> biomes) {
        StringBuilder sb = new StringBuilder();
        if (biomes.size() > 0) {
            sb.append(biomes.get(0));
            for (int i = 1; i < biomes.size(); i++) {
                sb.append("§8,§6").append(biomes.get(i));
            }
        }
        return sb.toString();
    }
}
