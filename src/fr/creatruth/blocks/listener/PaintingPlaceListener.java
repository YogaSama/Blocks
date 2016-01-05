/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.listener;

import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.blocks.manager.block.type.PaintingBlock;

import fr.creatruth.blocks.manager.tools.ItemPattern;
import fr.creatruth.blocks.manager.utils.ItemUtils;
import fr.creatruth.development.item.ItemManager;
import fr.creatruth.development.material.MatData;
import fr.creatruth.development.material.MatManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class PaintingPlaceListener extends AListener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPaintingPlace(HangingPlaceEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getItemInHand();

        if (item.getType() == Material.PAINTING) {

            MatData md;
            if (!item.hasItemMeta())
                md = new MatData(item.getTypeId(), item.getDurability());

            else if (ItemPattern.hasPattern(ItemPattern.P_BLOCK, item)) {
                md = ItemPattern.getMatData(ItemUtils.getDisplayName(item));
            }
            else
                return;

            BaseBlock base = MatManager.getState(item.getType()).getBase();

            if (base != null && base instanceof PaintingBlock) {
                ((PaintingBlock) base).onPlaceEntity(ItemManager.getInstance().getBuilder(md), event);
            }
        }
    }
}
