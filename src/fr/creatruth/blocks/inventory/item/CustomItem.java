/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.inventory.item;

import fr.creatruth.blocks.manager.utils.NumberUtils;
import org.bukkit.inventory.ItemStack;

public class CustomItem {

    /**
     * Cache l'identifiant de l'objet dans son nom
     */
    public static String encodeInName(String itemName, int itemID) {
        String id = Integer.toString(itemID);
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < id.length(); i++) {
            builder.append("§").append(id.charAt(i));
        }
        return builder + "§S" + itemName;
    }

    /**
     * Decode l'identifiant de l'objet
     */
    public static int decodeId(ItemStack item) {
        int intId = -1;
        if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
            String itemName = item.getItemMeta().getDisplayName();
            if (itemName.contains("§S")) {
                String[] stringID = itemName.split("§S");
                if (stringID.length > 0) {
                    itemName = stringID[0].replaceAll("§", "");
                    intId = NumberUtils.getInteger(itemName, -1);
                }
            }
        }
        return intId;
    }
}
