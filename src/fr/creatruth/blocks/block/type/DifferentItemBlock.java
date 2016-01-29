/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block.type;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.block.BaseBlock;
import fr.creatruth.blocks.block.Pickable;
import fr.creatruth.blocks.block.Placeable;
import org.bukkit.Material;

public class DifferentItemBlock extends BaseBlock implements Placeable, Pickable {

    protected Material item;
    protected Material block;
    protected boolean  giveItem;

    public DifferentItemBlock(Material item, Material block) {
        this(item, block, true);
    }

    public DifferentItemBlock(Material item, Material block, boolean giveItem) {
        this.item     = item;
        this.block    = block;
        this.giveItem = giveItem;
    }

    @Override
    public void onPick(PickBlockEvent event) {
        if (event.isCursorType(item) && event.isTargetType(block)) {
            event.setCursor(itemManager().getBuilder(giveItem ? item : block, event.getTarget().getData()).build());
        }
    }

    @Override
    public void onPlace(BlocksPlaceEvent event) {
        event.getBlock().setType(block);
        event.getBlock().setData(event.getData());
    }
}
