/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.utils.WorldUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.block.BlockPlaceEvent;

public class RedstoneBlock extends OrientableBlock {

    private World world;

    public RedstoneBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        world = event.getPlayer().getWorld();
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
