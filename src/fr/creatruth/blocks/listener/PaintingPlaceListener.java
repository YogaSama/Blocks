/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.listener;

import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.type.PaintingItem;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class PaintingPlaceListener extends AListener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = false)
    public void onPaintingPlace(HangingPlaceEvent event) {
        if (event.isCancelled()) return;

        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();

        if (item.getType() == Material.PAINTING) {
            BaseItem bi = BaseItem.toBaseItem(item);
            if (bi != null && bi instanceof PaintingItem) {
                ((PaintingItem) bi).onPlaceEntity(event);
            }
        }
    }
}
