/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.manager.block.DataBlock;
import fr.creatruth.development.material.MatData;
import org.bukkit.Material;

public class CauldronBlock extends DataBlock {

    @Override
    public void onPick(PickBlockEvent event) {
        if (event.isTargetType(Material.CAULDRON)) {
            MatData md = new MatData(event.getCursor().getType(), event.getTarget().getData());
            event.setCursor(itemManager().getBuilder(md).build());
        }
    }
}
