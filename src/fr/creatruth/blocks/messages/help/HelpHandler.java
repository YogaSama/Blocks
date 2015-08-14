/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.messages.help;

import fr.creatruth.blocks.messages.Message;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.*;

/**
 * Gère l'aide des plugins.
 */
public class HelpHandler {

    private static Map<String, Map<String, PluginHelp>> handlers = new HashMap<>();

    private String command;

    /**
     * Constructeur
     */
    public HelpHandler(String command) {
        this.command = command;
        handlers.put(command, new HashMap<String, PluginHelp>());
    }

    public PluginHelp put(String commandArgs, PluginHelp help) {
        return getCommand().put(commandArgs, help);
    }

    /**
     * Renvois l'aide entière de la commande.
     */
    public Map<String, PluginHelp> getCommand() {
        return handlers.get(command);
    }

    public static Map<String, PluginHelp> getCommand(String name) {
        return handlers.get(name);
    }

    /**
     * Renvois l'aide d'une commande particulière
     */
    public PluginHelp getHelp(String name) {
        Map<String, PluginHelp> cmd = getCommand();
        if (cmd != null) {
            return cmd.get(name);
        }
        return null;
    }

    public List<PluginHelp> getHelp() {
        Collection<PluginHelp> hs = getCommand().values();
        PluginHelp[] table = new PluginHelp[hs.size()];
        return Arrays.asList(hs.toArray(table));
    }

    public String constructHelp(CommandSender sender, int page) {
        return constructHelp(sender, page, "§6------------------- §eBlocks help§6 -------------------");
    }

    /**
     * Envois l'aide par rapport à la page demandée
     */
    public String constructHelp(CommandSender sender, int page, String firstLine) {
        Collection<PluginHelp> hs = getCommand().values();
        List<PluginHelp> cmds = new ArrayList<>();

        for (PluginHelp help : hs) {
            if (help.hasPermission(sender))
                cmds.add(help);
        }

        StringBuilder sb = new StringBuilder();
        int maxPage = 1, max = 4;
        sb.append(firstLine).append("\n");

        if (cmds.size() > 0 ) {

            Collections.sort(cmds);
            maxPage = (int) Math.ceil((double) cmds.size() / max);
            page = page > maxPage ? maxPage : page < 1 ? 1 : page;

            for (int i = (page - 1) * max; i < (page * max) && i < cmds.size(); i++)
                sb.append(cmds.get(i).toString());

        }
        else {
            sb.append(Message.COMMAND_ERROR_HELPNOTFOUND.getMessage());
        }
        sb.append(String.format("\n§6--------------------- §e%d§7/§e%d §6-----------------------", page, maxPage));
        return sb.toString();
    }
}