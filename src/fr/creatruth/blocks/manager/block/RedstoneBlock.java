/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

import fr.creatruth.blocks.manager.utils.WorldUtils;
import fr.creatruth.development.item.ItemBuilder;
import fr.creatruth.development.item.ItemManager;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;

public class RedstoneBlock extends OrientableBlock {

    /* -------------- */
    /* PICK           */
    /* -------------- */

    protected Material on;
    protected Material off;
    protected Material classicItem;

    @Override
    public void onPick(Block target, InventoryCreativeEvent event) {
        super.onPick(target, event);

        if      (cursor.getType() == on         ) setCursor(event, on);
        else if (cursor.getType() == classicItem) setCursor(event, classicItem);
    }

    private void setCursor(InventoryCreativeEvent event, Material material) {
        if (block.getType() == on)
            event.setCursor(ItemManager.getInstance().getBuilder(material, (byte) 0).build());

        else if (block.getType() == off)
            event.setCursor(ItemManager.getInstance().getBuilder(material, (byte) 1).build());
    }

    /* -------------- */
    /* PLACE          */
    /* -------------- */

    private World world;

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);
        this.world = event.getPlayer().getWorld();
    }

    public void apply(Material material) {
        try {
            WorldUtils.setStaticWorld(world, true);
            block.setType(material);
            WorldUtils.setStaticWorld(world, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
