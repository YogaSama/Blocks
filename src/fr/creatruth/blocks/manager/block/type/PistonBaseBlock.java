/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.BaseBlock;

import fr.creatruth.development.item.ItemBuilder;
import org.bukkit.event.block.BlockPlaceEvent;

public class PistonBaseBlock extends BaseBlock {

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);

        if (data == 6 || data == 7 || data == 14 || data == 15)
            block.setData(data);
    }
}
