/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.command;

import fr.creatruth.blocks.messages.help.HelpHandler;
import fr.creatruth.blocks.messages.help.PluginHelp;
import fr.creatruth.blocks.player.Perm;
import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.command.handle.ACommand;
import fr.creatruth.blocks.messages.Message;
import fr.creatruth.blocks.player.PlayerData;
import org.bukkit.entity.Player;

public class ToggleCmd extends ACommand {

    static HelpHandler HELP;

    public static void loadHelp() {
        HELP = new HelpHandler("Toggle");
        HELP.put("list", new PluginHelp("/Toggle list").setDescription(Message.HELP_TOGGLE_LIST.getMessage()));
        HELP.put("list.other", new PluginHelp("/Toggle list §7[§aplayer§7]").setDescription(Message.HELP_TOGGLE_OTHER.getMessage()).setPermission(Perm.TOGGLE_LIST_OTHER));
        HELP.put("info", new PluginHelp("/Toggle info §7[§aplayer§7]").setDescription(Message.HELP_TOGGLE_INFO.getMessage()));
        HELP.put("change", new PluginHelp("/Toggle change §7[§aplayer§7]").setDescription(Message.HELP_TOGGLE_CHANGE.getMessage()));
        HELP.put("middleclick", new PluginHelp("/Toggle middle §7[§aplayer§7]").setDescription(Message.HELP_TOGGLE_MIDDLE.getMessage()));
        HELP.put("block", new PluginHelp("/Toggle block §7[§aplayer§7]").setDescription(Message.HELP_TOGGLE_BLOCK.getMessage()));
        HELP.put("all", new PluginHelp("/Toggle all §7<§aon§7|§aoff§7> §7[§aplayer§7]").setDescription(Message.HELP_TOGGLE_ALL.getMessage()));
    }

    @Override
    public void execute() {
        Player player = (Player) sender;
        PlayerData targetData;
        Player target = player;
        /*
         * ALL
         */
        if (args.get(0, "").equalsIgnoreCase("*") || args.get(0, "").equalsIgnoreCase("all")) {
            if (args.size() > 2) {
                target = setTarget(player, 2, Perm.TOGGLE_OTHER);
                if (target == null) return;
            }

            targetData = BMain.getData(target);

            if (args.get(1, "").equalsIgnoreCase("on")) {
                target.sendMessage(Message.LINE.getMessage());
                for (PlayerData.Toggle toggle : PlayerData.Toggle.values()) {
                    setToggle(target, targetData, toggle, true);
                }
                target.sendMessage(Message.LINE.getMessage());
            }
            else if (args.get(1, "").equalsIgnoreCase("off")) {
                target.sendMessage(Message.LINE.getMessage());
                for (PlayerData.Toggle toggle : PlayerData.Toggle.values()) {
                    setToggle(target, targetData, toggle, false);
                }
                target.sendMessage(Message.LINE.getMessage());
            }
            else {
                Message.COMMAND_ERROR_STATE.sendAlert(player);
            }
            return;
        }
        /*
         * LIST
         */
        if (args.get(0, "").equalsIgnoreCase("list")) {
            if (args.size() > 1) {
                target = setTarget(player, 1, Perm.TOGGLE_LIST_OTHER);
                if (target == null) return;
            }
            targetData = BMain.getData(target);

            if (player == target)
                Message.COMMAND_TOGGLE_LIST.send(player, Message.Type.BLOCK);
            else
                Message.COMMAND_TOGGLE_LISTOTHER.send(player, Message.Type.BLOCK, target.getName());

            player.sendMessage(Message.LINE.getMessage());
            for (PlayerData.Toggle toggle : PlayerData.Toggle.values()) {
                player.sendMessage(toggle.getInfo(targetData));
            }
            player.sendMessage(Message.LINE.getMessage());
            return;
        }
        /*
         * TOGGLE
         */
        PlayerData.Toggle toggle = PlayerData.Toggle.get(args.get(0));

        if (toggle != null && args.size() > 1) {
            target = setTarget(player, 1, Perm.TOGGLE_OTHER);
            if (target == null) return;
        }
        if (toggle == null) {
            int page =              args.getInt(0, -1);
            if (page == -1) page =  args.getInt(1, 1);

            player.sendMessage(HELP.constructHelp(player, page));
        }
        else {
            target.sendMessage(Message.LINE.getMessage());
            setToggle(target, BMain.getData(target), toggle);
            target.sendMessage(Message.LINE.getMessage());

            if (player != target) {
                Message.COMMAND_TOGGLE_CHANGEDOTHER.send(player, Message.Type.BLOCK, toggle.name(), target.getName());
            }
        }
    }

    @Override
    public String name() {
        return "toggle";
    }

    private void setToggle(Player player, PlayerData playerData, PlayerData.Toggle toggle) {
        setToggle(player, playerData, toggle, toggle != null && !playerData.has(toggle));
    }

    private void setToggle(Player player, PlayerData playerData, PlayerData.Toggle toggle, boolean on) {
        playerData.setToggle(player, toggle, on);
        player.sendMessage(toggle.getInfo(playerData));
    }

    private Player setTarget(Player player, int index, Perm perm) {
        Player target = args.getPlayer(index);
        if (!perm.has(player)) {
            target = player;
        }
        else if (target == null) {
            Message.COMMAND_ERROR_PLAYERNOTFOUND.sendAlert(player, args.get(index));
            return null;
        }
        return target;
    }
}