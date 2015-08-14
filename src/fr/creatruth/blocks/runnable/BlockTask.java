/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.runnable;

import fr.creatruth.blocks.manager.block.type.SoilBlock;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

public class BlockTask implements Runnable {

    private Block block;

    public BlockTask(Block block) {
        this.block = block;
    }

    @Override
    public void run() {
        if (SoilBlock.SOILS.contains(block.getRelative(BlockFace.DOWN).getType()) && !block.isEmpty()) {
            this.block.getRelative(BlockFace.DOWN).setType(Material.DIRT);
        }
    }
}
