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
import fr.creatruth.blocks.manager.tools.Meter;
import fr.creatruth.blocks.player.Perm;
import org.bukkit.entity.Player;

public class MeterCmd extends ACommand {

    static HelpHandler HELP;

    public static void loadHelp() {
        HELP = new HelpHandler("meter");
        HELP.put("meter", new PluginHelp("/Meter").setDescription(Message.HELP_METER.getMessage()).setPermission(Perm.METER));
    }

    @Override
    public void execute() {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (Perm.METER.has(player)) {
                if (args.get(0, "").equalsIgnoreCase("help")) {
                    sender.sendMessage(HELP.constructHelp(sender, 1));
                }
                else {
                    Meter.add(player);
                    Message.COMMAND_METER_RECEIVE.send(player, Message.Type.CLASSIC);
                }
            }
            else
                Message.COMMAND_ERROR_NOACCESS.sendAlert(sender);
        }
    }

    @Override
    public String name() {
        return "meter";
    }
}
