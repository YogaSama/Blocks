/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.OrientableBlock;
import fr.creatruth.blocks.manager.item.BaseItem;

import org.bukkit.event.block.BlockPlaceEvent;

public class BedBlock extends OrientableBlock {

    public BedBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        block.setType(material);
        byte newData = getBaseItem().getItemBuilder().getData();
        block.setData((byte) ((newData * 4) + (data < 2 ? getOrientation() : getReverseOrientation())));
    }
}
