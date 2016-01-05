/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.utils;

import org.bukkit.Art;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Painting;

public class PaintingUtils {

    public static Painting getPainting(Entity entity) {
        return entity instanceof Painting ? (Painting) entity : null;
    }

    public static void setArt(Entity entity, int id) {
        Painting painting = getPainting(entity);
        if (painting != null)
            painting.setArt(Art.getById(id));
    }
}
