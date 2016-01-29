package fr.creatruth.blocks.block;

import fr.creatruth.api.event.BlocksPlaceEvent;

public interface Placeable {

    void onPlace(BlocksPlaceEvent event);
}
