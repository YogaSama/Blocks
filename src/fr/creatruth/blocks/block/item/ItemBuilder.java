package fr.creatruth.blocks.block.item;

import fr.creatruth.blocks.tools.ItemPattern;
import fr.creatruth.blocks.utils.ItemUtils;
import fr.creatruth.blocks.utils.MaterialUtils;
import fr.creatruth.blocks.block.material.MatData;
import org.bukkit.inventory.ItemStack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemBuilder {

    private static final MatData DEFAULT    = new MatData(0);

    private static final Pattern P_MATERIAL = Pattern.compile("(^\\d+(?::\\d+)?$)|key=(\\d+(?::\\d+)?)");
    private static final Pattern P_TEXTURE  = Pattern.compile("texture=(\\d+(?::\\d+)?)");
    private static final Pattern P_SPECIAL  = Pattern.compile("special=(\\d+(?::\\d+)?)");

    private MatData key;
    private MatData texture;
    private MatData special; // TODO

    /**
     * Constructeur.
     *
     * @param input Clé à déchiffre.
     *              Exemple : "mat=159:12;texture=12;special=5"
     */
    public ItemBuilder(String input) {
        this.key     = MaterialUtils.getMatData(extractMaterial(input, P_MATERIAL), DEFAULT);
        this.texture = MaterialUtils.getMatData(extractMaterial(input, P_TEXTURE ), key);
        this.special = MaterialUtils.getMatData(extractMaterial(input, P_SPECIAL ), key);
    }

    /**
     * Constructeur.
     *
     * @param key Clé du bloc.
     */
    public ItemBuilder(MatData key) {
        this.key     = key;
        this.texture = key;
        this.special = key;
    }

    /**
     * Clé du constructeur.
     * @return
     */
    public MatData getKey() {
        return key;
    }

    public ItemStack build() {
        ItemStack is = new ItemStack(texture.getMaterial());
        is.setDurability            (texture.getData());

        if      (key.getData() == 0)  is.setAmount(1);
        else if (key.getData() > 127) is.setAmount(127);
        else                          is.setAmount(key.getData());

        String material = String.format(ItemPattern.BLOCK_MAT,  key.getMaterial());
        String data     = String.format(ItemPattern.BLOCK_DATA, key.getData());

        ItemUtils.setName(is, material + data);
        return is;
    }

    @Override
    public String toString() {
        return key.toString();
    }

    private static String extractMaterial(String input, Pattern pattern) {
        String result = null;
        Matcher m = pattern.matcher(input.toLowerCase());

        if (m.find()) {
            result = (result = m.group(1)) == null ? m.group(2) : result;
            if (result != null && !result.contains(":")) result += ":0";
        }

        return result;
    }
}
