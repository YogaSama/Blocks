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
import org.bukkit.material.Pumpkin;

public class PumpkinBlock extends OrientableBlock {

    public PumpkinBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        if (getBaseItem().getItem().getData() instanceof Pumpkin) {
            if (data >= 4)
                block.setData(data);
        }
        else {
            block.setType(getBaseItem().getItemBuilder().getMaterial());

            if (data >= 4)
                block.setData(data);

            else
                block.setData(getReverseOrientation());
        }
    }
}
