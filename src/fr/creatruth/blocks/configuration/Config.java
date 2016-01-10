/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.configuration;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.tools.BiomeTool;
import fr.creatruth.blocks.tools.Meter;
import fr.creatruth.blocks.utils.MaterialUtils;
import fr.creatruth.blocks.player.PlayerData;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.*;
import java.util.List;

public class Config {

    static int line_delay_per_block;
    static int line_size_limit;
    static Material meter_wand;
    static Material biome_wand;
    static int biome_max_radius;
    static byte seed_data_value;
    static List<String> line_block_blacklist;
    static List<String> blocks_blacklist;

    static final String ITEM_BYID =                             "Item.byId";
    static final String LINE_DELAY_PER_BLOCK =                  "Line.delayPerBlock";
    static final String LINE_SIZE_LIMIT =                       "Line.sizeLimit";
    static final String METER_WAND =                            "meter.wand";
    static final String BIOME_WAND =                            "biome.wand";
    static final String BIOME_MAXRADIUS =                       "biome.maxRadius";
    static final String SEEDS_DATA_VALUE =                      "seedsDataValue";
    static final String DRAGONEGG_TELEPORTATION =               "dragonEggTeleportation";
    static final String TOGGLE_INFO =                           "Toggle.info";
    static final String TOGGLE_MIDDLE =                         "Toggle.middle";
    static final String TOGGLE_BLOCK =                          "Toggle.block";
    static final String TOGGLE_CHANGE =                         "Toggle.change";
    static final String LINE_BREAK_ORIGIN =                     "Line.onBreakOrigin";
    static final String LINE_BLOCK_BLACKLIST =                  "Line.blockBlackList";
    static final String BLOCKS_BLACKLIST =                      "blocks-blacklist";

    public static FileConfiguration    fileConfig;
    public static PlayerDataFolder     playerDataFolder;

    public static void load() {
        line_delay_per_block = -1;
        line_size_limit = -1;
        meter_wand = null;
        biome_wand = null;
        biome_max_radius = -1;
        seed_data_value = -1;
        line_block_blacklist = null;
        blocks_blacklist = null;

        BMain.instance.getConfig().options().copyDefaults(true);
        BMain.instance.saveDefaultConfig();
        BMain.instance.reloadConfig();

        fileConfig = BMain.instance.getConfig();

        Meter.MATERIAL     = Config.getMeterWand();
        BiomeTool.MATERIAL = Config.getBiomeWand();

        new ListFile().loadContent();
        new BlocksListFile().loadContent();
        new PhysicsFile().loadContent();
        new MessageFile().loadContent();
        playerDataFolder = new PlayerDataFolder();

        PlayerData.Toggle.initMessages();

        BMain.log("Config loaded.");
    }

    public static boolean getItemById() {
        return fileConfig.getBoolean(ITEM_BYID, false);
    }

    public static List<String> getLineBlockBlackList() {
        if (line_block_blacklist == null) {
            line_block_blacklist = fileConfig.getStringList(LINE_BLOCK_BLACKLIST);
        }
        return line_block_blacklist;
    }

    public static List<String> getBlocksBlackList() {
        if (blocks_blacklist == null) {
            blocks_blacklist = fileConfig.getStringList(BLOCKS_BLACKLIST);
        }
        return blocks_blacklist;
    }

    public static boolean isLineBreakOrigin() {
        return fileConfig.getBoolean(LINE_BREAK_ORIGIN, true);
    }

    public static int getLineDelayPerBlock() {
        if (line_delay_per_block < 0) {
            line_delay_per_block = fileConfig.getInt(LINE_DELAY_PER_BLOCK, 20);
        }
        return line_delay_per_block;
    }

    public static int getLineSizeLimit() {
        if (line_size_limit < 0) {
            line_size_limit = fileConfig.getInt(LINE_SIZE_LIMIT, 20);
        }
        return line_size_limit;
    }

    public static Material getMeterWand() {
        if (meter_wand == null) {
            String name = fileConfig.getString(METER_WAND, "STRING");
            meter_wand = MaterialUtils.getMatByName(name, Material.STRING);
        }
        return meter_wand;
    }

    public static Material getBiomeWand() {
        if (biome_wand == null) {
            String name = fileConfig.getString(BIOME_WAND, "BLAZE_ROD");
            biome_wand = MaterialUtils.getMatByName(name, Material.BLAZE_ROD);
        }
        return biome_wand;
    }

    public static int getBiomeMaxRadius() {
        if (biome_max_radius < 0) {
            int max = fileConfig.getInt(BIOME_MAXRADIUS, 5);
            biome_max_radius = max < 0 ? 0 : max;
        }
        return biome_max_radius;
    }

    public static byte getSeedData() {
        if (seed_data_value < 0) {
            int seed = fileConfig.getInt(SEEDS_DATA_VALUE, 7);
            seed_data_value = (byte) (0 <= seed && seed <= 15 ? seed : 0);
        }
        return seed_data_value;
    }

    public static boolean isDragonEggTeleport() {
        return fileConfig.getBoolean(DRAGONEGG_TELEPORTATION, false);
    }

    public static boolean isToggle(PlayerData.Toggle toggle) {
        return fileConfig.getBoolean("toggle." + toggle.name().toLowerCase(), true);
    }

    /**
     * Sauvegarde les modifications apportées.
     */
    static void save(FileConfiguration fc, File file) {
        try {
            fc.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
