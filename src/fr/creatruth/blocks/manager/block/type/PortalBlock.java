package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.OrientableBlock;
import fr.creatruth.development.item.ItemBuilder;
import org.bukkit.event.block.BlockPlaceEvent;

public class PortalBlock extends OrientableBlock {

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);
        byte o = getOrientation();
        block.setType(material);
        block.setData((byte) (o % 2 == 0 ? 1 : 2));
    }
}
