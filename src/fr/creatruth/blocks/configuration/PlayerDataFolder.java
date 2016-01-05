/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.configuration;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.player.PlayerData;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public class PlayerDataFolder {

    private static final String FILE_NAME = "playerdata";

    private File file;

    /**
     * Initialise le dossier de sauvegarde des joueurs.
     */
    public PlayerDataFolder() {
        getDirectory();
    }

    public File getDirectory() {
        if (file == null) {
            file = new File(BMain.instance.getDataFolder().getAbsolutePath(), FILE_NAME);
            if (file.mkdirs()) BMain.log("\"" + FILE_NAME + "\" create !");
        }
        return file;
    }

    private FileConfiguration loadConfiguration(File file) {
        try {
            return YamlConfiguration.loadConfiguration(file);
        } catch (Exception e) {
            BMain.log("Cannot load " + FILE_NAME + " File !");
            return null;
        }
    }

    public File getFile(UUID uuid) {
        return uuid != null ? new File(getDirectory().getAbsolutePath(), uuid + ".yml") : null;
    }

    public void updatePlayer(UUID uuid, PlayerData.Toggle toggle, boolean value) {
        if (uuid == null) return;

        File file = getFile(uuid);
        FileConfiguration fc = loadConfiguration(file);

        if (fc != null) {
            fc.set("toggles." + toggle.name(), value);
            Config.save(fc, file);
        }
    }

    public boolean getValue(File playerFile, PlayerData.Toggle toggle, boolean defaultValue) {
        if (playerFile != null) {
            FileConfiguration fc = loadConfiguration(playerFile);
            if (fc != null) {
                ConfigurationSection cs = fc.getConfigurationSection("toggles");
                if (cs != null && cs.contains(toggle.name())) {
                    return cs.getBoolean(toggle.name());
                }
            }
        }
        return defaultValue;
    }
}
