package fr.creatruth.blocks.block;

import fr.creatruth.api.event.PickBlockEvent;

public interface Pickable {

    void onPick(PickBlockEvent event);
}
