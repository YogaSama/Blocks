package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.development.material.MatData;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.material.Bed;

public class BedItemBlock extends BaseBlock {

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        if (event.getData() == 0) return;

        Block block    = event.getBlock();
        Bed bed        = (Bed) block.getState().getData();
        BlockFace face = bed.getFacing();

        if (event.getData() == 1) {
            if (block.getData() % 2 == 0)
                block.getRelative(face).setData((byte) (2 - block.getData()));
            else
                block.getRelative(face).setData((byte) (4 - block.getData()));
        }

        else { // data 2
            if (block.getData() % 2 == 0)
                block.setData((byte) (10 - block.getData()));
            else
                block.setData((byte) (12 - block.getData()));
        }
    }

    @Override
    public void onPick(PickBlockEvent event) {
        Block target   = event.getTarget();
        Bed bed        = (Bed) target.getState().getData();
        BlockFace face = bed.getFacing();

        if (target.getRelative(bed.isHeadOfBed() ? face.getOppositeFace() : face).getType() != Material.BED_BLOCK) {
            MatData md = new MatData(target.getType(), (byte) (target.getData() < 8 ? 0 : 8));
            event.setCursor(itemManager().getBuilder(md).build());
        }
    }
}
