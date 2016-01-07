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

public class HugeMushroomBlock extends TypeAndDataBlock {

    private Material material;

    public HugeMushroomBlock(Material material) {
        this.material = material;
    }

    @Override
    public void onPick(PickBlockEvent event) {
        super.onPick(event);

        if (event.isTargetType(material)) {
            event.setCursor(itemManager().getBuilder(event.getTarget()).build());
        }
    }
}