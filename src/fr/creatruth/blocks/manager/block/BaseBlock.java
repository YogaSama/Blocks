/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

import fr.creatruth.development.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class BaseBlock {

    protected Material  material;
    protected byte      data;

    protected Player    player;
    protected Block     block;

    protected ItemStack cursor;

    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        material = builder.getKey().getMaterial();
        data     = (byte) builder.getKey().getData();
        player   = event.getPlayer();
        block    = event.getBlock();
    }

    public void onPick(Block target, InventoryCreativeEvent event) {
        cursor = event.getCursor();
    }
}
