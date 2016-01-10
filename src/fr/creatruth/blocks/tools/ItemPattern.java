/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.tools;

import fr.creatruth.blocks.utils.BiomeUtils;
import fr.creatruth.blocks.utils.ItemUtils;
import fr.creatruth.blocks.utils.MaterialUtils;
import fr.creatruth.blocks.utils.NumberUtils;
import fr.creatruth.development.material.MatData;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Garde en référence les différents paternes que les items
 * peuvent avoir ainsi que leur formats.
 */
public class ItemPattern {

    public static boolean hasPattern(Pattern pattern, ItemStack item) {
        String name = ItemUtils.getDisplayName(item);
        return pattern.matcher(name).find();
    }


    /**
     * -------------------- Blocs
     *
     * Group(1) : '§7Block:§5'
     * Group(2) :  materiel
     * Group(3) : '§7Data:§b'
     * Group(4) :  data
     */
    public static final String  BLOCK_MAT   =  "§7Block:§5%s";
    public static final String  BLOCK_DATA  = " §7Data:§b%d";

    public static final Pattern P_BLOCK     = Pattern.compile("(.{2}Block:.{2})(\\w+)(?:( .{2}Data:.{2})((\\d+)))?");

    public static MatData getMatData(String input) {
        return new MatData(getMaterial(input), getData(input));
    }

    public static Material getMaterial(String input) {
        String output = get(P_BLOCK, input, 2);
        return MaterialUtils.getMaterial(output, null);
    }

    public static byte getData(String input) {
        String output = get(P_BLOCK, input, 4);
        return output != null ? NumberUtils.getByte(output, (byte) 0) : -1;
    }


    /**
     * -------------------- Types
     */
    public static final Pattern P_TYPE      = Pattern.compile("( .{2}Type:.{2})((\\w+))");
    public static final Pattern P_LINE      = Pattern.compile(" .{2}Size:.{2}(\\d+) .{2}Dir:.{2}((\\w+))");

    public static Attribute getAttributes(String input) {
        String output = get(P_TYPE, input, 2);
        Attribute.Type type = Attribute.Type.get(output);
        Attribute atb = new Attribute(type);
        if (type == Attribute.Type.LINE) {
            Matcher m = P_LINE.matcher(input);
            if (m.find()) {
                for (int i = 1; i < m.groupCount(); i++) {
                    atb.add(m.group(i));
                }
            }
        }
        return atb;
    }


    /**
     * -------------------- Biomes
     */
    public static final String     BIOME_FORMAT     = "§7Biome:§6%s §7Radius:§3%d";
    public static final Pattern    P_BIOME          = Pattern.compile(".{2}Biome:.{2}([^ ]*) .{2}Radius:.{2}(\\d+)");

    public static List<Biome> getBiomes(String input) {
        String output = get(P_BIOME, input, 1);
        return BiomeUtils.getList(output);
    }

    public static int getRadius(String input) {
        String output = get(P_BIOME, input, 2);
        return NumberUtils.getInteger(output, 0);
    }

    /**
     * -------------------- Tools
     *
     * @param p Le paterne utilisé.
     * @param input La chaine de caractère à tester.
     * @param group Le groupe à récupérer.
     * @return null si le group n'existe pas ou si le group a une valeur null.
     */
    public static String get(Pattern p, String input, int group) {
        Matcher m = p.matcher(input);
        return m.find() && group <= m.groupCount() ? m.group(group) : null;
    }
}
