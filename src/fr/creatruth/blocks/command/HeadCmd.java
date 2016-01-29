/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.command;

import fr.creatruth.blocks.command.handle.ACommand;
import fr.creatruth.blocks.messages.Message;
import fr.creatruth.blocks.messages.help.HelpHandler;
import fr.creatruth.blocks.messages.help.PluginHelp;
import fr.creatruth.blocks.player.Perm;
import fr.creatruth.blocks.utils.SkullUtils;

import org.bukkit.entity.Player;

public class HeadCmd extends ACommand {

    static HelpHandler HELP;

    public static void loadHelp() {

        HELP = new HelpHandler("head");
        HELP.put("own", new PluginHelp("/Head").setLore(Message.HELP_HEAD_OWN.getMessage()).setPermission(Perm.HEAD));
        HELP.put("other", new PluginHelp("/Head §7[§aplayer§7]").setLore(Message.HELP_HEAD_OTHER.getMessage()).setPermission(Perm.HEAD));
    }

    @Override
    public void execute() {
        Player player = (Player) sender;

        if (Perm.HEAD.has(player)) {
            if (args.size() == 0) {
                player.getInventory().addItem(SkullUtils.getHead(player.getName()));
                Message.COMMAND_HEAD_YOURHEAD.send(sender, Message.Type.CLASSIC);
            }
            else if (args.size() >= 1) {
                if (args.get(0).equalsIgnoreCase("help")) {
                    sender.sendMessage(HELP.constructHelp(sender, 1));
                }
                else {
                    player.getInventory().addItem(SkullUtils.getHead(args.get(0)));
                    Message.COMMAND_HEAD_HEADRECEIVED.send(sender, Message.Type.CLASSIC, args.get(0));
                }
            }
        }
        else
            Message.COMMAND_ERROR_NOACCESS.sendAlert(sender);
    }

    @Override
    public String name() {
        return "head";
    }
}
