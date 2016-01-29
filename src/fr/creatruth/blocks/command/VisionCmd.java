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
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VisionCmd extends ACommand {

    static HelpHandler HELP;

    public static void loadHelp() {
        HELP = new HelpHandler("vision");
        HELP.put("aqua", new PluginHelp("/vision aqua").setLore(Message.HELP_VISION_AQUA.getMessage()).setPermission(Perm.VISION_AQUA));
        HELP.put("night", new PluginHelp("/vision night").setLore(Message.HELP_VISION_NIGHT.getMessage()).setPermission(Perm.VISION_NIGHT));
    }

    @Override
    public void execute() {
        Player player = (Player) sender;

        if (args.get(0, "").equalsIgnoreCase("night")) {
            if (Perm.VISION_NIGHT.has(player)) {
                nightVision(player, !player.hasPotionEffect(PotionEffectType.NIGHT_VISION));
            }
            else
                Message.COMMAND_ERROR_NOACCESS.sendAlert(sender);
        }
        else if (args.get(0, "").equalsIgnoreCase("aqua")) {
            if (Perm.VISION_AQUA.has(player)) {
                ItemStack helmet = player.getInventory().getHelmet();
                aquaVision(player, helmet == null || helmet.getEnchantmentLevel(Enchantment.DEPTH_STRIDER) < 2);
            }
            else
                Message.COMMAND_ERROR_NOACCESS.sendAlert(sender);
        }
        else
            sender.sendMessage(HELP.constructHelp(sender, 1));
    }

    @Override
    public String name() {
        return "vision";
    }

    private void nightVision(Player player, boolean enable) {
        if (enable)
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 10000000, 1));
        else player.removePotionEffect(PotionEffectType.NIGHT_VISION);

        player.playSound(player.getLocation(), Sound.LAVA_POP, 5.0F, 5.0F);

        if (enable)
            Message.COMMAND_VISION_NIGHT.send(player, Message.Type.CLASSIC, Message.COMMAND_COMMON_ENABLED.getMessage());
        else
            Message.COMMAND_VISION_NIGHT.send(player, Message.Type.CLASSIC, Message.COMMAND_COMMON_DISABLED.getMessage());
    }

    private void aquaVision(Player player, boolean enable) {
        if (enable) {
            player.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
            ItemStack h = player.getInventory().getHelmet();
            h.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 3);
            h.addUnsafeEnchantment(Enchantment.WATER_WORKER, 3);
            h.addUnsafeEnchantment(Enchantment.OXYGEN, 3);
        }
        else
            player.getInventory().setHelmet(null);

        player.playSound(player.getLocation(), Sound.DRINK, 5.0F, 5.0F);

        if (enable)
            Message.COMMAND_VISION_AQUA.send(player, Message.Type.CLASSIC, Message.COMMAND_COMMON_ENABLED.getMessage());
        else
            Message.COMMAND_VISION_AQUA.send(player, Message.Type.CLASSIC, Message.COMMAND_COMMON_DISABLED.getMessage());
    }
}
