/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.command.argument;

import fr.creatruth.blocks.utils.MaterialUtils;
import fr.creatruth.blocks.utils.NumberUtils;
import fr.creatruth.blocks.block.material.MatData;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

/**
 * Permet de gérer les arguments entrés dans les commandes.
 * /<cmd> [args...]
 */

public class Arguments {

    private String[] arguments;

    public Arguments(String[] arguments) {
        this.arguments = arguments;
    }

    /*
     * OBJECT
     */

    public short getShort(int index, short defaultValue) {
        return NumberUtils.getShort(get(index), defaultValue);
    }

    public int getInt(int index, int defaultValue) {
        return NumberUtils.getInteger(get(index), defaultValue);
    }

    public float getFloat(int index, float defaultValue) {
        return NumberUtils.getFloat(get(index), defaultValue);
    }

    public double getDouble(int index, double defaultValue) {
        return NumberUtils.getDouble(get(index), defaultValue);
    }

    public long getLong(int index, long defaultValue) {
        return NumberUtils.getLong(get(index), defaultValue);
    }

    public boolean getBoolean(int index, boolean defaultValue) {
        if (get(index) == null) return defaultValue;
        try {
            return Boolean.parseBoolean(get(index));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /*
     * BUKKIT
     */

    public Player getPlayer(int index) {
        return Bukkit.getPlayer(get(index));
    }

    public World getWorld(int index) {
        return Bukkit.getWorld(get(index));
    }

    public MatData getMatData(int index) {
        return MaterialUtils.getMatData(get(index), null);
    }

    public byte getData(int index, byte data) {
        return MaterialUtils.getData(get(index), data);
    }

    /*
     * ARGUMENTS
     */

    public String get(int index) {
        return get(index, null);
    }

    public String get(int index, String s) {
        if (size() > index)
            return this.arguments[index];
        return s;
    }

    public String[] getArgs() {
        return this.arguments;
    }

    public int size() {
        return this.arguments.length;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (String arg : getArgs()) {
            builder.append(arg).append(" ");
        }
        return builder.toString();
    }
}
