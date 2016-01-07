/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.manager.block.TypeAndDataBlock;
import org.bukkit.Material;

public class SeedBlock extends TypeAndDataBlock {

    protected Material seed;
    protected Material material;

    public SeedBlock(Material seed, Material material) {
        this.seed = seed;
        this.material = material;
    }

    @Override
    public void onPick(PickBlockEvent event) {
        if (event.isCursorType(seed) && event.isTargetType(material)) {
            event.setCursor(itemManager().getBuilder(seed, event.getTarget().getData()).build());
        }
    }
}
