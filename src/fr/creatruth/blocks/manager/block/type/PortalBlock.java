package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.blocks.manager.block.OrientableBlock;

public class PortalBlock extends OrientableBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        byte o = getOrientation(event.getPlayer());
        event.getBlock().setType(event.getMaterial());
        event.getBlock().setData((byte) (o % 2 == 0 ? 1 : 2));
    }
}
