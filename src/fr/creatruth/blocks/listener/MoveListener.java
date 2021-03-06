/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.listener;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

public class MoveListener extends AListener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onMove(PlayerMoveEvent event) {
        if (hasMoved(event.getFrom(), event.getTo())) {
            ItemStack item = event.getPlayer().getItemInHand();

            /*if (MeterItem.isMeter(item)) {
                MeterItem meter = new MeterItem(item);
                meter.updateMeter(event.getTo().toVector());
            }*/ // FIXME
        }
    }

    private boolean hasMoved(final Location fromLoc, final Location toLoc) {
        return !(fromLoc.getWorld().equals(toLoc.getWorld()) &&
                fromLoc.getBlockX() == toLoc.getBlockX() &&
                fromLoc.getBlockY() == (toLoc.getBlockY()) &&
                fromLoc.getBlockZ() == toLoc.getBlockZ());
    }
}