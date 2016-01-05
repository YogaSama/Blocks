/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.utils;

import fr.creatruth.blocks.messages.Message;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Skull;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InfoUtils {

    public static void getBlockInfo(Block b, Player player, Location l) {
        if (b.getType() == Material.SKULL && ((Skull) b.getState()).getSkullType() == SkullType.PLAYER)
            Message.EVENT_CLICK_SKULL.send(player, Message.Type.CLASSIC, b.getTypeId(), b.getData(), b.getType().name().toLowerCase(), Integer.toString(l.getBlockX()), l.getBlockY(), Integer.toString(l.getBlockZ()), ((Skull) b.getState()).getOwner());

        else
            Message.EVENT_CLICK_INFO.send(player, Message.Type.CLASSIC, b.getTypeId(), b.getData(), b.getType().name().toLowerCase(), Integer.toString(l.getBlockX()), l.getBlockY(), Integer.toString(l.getBlockZ()));

    }
}

