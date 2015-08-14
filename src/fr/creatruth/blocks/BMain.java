/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks;

import fr.creatruth.blocks.listener.AListener;
import fr.creatruth.blocks.command.handle.CommandsHandler;
import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.messages.help.Help;
import fr.creatruth.blocks.player.PlayerData;
import fr.creatruth.blocks.player.PlayerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class BMain extends JavaPlugin {

    public static BMain instance;
    public static final String PREFIX = "§5§lBlocks§7 > §r";

    private CommandsHandler commandsHandler;
    private PlayerDataHandler playerDataHandler;

    public static PlayerData getData(Player player) {
        return instance.playerDataHandler.getData(player);
    }

    public static void removePlayerData(Player player) {
        instance.playerDataHandler.remove(player);
    }

    @Override
    public void onEnable() {
        instance = this;
        commandsHandler = new CommandsHandler();
        playerDataHandler = new PlayerDataHandler();

        Config.load();
        Help.initHelp();
        AListener.enable();
    }

    @Override
    public void onDisable() {
        for (Player  online : Bukkit.getOnlinePlayers()) {
            online.closeInventory();
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return this.commandsHandler.handleCommand(sender, cmd.getName(), args);
    }

    public static String getVersion() {
        PluginDescriptionFile pl = instance.getDescription();
        return pl.getVersion();
    }

    public static void log(String message) {
        Bukkit.getConsoleSender().sendMessage(PREFIX + message);
    }

    public static void log(String format, Object... args) {
        Bukkit.getConsoleSender().sendMessage(PREFIX + String.format(format, args));
    }
}