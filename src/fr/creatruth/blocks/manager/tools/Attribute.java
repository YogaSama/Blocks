/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.tools;

import fr.creatruth.blocks.manager.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

public class Attribute {

    private Type          type;
    private List<Object>  attributes;

    public Attribute(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public List<Object> list() {
        if (attributes == null) attributes = new ArrayList<>();
        return attributes;
    }

    public int getInt(int index) {
        return NumberUtils.getInteger(list().get(index).toString(), 0);
    }

    public Face getFace(int index) {
        return Face.getByKey(list().get(index).toString(), Face._BLOCK);
    }

    public void add(Object value) {
        list().add(value);
    }

    @Override
    public String toString() {
        if (type == null) return "";
        if (list().size() == 0) return type.getFormat();
        return String.format(type.getFormat(), list().toArray());
    }

    public enum Type {

        SPECIAL    (""),
        LINE       ("§7Size:§f%s §7Dir:§e%s");

        String format;

        Type(String format) {
            this.format = " " + format;
        }

        public String getFormat() {
            return " §7Type:§a" + this + format;
        }

        public static Type get(String s) {
            try {
                return valueOf(s.toUpperCase());
            } catch (Exception e) {return null;}
        }
    }
}
