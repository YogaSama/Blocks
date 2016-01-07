/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.manager.utils.WorldUtils;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class RedstoneBlock extends OrientableBlock {

    /* -------------- */
    /* PICK           */
    /* -------------- */

    protected Material on;
    protected Material off;
    protected Material classicItem;

    @Override
    public void onPick(PickBlockEvent event) {
        if      (event.isCursorType(on)         ) setCursor(event, on);
        else if (event.isCursorType(classicItem)) setCursor(event, classicItem);
    }

    private void setCursor(PickBlockEvent event, Material material) {
        if (event.isTargetType(on))
            event.setCursor(itemManager().getBuilder(material, (byte) 0).build());

        else if (event.isTargetType(off))
            event.setCursor(itemManager().getBuilder(material, (byte) 1).build());
    }

    /* -------------- */
    /* PLACE          */
    /* -------------- */

    public void apply(Block block, Material material) {
        try {
            WorldUtils.setStaticWorld(block.getWorld(), true);
            block.setType(material);
            WorldUtils.setStaticWorld(block.getWorld(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
