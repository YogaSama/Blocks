/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.configuration;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.utils.MaterialUtils;
import fr.creatruth.blocks.utils.NumberUtils;
import fr.creatruth.development.item.ItemBuilder;
import fr.creatruth.development.item.ItemManager;
import fr.creatruth.development.material.MatData;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class BlocksListFile extends AConfigFile {

    public static Map<Integer, ItemStack>   itemMap;
    public static int                       maxSlot;
    private static ItemStack[]              content;

    /**
     * Constructeur..
     */
    public BlocksListFile() {
        super("blockslist.yml", true);
    }

    @Override
    public void loadContent() {
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

                MatData md = MaterialUtils.getMatData(s, null);
                if (md != null && md.getMaterial().getId() > 0) {
                    ItemBuilder ib = ItemManager.getInstance().getBuilder(md);
                    if (ib != null) itemMap.put(slot, ib.build());
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
