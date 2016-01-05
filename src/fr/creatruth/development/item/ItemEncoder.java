package fr.creatruth.development.item;

import fr.creatruth.blocks.manager.utils.ItemUtils;
import fr.creatruth.blocks.manager.utils.NumberUtils;
import org.bukkit.inventory.ItemStack;

public class ItemEncoder {

    /**
     * Séparateur entre le code et le nom de l'item.
     */
    public static final String SPLITTER = "§T";

    /**
     * Cache un numérdo caché dans le nom d'un item.
     * si l'item est déjà codé, on extrait son nom pour
     * le réencoder.
     *
     * @param item   Item à encoder.
     * @param itemID Id à cacher dans le nom.
     */
    public static void encodeInName(ItemStack item, int itemID) {
        String name = ItemUtils.getDisplayName(item);
        if (hasCodeInName(name)) name = extractName(name);

        String id   = Integer.toString(itemID);
        String code = "";
        for (int i = 0; i < id.length(); i++) code += "§" + id.charAt(i);
        ItemUtils.setName(item, code + SPLITTER + name);
    }

    /**
     * Decode l'identifiant de l'objet.
     *
     * @param name Nom de l'item à décoder.
     * @return     Le numérdo du code.
     */
    public static int decodeId(String name) {
        String[] stringID = name.split(SPLITTER);
        String number = stringID[0].replaceAll("§", "");
        return NumberUtils.getInteger(number, -1);
    }

    /**
     * Decode l'identifiant de l'objet.
     *
     * @param item Item à décoder.
     * @return Le numéro du code.
     */
    public static int decodeId(ItemStack item) {
        return decodeId(ItemUtils.getDisplayName(item));
    }

    /**
     * Moyen de vérifier si un item est encodé.
     *
     * @return vrai si l'item est déjà encodé.
     */
    public static boolean hasCodeInName(String name) {
        return name.contains(SPLITTER) && name.split(SPLITTER).length > 0;
    }

    /**
     * Permet de récupérer le nom d'un item encodé.
     * On par du principe que le nom contient un ID.
     * Si ce n'est pas le cas, alors une exception va
     * être produite.
     *
     * @param name Nom encodé.
     * @return     Nom non codé.
     * @throws ArrayIndexOutOfBoundsException
     */
    public static String extractName(String name) {
        return name.split(SPLITTER)[1];
    }
}
