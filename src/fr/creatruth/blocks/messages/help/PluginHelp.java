/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.messages.help;

import fr.creatruth.blocks.player.Perm;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PluginHelp implements Comparable<PluginHelp> {

    private String command;
    private String description;
    private Perm permission;

    public PluginHelp() {
        this("§c???");
    }

    public PluginHelp(String command) {
        this.command = " " + command;
        this.description = "§cPas de description.";
        this.permission = null;
    }

    public PluginHelp setCommand(String command) {
        this.command = command;
        return this;
    }

    public PluginHelp setDescription(String description) {
        this.description = description;
        return this;
    }

    public PluginHelp setPermission(Perm permission) {
        this.permission = permission;
        return this;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }

    public boolean hasPermission(CommandSender sender) {
        return permission == null || permission.has(sender);
    }

    @Override
    public int compareTo(PluginHelp help) {
        return this.command.compareTo(help.command);
    }

    @Override
    public String toString() {
        return "\n§a" + command + "\n§f" + description;
    }
}