/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.command.handle;

import fr.creatruth.blocks.command.*;
import fr.creatruth.development.DevCmd;
import org.bukkit.command.CommandSender;

import java.util.HashMap;
import java.util.Map;

public class CommandsHandler {

    private Map<String, ACommand> commands;

    public CommandsHandler() {
        this.initCommands();
    }

    public boolean handleCommand(CommandSender commandSender, String commandName, String[] args) {
        return commands.containsKey(commandName) && commands.get(commandName).prepare(commandSender, args);
    }

    private void put(ACommand cmd) {
        commands.put(cmd.name(), cmd);
    }

    private void initCommands() {
        commands = new HashMap<>();
        put(new BlockCmd());
        put(new VisionCmd());
        put(new HeadCmd());
        put(new BlocksCmd());
        put(new LineCmd());
        put(new ToggleCmd());
        put(new MeterCmd());
        put(new DevCmd());
        put(new BiomeCmd());
    }
}