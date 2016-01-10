/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block.type;

import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.block.DataBlock;
import fr.creatruth.development.material.MatData;
import org.bukkit.Material;

public class CakeBlock extends DataBlock {

    @Override
    public void onPick(PickBlockEvent event) {
        if (event.isTargetType(Material.CAKE_BLOCK)) {
            MatData md = new MatData(event.getCursor().getType(), event.getTarget().getData());
            event.setCursor(itemManager().getBuilder(md).build());
        }
    }
}
