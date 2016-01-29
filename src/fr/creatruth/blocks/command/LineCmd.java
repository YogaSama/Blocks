/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.command;

import fr.creatruth.blocks.command.handle.ACommand;
import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.tools.Face;
import fr.creatruth.blocks.messages.Message;
import fr.creatruth.blocks.messages.help.HelpHandler;
import fr.creatruth.blocks.messages.help.PluginHelp;
import fr.creatruth.blocks.player.Perm;

import fr.creatruth.blocks.block.item.ItemBuilder;
import org.bukkit.entity.Player;

/**
 * /line [size|help|off] [direction] [material|id[:data]]
 */
public class LineCmd extends ACommand { // TODO Transformer un spécial item en ligne actualise la durabilité de l'item.

    static HelpHandler HELP;

    public static void loadHelp() {
        HELP = new HelpHandler("Line");
        HELP.put("base", new PluginHelp("/Line").setLore(Message.HELP_LINE_DEFAULT.getMessage()).setPermission(Perm.LINE));
        HELP.put("classic", new PluginHelp("/Line §7[§asize§7] [§adirection§7]").setLore(Message.HELP_LINE_BASE.getMessage()).setPermission(Perm.LINE));
        HELP.put("advanced", new PluginHelp("/Line §7[§asize§7] [§adirection§7] [§amaterial§7[§a:data§7]]").setLore(Message.HELP_LINE_ADVANCED.getMessage()).setPermission(Perm.LINE));
        HELP.put("off", new PluginHelp("/Line off").setLore(Message.HELP_LINE_OFF.getMessage()).setPermission(Perm.LINE));
    }

    @Override
    public void execute() {
        int length = 10;
        Face face = Face._PLAYER;
        ItemBuilder builder = null;
        String mat = "";

        if (!(sender instanceof Player)) return;

        if (!Perm.LINE.has(sender)) {
            Message.COMMAND_ERROR_NOACCESS.sendAlert(sender);
            return;
        }

        /*Player player = (Player) sender;

        if (args.size() > 0) {

            if (args.get(0).equalsIgnoreCase("help")) {
                sender.sendMessage(HELP.constructHelp(sender, 1));
                return;
            }

            else if (args.get(0).equalsIgnoreCase("off")) {
                if (ItemPattern.hasPattern(ItemPattern.P_LINE, player.getItemInHand())) {
                    builder = BaseItem.toBaseItem(player.getItemInHand());
                    if (builder != null) {
                        builder.getItemBuilder().setAttributes(new Attributes(null));
                        builder.updateName();
                        Message.COMMAND_LINE_OFF.send(sender, Message.Type.BLOCK);
                    }
                }
                return;
            }

            size = getSize(player);
            if (args.size() > 1) {
                face = Face.getByKey(args.get(1), null);

                if (face == null) {
                    Message.COMMAND_LINE_VALIDFACES.send(sender, Message.Type.BLOCK, Face.getValidFaces());
                    return;
                }

                if (args.size() > 2) {
                    MatData matData = args.getKey(2);
                    if (matData == null) return;

                    mat = matData.getMaterial().name();
                    builder = ItemManager.getInstance().getBuilder(matData);
                }
            }
        }

        if (args.size() < 3)
            builder = BaseItem.toBaseItem(player.getItemInHand());

        if (builder != null) {
            if (    !Config.getLineBlockBlackList().contains(builder.getKey().getMaterial().name()) ||
                    Perm.LINE_BYPASS.has(sender)) {

                ItemBuilder ib = baseItem.getItemBuilder();
                Attribute attribute = new Attribute(Attribute.Type.LINE);
                attribute.add(length);
                attribute.add(face == Face._PLAYER ? Face.getByOrientation(player) : face);
                ib.setAttribute(attribute);
                baseItem.updateName();
                String on = Message.COMMAND_LINE_HANDITEM.getMessage();

                if (args.size() >= 3) {
                    on = mat;
                    player.getInventory().addItem(builder.build());
                }

                Message.COMMAND_LINE_GIVE.sendRaw(player, on, Integer.toString(size), face, Message.LINE.getMessage());
                player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1F, 1F);
            }
            else
                Message.COMMAND_LINE_BLACKLISTMAT.sendAlert(player, builder.getKey().getMaterial().name().toLowerCase());
        }
        else
            Message.COMMAND_ERROR_USE.sendAlert(player);*/
    }

    private int getSize(Player player) {
        int size = args.getInt(0, 20);
        size = size < 1 ? 1 : size;
        return size <= Config.getLineSizeLimit() || Perm.LINE_BYPASS.has(player) ? size : Config.getLineSizeLimit();
    }

    @Override
    public String name() {
        return "line";
    }
}
