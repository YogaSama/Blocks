/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.configuration;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.SpecialBase;
import fr.creatruth.blocks.manager.utils.MaterialUtils;
import fr.creatruth.blocks.manager.utils.NumberUtils;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class BlocksListFile {

    private static final String FILE_NAME = "blockslist.yml";

    private File file;
    private FileConfiguration fc;

    public static Map<Integer, ItemStack>   itemMap;
    public static int                       maxSlot;
    private static ItemStack[]              content;

    /**
     * Initialise le fichier de la physics.
     */
    public BlocksListFile() {
        file = new File(BMain.instance.getDataFolder().getAbsolutePath(), FILE_NAME);
        if (!file.exists()) {
            Config.write(FILE_NAME, file);
            BMain.log("\"" + FILE_NAME + "\" create !");
        }
        loadContent();
    }

    private FileConfiguration getFileConfiguration() {
        return fc == null ? fc = loadConfiguration() : fc;
    }

    private FileConfiguration loadConfiguration() {
        try {
            return YamlConfiguration.loadConfiguration(file);
        } catch (Exception e) {
            BMain.log("Cannot load " + FILE_NAME + " File !");
            return null;
        }
    }

    void loadContent() {
        itemMap = new HashMap<>();
        content = null;
        maxSlot = 1;
        ConfigurationSection slots = getFileConfiguration().getConfigurationSection("slots");

        for (String key : slots.getKeys(false)) {
            String s = slots.getString(key);
            int slot = NumberUtils.getInteger(key, -1);

            if (slot > -1) {
                if (slot > maxSlot)
                    maxSlot = slot;

                Material material = MaterialUtils.getMaterialWithData(s, null);
                if (material != null) {
                    byte data = MaterialUtils.getData(s, (byte) 0);

                    BaseItem bi = BaseItem.create(material, data, null);
                    ItemStack item;
                    if (bi != null) {
                        if (bi instanceof SpecialBase)
                            item = ((SpecialBase) bi).getSpecialBase(data).getItem();
                        else
                            item = bi.getItem();
                    }
                    else
                        item = new ItemStack(material);

                    itemMap.put(slot, item);
                }
            }
        }
        int amount = itemMap.size();
        BMain.log(String.format("Blockslist loaded. (§e%d§f item%s)", amount, amount > 1 ? "s" : ""));
    }

    public static ItemStack[] getContent() {
        if (content == null) {
            content = new ItemStack[BlocksListFile.maxSlot + 1];

            for (int slot : BlocksListFile.itemMap.keySet())
                content[slot] = BlocksListFile.itemMap.get(slot);
        }
        return content;
    }
}
