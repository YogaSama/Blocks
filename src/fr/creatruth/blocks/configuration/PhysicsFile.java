/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.configuration;

import fr.creatruth.blocks.manager.utils.MaterialUtils;
import fr.creatruth.blocks.BMain;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.*;

public class PhysicsFile extends AConfigFile {

    public static Map<String, Set<Material>>    worldsMap;
    public static Map<String, Boolean>          worldsMapStar;
    public static Map<String, Set<Material>>    worldsMapExclude;

    public static Set<Material>                 global;
    public static boolean                       globalStar;
    public static Set<Material>                 globalExclude;

    public static Set<String>                   worldsBlackList;

    /**
     * Initialise le fichier de la physics.
     */
    public PhysicsFile() {
        super("physics.yml", true);
    }

    @Override
    public void loadContent() {
        worldsMap = new HashMap<>();
        worldsMapStar = new HashMap<>();
        worldsMapExclude = new HashMap<>();

        global = new HashSet<>();
        globalStar = false;
        globalExclude = new HashSet<>();

        worldsBlackList = new HashSet<>();
        /*
         * WORLDS
         */
        ConfigurationSection worlds = getFileConfiguration().getConfigurationSection("worlds");

        if (worlds == null)
            worlds = getFileConfiguration().createSection("worlds");

        for (String key : worlds.getKeys(false)) {

            List<String> keys = worlds.getStringList(key);
            if (keys.size() > 0) {
                Set<Material> excludeMats = new HashSet<>();
                Set<Material> materials = new HashSet<>();

                for (String name : keys) {
                    if (name.equals("*")) {
                        worldsMapStar.put(key, true);
                    }
                    else if (name.startsWith("-")) {
                        Material material = MaterialUtils.getMatByName(name.substring(1, name.length()).trim(), null);
                        if (material != null)
                            excludeMats.add(material);
                    }
                    else {
                        Material material = MaterialUtils.getMatByName(name, null);
                        if (material != null)
                            materials.add(material);
                    }
                }
                worldsMapExclude.put(key, excludeMats);
                worldsMap.put(key, materials);
            }
        }
        int worldAmount = worldsMap.keySet().size();
        BMain.log("Physics by world loaded. (§e%d§f world%s)", worldAmount, worldAmount > 1 ? "s" : "");
        /*
         * GLOBAL
         */
        List<String> list = getFileConfiguration().getStringList("global");
        if (list.contains("*")) {
            for (String name : list) {
                if (name.equals("*")) {
                    globalStar = true;
                }
                else if (name.startsWith("-")) {
                    Material material = MaterialUtils.getMatByName(name.substring(1, name.length()).trim(), null);
                    if (material != null)
                        globalExclude.add(material);
                }
            }
            if (globalStar) {
                BMain.log("Physics global §estar found§f !");
            }
            BMain.log("Physics global loaded. (§e%d§f excluded material%s)", global.size() + globalExclude.size(), (global.size() + globalExclude.size()) > 1 ? "s" : "");
        }
        else {
            for (String name : list) {
                Material material = MaterialUtils.getMatByName(name, null);
                if (material != null)
                    global.add(material);
            }
            BMain.log("Physics global loaded. (§e%d§f material%s)", global.size(), global.size() > 1 ? "s" : "");
        }
        /*
         * BLACKLIST
         */
        List<String> worldNames = getFileConfiguration().getStringList("blacklisted-worlds");
        for (String name : worldNames) {
            worldsBlackList.add(name);
        }
        BMain.log("Physics blacklist loaded. (§e%d§f world%s)", worldsBlackList.size(), worldsBlackList.size() > 1 ? "s" : "");
    }
}