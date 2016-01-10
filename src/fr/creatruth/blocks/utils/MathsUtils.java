/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.utils;

import org.bukkit.util.Vector;

public class MathsUtils {

    public static double getDistance(Vector v1, Vector v2) {
        return getRounded(Math.sqrt(Math.pow(v1.getX() - v2.getX(), 2) + Math.pow(v1.getY() - v2.getY(), 2) + Math.pow(v1.getZ() - v2.getZ(), 2)), 3);
    }

    public static int getDistance(double a, double b) {
        double i = b < a ? -1 : 1;
        return (int) Math.sqrt(Math.pow(a - (b + i), 2));
    }

    public static double getRounded(double A, int B) {
        return (double) ((int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
    }
}
