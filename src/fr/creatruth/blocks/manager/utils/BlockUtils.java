/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.HumanEntity;

import java.util.HashSet;
import java.util.Set;

public class BlockUtils {

    private static final Set<Material> DEFAULT;
    private static final Set<Material> TRANSPARENTS;

    static {
        DEFAULT = new HashSet<>();
        DEFAULT.add(Material.AIR);

        TRANSPARENTS = new HashSet<>();
        TRANSPARENTS.add(Material.AIR);
        TRANSPARENTS.add(Material.WATER);
        TRANSPARENTS.add(Material.LAVA);
        TRANSPARENTS.add(Material.STATIONARY_WATER);
        TRANSPARENTS.add(Material.STATIONARY_LAVA);
        TRANSPARENTS.add(Material.STEP);
        TRANSPARENTS.add(Material.WOOD_STEP);
        TRANSPARENTS.add(Material.CARPET);
    }

    public static Block getExactlyTargetBlock(HumanEntity human, int distance) {
        return human.getTargetBlock(TRANSPARENTS, distance);
    }

    public static Block getTargetBlock(HumanEntity human, int distance) {
        return human.getTargetBlock(DEFAULT, distance);
    }
}
