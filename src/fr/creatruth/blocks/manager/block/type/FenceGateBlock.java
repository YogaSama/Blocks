/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.OrientableBlock;

import fr.creatruth.development.item.ItemBuilder;
import org.bukkit.event.block.BlockPlaceEvent;

public class FenceGateBlock extends OrientableBlock {

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);

        block.setType(material);
        block.setData((byte) (getOrientation() + (builder.getKey().getData() * 4)));
    }
}
