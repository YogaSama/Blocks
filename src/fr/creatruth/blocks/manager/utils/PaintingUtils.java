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
            painting.setArt(getArt(id));
    }

    public static Art getArt(int id) {
        switch (id) {
            case 0:
                return Art.KEBAB;
            case 1:
                return Art.AZTEC;
            case 2:
                return Art.ALBAN;
            case 3:
                return Art.AZTEC2;
            case 4:
                return Art.BOMB;
            case 5:
                return Art.PLANT;
            case 6:
                return Art.WASTELAND;
            case 7:
                return Art.POOL;
            case 8:
                return Art.COURBET;
            case 9:
                return Art.SEA;
            case 10:
                return Art.SUNSET;
            case 11:
                return Art.CREEBET;
            case 12:
                return Art.WANDERER;
            case 13:
                return Art.GRAHAM;
            case 14:
                return Art.MATCH;
            case 15:
                return Art.BUST;
            case 16:
                return Art.STAGE;
            case 17:
                return Art.VOID;
            case 18:
                return Art.SKULL_AND_ROSES;
            case 19:
                return Art.WITHER;
            case 20:
                return Art.FIGHTERS;
            case 21:
                return Art.POINTER;
            case 22:
                return Art.PIGSCENE;
            case 23:
                return Art.BURNINGSKULL;
            case 24:
                return Art.SKELETON;
            case 25:
                return Art.DONKEYKONG;
            default:
                return null;
        }
    }
}
