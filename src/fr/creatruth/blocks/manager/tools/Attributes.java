/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.tools;

import fr.creatruth.blocks.manager.item.ItemBuilder;
import fr.creatruth.blocks.manager.tools.Face;
import fr.creatruth.blocks.manager.utils.NumberUtils;

import java.util.ArrayList;
import java.util.List;

public class Attributes {

    private ItemBuilder.Type    type;
    private List<Object>        attributes;

    public Attributes(ItemBuilder.Type type) {
        this.type = type;
    }

    public ItemBuilder.Type getType() {
        return type;
    }

    public List<Object> list() {
        if (attributes == null) {
            attributes = new ArrayList<>();
        }
        return attributes;
    }

    public int getInt(int index) {
        return NumberUtils.getInteger(list().get(index).toString(), 0);
    }

    public Face getFace(int index) {
        return Face.getByKey(list().get(index).toString(), Face._BLOCK);
    }

    public void add(Object attribute) {
        list().add(attribute);
    }

    @Override
    public String toString() {
        if (type == null) return "";
        if (list().size() == 0) return type.getFormat();
        return String.format(type.getFormat(), list().toArray());
    }
}
