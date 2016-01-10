/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.command;

import fr.creatruth.blocks.command.handle.ACommand;
import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.tools.BiomeTool;
import fr.creatruth.blocks.utils.BiomeUtils;
import fr.creatruth.blocks.messages.Message;
import fr.creatruth.blocks.messages.help.HelpHandler;
import fr.creatruth.blocks.messages.help.PluginHelp;
import fr.creatruth.blocks.player.Perm;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BiomeCmd extends ACommand {

    private static String biomeNames;

    static HelpHandler HELP;

    public static void loadHelp() {
        HELP = new HelpHandler("biome");
        HELP.put("tool", new PluginHelp("/Biome wand §7[§abiome§7[§a,biome§7[§a,...§7]]] [§aradius§7]").setDescription(Message.HELP_BIOME_WAND.getMessage()).setPermission(Perm.BIOME));
        HELP.put("list", new PluginHelp("/Biome list").setDescription(Message.HELP_BIOME_LIST.getMessage()).setPermission(Perm.BIOME));
        HELP.put("biome", new PluginHelp("/Biome §7<§abiome§7[§a,biome§7[§a,...§7]]>").setDescription(Message.HELP_BIOME_SETBIOME.getMessage()).setPermission(Perm.BIOME));
        HELP.put("radius", new PluginHelp("/Biome §7<§aradius§7>").setDescription(Message.HELP_BIOME_SETRADIUS.getMessage()).setPermission(Perm.BIOME));
    }

    @Override
    public void execute() {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (Perm.BIOME.has(player)) {
                /*
                 * TOOL
                 */
                if (args.get(0, "").equalsIgnoreCase("wand") || args.get(0, "").equalsIgnoreCase("w")) {
                    List<Biome> biomes = BiomeUtils.getList(args.get(1, ""));
                    if (biomes.size() == 0)
                        biomes.add(player.getLocation().getBlock().getBiome());

                    int radius = args.getInt(2, 0);
                    int max = Config.getBiomeMaxRadius();
                    boolean byPass = Perm.BIOME_LIMIT.has(player);
                    radius = radius < 0 ?  0 : (radius > max ? (byPass ? (radius > 100 ? 100 : radius) : max) : radius);

                    BiomeTool.add(player, biomes, radius);
                    if (radius > 20) {
                        Message.COMMAND_BIOME_LARGERADIUS.sendAlert(player);
                    }
                    Message.COMMAND_BIOME_RECEIVED.send(player, Message.Type.CLASSIC);
                    return;
                }
                /*
                 * LIST
                 */
                if (args.get(0, "").equalsIgnoreCase("list") || args.get(0, "").equalsIgnoreCase("l")) {
                    Message.COMMAND_BIOME_LIST.send(player, Message.Type.CLASSIC, getBiomeNames());
                    return;
                }
                /*
                 * TOOL IN HAND
                 */
                ItemStack hand = player.getItemInHand();

                if (BiomeTool.isBiomeTool(hand)) {
                    BiomeTool tool = new BiomeTool(player, hand);
                    /*
                     * RADIUS
                     */
                    int radius = args.getInt(0, -1);
                    if (radius >= 0) {
                        int max = Config.getBiomeMaxRadius();
                        boolean byPass = Perm.BIOME_LIMIT.has(player);
                        radius = radius < 0 ?  0 : (radius > max ? (byPass ? (radius > 100 ? 100 : radius) : max) : radius);
                        tool.setRadius(radius);
                        if (tool.getRadius() > 20) {
                            Message.COMMAND_BIOME_LARGERADIUS.sendAlert(player);
                        }
                        Message.COMMAND_BIOME_SETRADIUS.send(player, Message.Type.CLASSIC, radius);
                        return;
                    }
                    /*
                     * BIOME
                     */
                    else {
                        List<Biome> biomes = BiomeUtils.getList(args.get(0, ""));
                        if (biomes.size() > 0) {
                            tool.setBiomes(biomes);
                            Message.COMMAND_BIOME_SETBIOME.send(player, Message.Type.CLASSIC, BiomeUtils.toString(biomes));
                            return;
                        }
                    }
                }

                /*
                 * HELP
                 */
                int page = args.getInt(0, -1);
                if (page == -1) page = args.getInt(1, 1);

                sender.sendMessage(HELP.constructHelp(sender, page));

            }
            else
                Message.COMMAND_ERROR_NOACCESS.sendAlert(sender);
        }
    }

    @Override
    public String name() {
        return "biome";
    }

    private static String getBiomeNames() {
        if (biomeNames == null) {
            List<String> biomes = new ArrayList<>();

            for (Biome b : Biome.values())
                biomes.add(b.name().toLowerCase());

            Collections.sort(biomes);

            StringBuilder builder = new StringBuilder();

            for (int i = 1; i < biomes.size(); i++) {
                builder.append(i % 2 == 0 ? "§e" : "§f");
                builder.append(", ").append(biomes.get(i));
            }

            biomeNames = builder.toString();
        }
        return biomeNames;
    }
}