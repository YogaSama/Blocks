/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.command;

import fr.creatruth.blocks.command.handle.ACommand;
import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.messages.help.Help;
import fr.creatruth.blocks.messages.help.HelpHandler;
import fr.creatruth.blocks.messages.help.PluginHelp;
import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.messages.Message;
import fr.creatruth.blocks.player.Perm;
import org.bukkit.entity.Player;

public class BlocksCmd extends ACommand {

    static HelpHandler HELP;

    public static void loadHelp() {
        HELP = new HelpHandler("blocks");
        HELP.put("biome", new PluginHelp("/Biome help").setDescription(Message.HELP_SHOW.getMessage("/Biome")).setPermission(Perm.BIOME));
        HELP.put("block", new PluginHelp("/Block help").setDescription(Message.HELP_SHOW.getMessage("/Block")).setPermission(Perm.BLOCK));
        HELP.put("head", new PluginHelp("/Head help").setDescription(Message.HELP_SHOW.getMessage("/Head")).setPermission(Perm.HEAD));
        HELP.put("line", new PluginHelp("/Line help").setDescription(Message.HELP_SHOW.getMessage("/Line")).setPermission(Perm.LINE));
        HELP.put("meter", MeterCmd.HELP.getHelp("meter")); 
        HELP.put("toggle", new PluginHelp("/Toggle help").setDescription(Message.HELP_SHOW.getMessage("/Toggle")));
        HELP.put("reload", new PluginHelp("/Blocks reload").setDescription(Message.HELP_RELOAD.getMessage()).setPermission(Perm.RELOAD));
        HELP.put("vision", new PluginHelp("/Vision help").setDescription(Message.HELP_SHOW.getMessage("/Vision")));
        HELP.put("change", new PluginHelp("§7#" + Message.HELP_BLOCKS_HOWTO.getMessage()).setDescription(Message.HELP_BLOCKS_ANSWER.getMessage()));
        HELP.put("version", new PluginHelp("§8#§eVersion :").setDescription("§7 " + BMain.getVersion()));
        HELP.put("developer", new PluginHelp("§8#§eDeveloper :").setDescription("§7 Yoga_Sama"));
        HELP.put("website", new PluginHelp("§8#§eWebsite :").setDescription("§7 http://creatruth.fr/"));
    }

    @Override
    public void execute() {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            /*
             * RELOAD
             */
            if (args.get(0, "").equalsIgnoreCase("reload")) {
                if (Perm.RELOAD.has(player)) {
                    Config.load();
                    Help.initHelp();
                    Message.COMMAND_BLOCKS_RELOAD.send(player, Message.Type.BLOCK);
                }
                else
                    Message.COMMAND_ERROR_NOACCESS.sendAlert(sender);
            }
            /*
             * HELP
             */
            else {
                int page =              args.getInt(0, -1);
                if (page == -1) page =  args.getInt(1, 1);

                sender.sendMessage(HELP.constructHelp(sender, page));
            }
        }
        else {
            if (args.get(0, "").equalsIgnoreCase("reload")) {
                Config.load();
                Help.initHelp();
                BMain.log("Blocks reload !");
            }
        }
    }

    @Override
    public String name() {
        return "blocks";
    }
}