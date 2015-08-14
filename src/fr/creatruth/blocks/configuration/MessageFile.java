/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.configuration;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.messages.Message;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

/**
 * Gestion des messages.
 */
public class MessageFile {

    private static final String FILE_NAME = "messages.yml";

    private File file;
    private FileConfiguration fc;
    private boolean isValid = true;

    /**
     * Initialise le fichier des messages.
     */
    public MessageFile() {
        file = new File(BMain.instance.getDataFolder().getAbsolutePath(), FILE_NAME);
        if (!file.exists()) {
            Config.save(getFileConfiguration(), file);
            BMain.log("\"" + FILE_NAME + "\" create !");
        }
        loadMessages();
    }

    private FileConfiguration getFileConfiguration() {
        return fc == null ? fc = loadConfiguration() : fc;
    }

    private FileConfiguration loadConfiguration() {
        try {
            return YamlConfiguration.loadConfiguration(file);
        } catch (Exception e) {
            BMain.log("Cannot load " + FILE_NAME + " File !");
            isValid = false;
            return null;
        }
    }

    /**
     * Charge les messages, si ils n'existent pas dans la configuration alors
     * utilise les messages par défaut.
     */
    void loadMessages() {
        for (Message m : Message.values()) {
            if (!isValid) return;

            String path = m.name().replace("_", ".").toLowerCase();
            List<String> txt = getFileConfiguration().getStringList(path);

            if (txt.size() == 0) {
                txt = m.getMessages();
                getFileConfiguration().set(path, txt);
                Config.save(getFileConfiguration(), file);
            }
            m.setMessage(txt);
        }
        BMain.log("Messages loaded.");
    }
}