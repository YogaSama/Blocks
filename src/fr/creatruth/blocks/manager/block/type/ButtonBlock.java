/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.blocks.BMain;

import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.material.Button;

public class ButtonBlock extends BaseBlock {

    public ButtonBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        if (!(getBaseItem().getItem().getData() instanceof Button))
            block.setType(material);

        setData();
    }

    private void setData() {
        if (data == 1) {
            switch (BMain.getData(player).getLastBlockFace()) {
                case EAST:
                    block.setData((byte) 9);
                    return;
                case WEST:
                    block.setData((byte) 10);
                    return;
                case SOUTH:
                    block.setData((byte) 11);
                    return;
                case NORTH:
                    block.setData((byte) 12);
                    return;
            }
        }
        else {
            switch (BMain.getData(player).getLastBlockFace()) {
                case EAST:
                    block.setData((byte) 1);
                    return;
                case WEST:
                    block.setData((byte) 2);
                    return;
                case SOUTH:
                    block.setData((byte) 3);
                    return;
                case NORTH:
                    block.setData((byte) 4);
                    return;
            }
        }
        block.setData((byte) 0);
    }
}
