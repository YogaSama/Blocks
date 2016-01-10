/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.listener;

import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.block.BaseBlock;
import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.utils.BlockUtils;
import fr.creatruth.blocks.player.PlayerData;

import fr.creatruth.development.material.MatManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class InventoryCreativeListener extends AListener {

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onCreativeInventory(InventoryCreativeEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getSlotType() != InventoryType.SlotType.QUICKBAR)
            return;

        if (!BMain.getData(player).has(PlayerData.Toggle.MIDDLE))
            return;

        Block target     = BlockUtils.getExactlyTargetBlock(player, 5);
        ItemStack cursor = event.getCursor();

        if (    cursor.getType() == Material.AIR ||
                target.getType() == Material.AIR ||
                cursor.hasItemMeta()    )
            return;

        BaseBlock base = MatManager.getState(cursor.getType()).getBase();
        if (base != null) {
            PickBlockEvent pickEvent = new PickBlockEvent(target, event);
            Bukkit.getPluginManager().callEvent(pickEvent);
            if (!pickEvent.isCancelled()) base.onPick(pickEvent);
        }

        /*BaseItem bi;
        if (event.getCursor().getType() != Material.PAINTING)
            bi = BaseItem.create(target.getType(), target.getData(), null);
        else
            bi = BaseItem.create(Material.PAINTING, (byte) 0, null);
            if (bi != null) bi.onPick(event);*/
    }
}
