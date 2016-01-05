/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.listener;

import fr.creatruth.blocks.manager.tools.ItemPattern;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class DropListener extends AListener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        ItemStack itemInHand = player.getItemInHand();

        if (itemInHand.getAmount() != 0 && ItemPattern.hasPattern(ItemPattern.P_BLOCK, event.getItemDrop().getItemStack())) {
            ItemStack drop = itemInHand.clone();
            drop.setAmount(drop.getAmount() + 1);
            event.getItemDrop().setItemStack(drop);
            player.getInventory().remove(itemInHand);
        }
    }
}
