package fr.creatruth.blocks.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.block.BaseBlock;
import org.bukkit.Material;

public class PistonBlock extends BaseBlock {

    @Override
    public void onPick(PickBlockEvent event) {
        if (event.isTargetType(Material.PISTON_EXTENSION)) {
            event.setCursor(itemManager().getBuilder(event.getTarget().getType(), (byte) 0).build());
        }
    }

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        byte data = event.getBlock().getData();
        event.getBlock().setType(event.getMaterial());
        event.getBlock().setData((byte) (event.getData() + data));
    }
}
