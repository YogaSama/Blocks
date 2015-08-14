/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.utils;

public class NumberUtils {

    public static byte getByte(String s, byte defaultValue) {
        if (s == null) return defaultValue;
        try {
            return Byte.parseByte(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static short getShort(String s, short defaultValue) {
        if (s == null) return defaultValue;
        try {
            return Short.parseShort(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static int getInteger(String s, int defaultValue) {
        if (s == null) return defaultValue;
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static long getLong(String s, long defaultValue) {
        if (s == null) return defaultValue;
        try {
            return Long.parseLong(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static double getDouble(String s, double defaultValue) {
        if (s == null) return defaultValue;
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static float getFloat(String s, float defaultValue) {
        if (s == null) return defaultValue;
        try {
            return Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
