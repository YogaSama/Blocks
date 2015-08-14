/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.tools;

import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.ItemBuilder;

import org.bukkit.entity.Player;

public class LineBuilder {

    public static void buildItem(Player player, BaseItem baseItem, Face face, int length) {
        ItemBuilder ib = baseItem.getItemBuilder();
        Attributes attributes = new Attributes(ItemBuilder.Type.LINE);
        attributes.add(length);
        attributes.add(face == Face._PLAYER ? Face.getByOrientation(player) : face);
        ib.setAttributes(attributes);
        baseItem.updateName();
    }
}
