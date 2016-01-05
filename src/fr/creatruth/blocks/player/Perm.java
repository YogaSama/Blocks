/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;

public enum Perm {

    BIOME("biome"),
    BIOME_LIMIT("biome.limit"),

    BLOCK("block"),
    BLOCK_OTHER("block.other"),

    RELOAD("reload"),

    LINE("line"),
    LINE_BYPASS("line.bypass"),

    METER("meter"),

    HEAD("head"),

    VISION_NIGHT("vision.night"),
    VISION_AQUA("vision.aqua"),

    TOGGLE_LIST_OTHER("toggle.list.other"),
    TOGGLE_OTHER("toggle.other");

    private String name;

    Perm(String name) {
        this.name = "blocks." + name;
    }

    public String getName() {
        return this.name;
    }

    public boolean has(CommandSender sender) {
        return !(sender instanceof Player) || sender.hasPermission(name) || isDev((Player) sender);
    }

    public static boolean isDev(Player player) {
        return true || player.getUniqueId() != null && player.getUniqueId().toString().equals("83ccd6f0-931f-4ec3-a89a-c64b5e3c29b4");
    } // FIXME

    /*
     * BUILD
     */

    private static final ItemStack HAND_TEST = new ItemStack(Material.AIR);
    private static final PluginManager pluginManager = Bukkit.getPluginManager();

    public static boolean canBuild(Player player, Location loc) {

        Block clicked = loc.getBlock();

        PlayerInteractEvent interact = new PlayerInteractEvent(player, Action.LEFT_CLICK_BLOCK, HAND_TEST, clicked, BlockFace.SELF);
        pluginManager.callEvent(interact);

        if(!interact.isCancelled()) {
            interact.setCancelled(true);
            BlockPlaceEvent place = new BlockPlaceEvent(clicked, clicked.getState(), clicked, HAND_TEST, player, true);
            pluginManager.callEvent(place);

            if (place.isCancelled()) {
                place.setCancelled(true);
                return false;
            }
            return true;
        }
        return false;
    }
}
