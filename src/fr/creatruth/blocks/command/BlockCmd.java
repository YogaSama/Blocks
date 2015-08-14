/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.command;

import fr.creatruth.blocks.command.handle.ACommand;
import fr.creatruth.blocks.configuration.BlocksListFile;
import fr.creatruth.blocks.inventory.gui.GUIManager;
import fr.creatruth.blocks.inventory.page.PagedInventory;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.ItemBuilder;
import fr.creatruth.blocks.manager.item.SpecialBase;
import fr.creatruth.blocks.manager.tools.Attributes;
import fr.creatruth.blocks.messages.Message;
import fr.creatruth.blocks.messages.help.HelpHandler;
import fr.creatruth.blocks.messages.help.PluginHelp;
import fr.creatruth.blocks.player.Perm;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * /block ((material|id)[:data]) [pseudo]
 */
public class BlockCmd extends ACommand {

    static HelpHandler HELP;

    public static void loadHelp() {
        HELP = new HelpHandler("block");
        HELP.put("classic", new PluginHelp("/Block §7<§amaterial§7[§a:data§7]>").setDescription(Message.HELP_BLOCK_BASE.getMessage()).setPermission(Perm.BLOCK));
        HELP.put("list", new PluginHelp("/Block list").setDescription(Message.HELP_BLOCK_LIST.getMessage()).setPermission(Perm.BLOCK));
        HELP.put("other", new PluginHelp("/Block §7<§amaterial§7[§a:data§7]> [§aplayer§7]").setDescription(Message.HELP_BLOCK_OTHER.getMessage()).setPermission(Perm.BLOCK_OTHER));
        HELP.put("special", new PluginHelp("/Block special").setDescription(Message.HELP_BLOCK_SPECIAL.getMessage()).setPermission(Perm.BLOCK));
    }

    @Override
    public void execute() {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (Perm.BLOCK.has(player)) {
                /*
                 * HELP
                 */
                if (args.size() == 0 || args.get(0, "").equalsIgnoreCase("help"))
                    sender.sendMessage(HELP.constructHelp(sender, 1));
                /*
                 * SPECIAL
                 */
                else if (args.get(0).equalsIgnoreCase("special") || args.get(0).equalsIgnoreCase("s")) {
                    ItemStack item = player.getItemInHand();
                    if (item.getType() != Material.AIR) {
                        BaseItem bi = BaseItem.toBaseItem(item);
                        if (bi != null && bi instanceof SpecialBase) {
                            player.setItemInHand(((SpecialBase) bi).getSpecialBase(bi.getItemBuilder().getData()).getItem());
                            Message.COMMAND_BLOCK_SPECIAL.send(player, Message.Type.BLOCK);
                        }
                        else
                            Message.COMMAND_ERROR_SPECIALNOTAPPLY.sendAlert(player);

                    }
                    else
                        Message.COMMAND_ERROR_NOTVALIDMATERIAL.sendAlert(player);

                }
                /*
                 * LIST
                 */
                else if (args.get(0).equalsIgnoreCase("list") || args.get(0).equalsIgnoreCase("l")) {
                    int maxPage = (int) Math.ceil(((double) BlocksListFile.maxSlot + 1) / (5 * 9));
                    PagedInventory pi = GUIManager.getInstance().set(player, "§4§l> §7Blocks", 6, maxPage);
                    pi.setItems(BlocksListFile.getContent());
                }
                /*
                 * MATERIAL
                 */
                else {
                    Player target = player;
                    if (args.size() > 1 && Perm.BLOCK_OTHER.has(player)) {
                        target = args.getPlayer(1);
                        if (target == null) {
                            Message.COMMAND_ERROR_PLAYERNOTFOUND.sendAlert(player, args.get(1));
                            return;
                        }
                    }
                    Material material = args.getMaterial(0);
                    if (material != null) {

                        BaseItem bi = BaseItem.toBaseItem(new ItemStack(material));
                        byte data = args.getData(0, (byte) 0);
                        String suffix = "";
                        if (bi != null) {
                            if (bi instanceof SpecialBase && !bi.getMaterials().isVisible()) {
                                SpecialBase sti = (SpecialBase) bi;
                                BaseItem sbi = sti.getSpecialBase(data);
                                target.getInventory().addItem(sbi.getItem());
                                suffix = Message.COMMAND_BLOCK_SPECIALSUFFIX.getMessage();
                                data = sbi.getItemBuilder().getData();
                            } else {
                                bi.getItemBuilder().ajustData(data);
                                bi.updateName();
                                target.getInventory().addItem(bi.getItem());
                                data = bi.getItemBuilder().getData();
                            }
                        }
                        else {
                            target.getInventory().addItem(new ItemStack(material));
                            data = 0;
                        }
                        String mat = material.name().toLowerCase() + (data == 0 ? "" : "§f data §b" + data);
                        Message.COMMAND_BLOCK_RECEIVED.send(target, Message.Type.BLOCK, mat, suffix);
                        if (!player.equals(target))
                            Message.COMMAND_BLOCK_SEND.send(player, Message.Type.BLOCK, mat, target.getName());

                    }
                    else
                        Message.COMMAND_ERROR_NOTVALIDMATERIAL.sendAlert(player);
                }
            }
            else
                Message.COMMAND_ERROR_NOACCESS.sendAlert(sender);
        }
    }

    @Override
    public String name() {
        return "block";
    }
}