/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.configuration;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.messages.Message;

import java.util.List;

/**
 * Gestion des messages.
 */
public class MessageFile extends AConfigFile {

    /**
     * Initialise le fichier des messages.
     */
    public MessageFile() {
        super("messages.yml", false);
        this.configurable = true;
        if (!this.file.exists()) {
            Config.save(getFileConfiguration(), file);
            BMain.log("\"" + fileName + "\" create !");
        }
    }

    /**
     * Charge les messages, si ils n'existent pas dans la configuration alors
     * utilise les messages par défaut.
     */
    @Override
    public void loadContent() {
        for (Message m : Message.values()) {
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