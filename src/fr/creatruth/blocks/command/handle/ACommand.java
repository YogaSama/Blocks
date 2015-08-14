/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.command.handle;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.command.argument.Arguments;
import org.bukkit.command.CommandSender;

public abstract class ACommand {

    protected Arguments args;
    protected CommandSender sender;

    public boolean prepare(CommandSender sender, String[] args) {
        this.sender = sender;
        this.args = new Arguments(args);
        try {
            execute();
        } catch (Exception e) {
            e.printStackTrace();
            sender.sendMessage(BMain.PREFIX + "§eError [see console]");
        }
        return true;
    }

    protected abstract void execute();

    public abstract String name();

    public String usage() {
        return BMain.instance.getCommand(this.name()).getUsage();
    }
}