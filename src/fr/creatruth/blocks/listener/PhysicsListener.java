/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.listener;

import fr.creatruth.blocks.configuration.PhysicsFile;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;

import java.util.Set;

public class PhysicsListener extends AListener {

    @EventHandler(ignoreCancelled = true)
    public void onBlockFromTo(EntityChangeBlockEvent event) {
        if (event.getEntityType() == EntityType.FALLING_BLOCK) {
            Material material = event.getBlock().getType();
            BlockPhysicsEvent physic = new BlockPhysicsEvent(event.getBlock(), material.getId());
            onPhysic(physic);
            if (physic.isCancelled())
                event.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPhysic(BlockPhysicsEvent event) {
        Material material = event.getBlock().getType();
        String worldName = event.getBlock().getWorld().getName();

        if (isGlobal(material)) {
            if (!PhysicsFile.worldsBlackList.contains(worldName) && !isExcludeInWorld(worldName, material))
                event.setCancelled(true);
        }

        else if (isInWorld(worldName, material)) {
            event.setCancelled(true);
        }
    }

    public static boolean isGlobal(Material material) {
        if (PhysicsFile.globalStar) return !PhysicsFile.globalExclude.contains(material);
        return PhysicsFile.global.contains(material);
    }

    public static boolean isInWorld(String worldName, Material material) {
        Set<Material> materials = PhysicsFile.worldsMap.get(worldName);
        if (materials != null) {
            if (PhysicsFile.worldsMapStar.get(worldName)) return !isExcludeInWorld(worldName, material);
            return materials.contains(material);
        }
        return false;
    }

    public static boolean isExcludeInWorld(String worldName, Material material) {
        Set<Material> exclude = PhysicsFile.worldsMapExclude.get(worldName);
        return exclude != null && exclude.contains(material);
    }
}
