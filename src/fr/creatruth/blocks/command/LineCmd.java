/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.command;

import fr.creatruth.blocks.command.handle.ACommand;
import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.SpecialBase;
import fr.creatruth.blocks.manager.tools.Attributes;
import fr.creatruth.blocks.manager.tools.Face;
import fr.creatruth.blocks.manager.tools.ItemPattern;
import fr.creatruth.blocks.manager.tools.LineBuilder;
import fr.creatruth.blocks.messages.Message;
import fr.creatruth.blocks.messages.help.HelpHandler;
import fr.creatruth.blocks.messages.help.PluginHelp;
import fr.creatruth.blocks.player.Perm;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * /line [size|help|off] [direction] [material|id[:data]]
 */
public class LineCmd extends ACommand { // TODO Transformr un spécial item en ligne actualise la durabilité de l'item.

    static HelpHandler HELP;

    public static void loadHelp() {
        HELP = new HelpHandler("Line");
        HELP.put("base", new PluginHelp("/Line").setDescription(Message.HELP_LINE_DEFAULT.getMessage()).setPermission(Perm.LINE)); 
        HELP.put("classic", new PluginHelp("/Line §7[§asize§7] [§adirection§7]").setDescription(Message.HELP_LINE_BASE.getMessage()).setPermission(Perm.LINE));
        HELP.put("advanced", new PluginHelp("/Line §7[§asize§7] [§adirection§7] [§amaterial§7[§a:data§7]]").setDescription(Message.HELP_LINE_ADVANCED.getMessage()).setPermission(Perm.LINE));
        HELP.put("off", new PluginHelp("/Line off").setDescription(Message.HELP_LINE_OFF.getMessage()).setPermission(Perm.LINE)); 
    }

    @Override
    public void execute() {
        int size = 10;
        Face face = Face._PLAYER;
        BaseItem baseItem = null;
        String mat = "";

        if (sender instanceof Player) {

            if (!Perm.LINE.has(sender)) {
                Message.COMMAND_ERROR_NOACCESS.sendAlert(sender);
                return;
            }
            Player player = (Player) sender;

            if (args.size() > 0) {

                if (args.get(0).equalsIgnoreCase("help")) {
                    sender.sendMessage(HELP.constructHelp(sender, 1));
                    return;
                }

                else if (args.get(0).equalsIgnoreCase("off")) {
                    if (ItemPattern.hasPattern(ItemPattern.P_LINE, player.getItemInHand())) {
                        baseItem = BaseItem.toBaseItem(player.getItemInHand());
                        if (baseItem != null) {
                            baseItem.getItemBuilder().setAttributes(new Attributes(null));
                            baseItem.updateName();
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
                        Material material = args.getMaterial(2);
                        if (material == null) return;

                        mat = material.name();
                        baseItem = BaseItem.toBaseItem(new ItemStack(material));

                        if (baseItem != null) {
                            byte data = args.getData(2, (byte) 0);
                            if (data > 0) {
                                mat += " : " + data;
                                baseItem.getItemBuilder().ajustData(data);
                            }
                            if (baseItem instanceof SpecialBase) {
                                SpecialBase sti = (SpecialBase) baseItem;
                                baseItem = sti.getSpecialBase(data);
                            }
                        }
                    }
                }
            }
            if (args.size() < 3)
                baseItem = BaseItem.toBaseItem(player.getItemInHand());

            if (baseItem != null) {
                if (!Config.getLineBlockBlackList().contains(baseItem.getItemBuilder().getMaterial().name()) || Perm.LINE_BYPASS.has(sender)) {
                    LineBuilder.buildItem(player, baseItem, face, size);

                    String on = Message.COMMAND_LINE_HANDITEM.getMessage();

                    if (args.size() >= 3) {
                        on = mat;
                        player.getInventory().addItem(baseItem.getItem());
                    }

                    Message.COMMAND_LINE_GIVE.sendRaw(player, on, Integer.toString(size), face, Message.LINE.getMessage());
                    player.playSound(player.getLocation(), Sound.NOTE_PIANO, 1F, 1F);
                }
                else
                    Message.COMMAND_LINE_BLACKLISTMAT.sendAlert(player, baseItem.getItemBuilder().getMaterial().name().toLowerCase());
            }
            else
                Message.COMMAND_ERROR_USE.sendAlert(player);
        }
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
