/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.blocks.manager.utils.SkullUtils;
import org.bukkit.Material;
import org.bukkit.block.Skull;

public class HeadBlock extends BaseBlock {

    @Override
    public void onPick(PickBlockEvent event) {
        if (event.getTarget().getType() == Material.SKULL) {
            Skull skull = (Skull) event.getTarget().getState();
            if (skull.hasOwner())
                SkullUtils.setHead(event.getTarget(), event.getCursor());
        }
    }
}
