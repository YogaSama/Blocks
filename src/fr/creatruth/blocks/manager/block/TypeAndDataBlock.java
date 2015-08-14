/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

import fr.creatruth.blocks.manager.item.BaseItem;
import org.bukkit.event.block.BlockPlaceEvent;

public class TypeAndDataBlock extends BaseBlock {

    public TypeAndDataBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        block.setType(material);
        block.setData(data);
    }
}

