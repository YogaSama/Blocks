/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.player;

import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.configuration.PlayerDataFolder;
import fr.creatruth.blocks.messages.Message;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.io.File;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

public class PlayerData {

    private UUID uid;
    private Map<Toggle, Boolean>    toggles;
    private boolean                 sneak;
    private BlockFace               lastBlockFace;

    public PlayerData(UUID uid) {
        this.uid     = uid;
        this.toggles = new HashMap<>();

        PlayerDataFolder pdf = Config.playerDataFolder;
        File file = pdf.getFile(uid);

        for (Toggle toggle : Toggle.values())
            this.toggles.put(toggle, pdf.getValue(file, toggle, Config.isToggle(toggle)));
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uid);
    }

    public boolean has(Toggle toggle) {
        return toggles.get(toggle);
    }

    public boolean isSneaking() {
        return sneak;
    }

    public BlockFace getLastBlockFace() {
        return lastBlockFace;
    }

    public void playSound(Sound sound, float volume) {
        Player player = getPlayer();
        player.playSound(player.getLocation(), sound, volume, 1F);
    }

    public void setToggle(Toggle toggle, boolean value) {
        toggles.put(toggle, value);
        Config.playerDataFolder.updatePlayer(uid, toggle, value);
    }

    public void setSneak(boolean sneak) {
        this.sneak = sneak;
    }

    public void setLastBlockFace(BlockFace blockFace) {
        lastBlockFace = blockFace;
    }

    public enum Toggle {

        INFO(Message.COMMAND_TOGGLE_INFO.getMessage()),
        BLOCK(Message.COMMAND_TOGGLE_BLOCK.getMessage()),
        CHANGE(Message.COMMAND_TOGGLE_CHANGE.getMessage()),
        MIDDLE(Message.COMMAND_TOGGLE_MIDDLE.getMessage());

        private String message;

        Toggle(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getInfo(PlayerData playerData) {
            String state = playerData.has(this) ? Message.COMMAND_COMMON_ENABLED.getMessage() : Message.COMMAND_COMMON_DISABLED.getMessage();
            return MessageFormat.format("§6{1}§7 : §r" + getMessage(), state, this);
        }

        public static Toggle get(String toggle) {
            try {
                return Toggle.valueOf(toggle.trim().toUpperCase(Locale.ENGLISH));
            } catch (Exception e) {
                return null;
            }
        }

        public static void initMessages() {
            INFO.setMessage(Message.COMMAND_TOGGLE_INFO.getMessage());
            BLOCK.setMessage(Message.COMMAND_TOGGLE_BLOCK.getMessage());
            CHANGE.setMessage(Message.COMMAND_TOGGLE_CHANGE.getMessage());
            MIDDLE.setMessage(Message.COMMAND_TOGGLE_MIDDLE.getMessage());
        }
    }
}
