/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

import fr.creatruth.development.item.ItemBuilder;
import org.bukkit.event.block.BlockPlaceEvent;

public class DataBlock extends BaseBlock {

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);

        block.setData(data);
    }
}
