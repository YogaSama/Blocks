/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.manager.block.OrientableBlock;
import fr.creatruth.blocks.player.PlayerData;
import fr.creatruth.development.material.MatData;

import org.bukkit.Material;

public class CocoaBlock extends OrientableBlock {

    @Override
    public void onPick(PickBlockEvent event) {
        if (event.getCursor().getDurability() == 3 && event.isTargetType(Material.COCOA)) {
            MatData md = new MatData(event.getCursor().getType(), (byte) (event.getTarget().getData() >> 2 << 2));
            event.setCursor(itemManager().getBuilder(md).build());
        }
    }

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        event.getBlock().setType(event.getMaterial());

        PlayerData pd = BMain.getData(event.getPlayer());
        switch (pd.getLastBlockFace()) {
            case UP:
            case DOWN:
            case SELF: //?
                event.getBlock().setData((byte) (event.getData() + getOrientation(event.getPlayer())));
                break;
            default:
                event.getBlock().setData((byte) (event.getData() + pd.getLastBlockFace().ordinal()));
        }
    }
}
