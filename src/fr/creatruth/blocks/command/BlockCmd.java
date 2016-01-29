/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.command;

import fr.creatruth.blocks.command.handle.ACommand;
import fr.creatruth.blocks.configuration.BlocksListFile;
import fr.creatruth.blocks.messages.Message;
import fr.creatruth.blocks.messages.help.HelpHandler;
import fr.creatruth.blocks.messages.help.PluginHelp;
import fr.creatruth.blocks.player.Perm;
import fr.creatruth.blocks.block.item.ItemBuilder;
import fr.creatruth.blocks.block.item.ItemManager;
import fr.creatruth.blocks.block.material.MatData;
import fr.creatruth.globalapi.ItemAPI;
import fr.creatruth.globalapi.inventory.InventoryCreator;
import fr.creatruth.globalapi.inventory.PagedInventory;
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
        HELP.put("classic", new PluginHelp("/Block §7<§amaterial§7[§a:data§7]>").setLore(Message.HELP_BLOCK_BASE.getMessage()).setPermission(Perm.BLOCK));
        HELP.put("list", new PluginHelp("/Block list").setLore(Message.HELP_BLOCK_LIST.getMessage()).setPermission(Perm.BLOCK));
        HELP.put("other", new PluginHelp("/Block §7<§amaterial§7[§a:data§7]> [§aplayer§7]").setLore(Message.HELP_BLOCK_OTHER.getMessage()).setPermission(Perm.BLOCK_OTHER));
        HELP.put("special", new PluginHelp("/Block special").setLore(Message.HELP_BLOCK_SPECIAL.getMessage()).setPermission(Perm.BLOCK));
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
                        MatData md = new MatData(item.getType(), item.getDurability());
                        ItemBuilder ib = ItemManager.getInstance().getBuilder(md);
                        player.setItemInHand(ib.build());
                        Message.COMMAND_BLOCK_SPECIAL.send(player, Message.Type.BLOCK);
                    }
                    else
                        Message.COMMAND_ERROR_NOTVALIDMATERIAL.sendAlert(player);

                }
                /*
                 * LIST
                 */
                else if (args.get(0).equalsIgnoreCase("list") || args.get(0).equalsIgnoreCase("l")) {
                    InventoryCreator ic  = new InventoryCreator(6, "§4§l> §7Blocks");
                    PagedInventory pi = new PagedInventory(ic, PagedInventory.calculatePages(BlocksListFile.maxSlot + 1, ic.getRows()));
                    pi.setItems(BlocksListFile.getContent());
                    ItemAPI.getHistory(player.getUniqueId()).add(pi);
                }
                /*
                 * GIVE
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
                    MatData matData = args.getMatData(0);
                    if (matData != null) {

                        ItemBuilder ib = ItemManager.getInstance().getBuilder(matData);
                        if (ib == null) {
                            player.sendMessage(""); // TODO
                            return;
                        }
                        target.getInventory().addItem(ib.build());

                        String mat = matData.getMaterial().name().toLowerCase()  +
                                     (ib.getKey().getData() == 0 ? "" : "§f data §b" +
                                     ib.getKey().getData());
                        Message.COMMAND_BLOCK_RECEIVED.send(target, Message.Type.BLOCK, mat);

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