/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.listener;

import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.manager.utils.BlockUtils;
import fr.creatruth.blocks.player.PlayerData;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryType;

public class InventoryCreativeListener extends AListener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = false)
    public void onCreativeInventory(final InventoryCreativeEvent event) {
        if (event.isCancelled()) return;

        Player player = (Player) event.getWhoClicked();

        if (event.getSlotType() != InventoryType.SlotType.QUICKBAR)
            return;

        if (BMain.getData(player).has(PlayerData.Toggle.MIDDLE)) {
            Block target = BlockUtils.getExactlyTargetBlock(player, 5);

            if (event.getCursor().getType() != Material.AIR && target.getType() != Material.AIR) {

                BaseItem bi;
                if (event.getCursor().getType() != Material.PAINTING)
                    bi = BaseItem.create(target.getType(), target.getData(), null);

                else
                    bi = BaseItem.create(Material.PAINTING, (byte) 0, null);

                if (bi != null)
                    bi.onPick(event);
            }
        }
    }
}
