/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.DataBlock;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.runnable.BlockTask;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashSet;
import java.util.Set;

public class SoilBlock extends DataBlock {

    public static final Set<Material> SOILS;

    static {
        SOILS = new HashSet<>();
        SOILS.add(Material.STONE);
        SOILS.add(Material.GRASS);
        SOILS.add(Material.DIRT);
        SOILS.add(Material.COBBLESTONE);
        SOILS.add(Material.SAND);
        SOILS.add(Material.GRAVEL);
        SOILS.add(Material.MYCEL);
    }

    public SoilBlock(BaseItem baseItem) {
        super(baseItem);
    }

    @Override
    public void onPlace(BlockPlaceEvent event) {
        super.onPlace(event);

        if (material == Material.SOIL)
            setSoil();

        setGround();
    }

    private void setSoil() {
        Block newBlock = block.getRelative(BlockFace.UP);
        byte value = Config.getSeedData();
        if (material == Material.SOIL && newBlock.isEmpty()) {
            Material seed;
            switch (data) {
                case 1: seed = Material.CROPS; break;
                case 2: seed = Material.CARROT; break;
                case 3: seed = Material.POTATO; break;
                case 4: seed = Material.PUMPKIN_STEM; break;
                case 5: seed = Material.MELON; break;
                default: return;
            }
            newBlock.setType(seed);
            newBlock.setData(value);
        }
    }

    private void setGround() {
        if (material == Material.DIRT || material == Material.SAND) {
            data = block.getData();
        }

        block.setData(data);

        if (SOILS.contains(block.getRelative(BlockFace.UP).getType()) && (material == Material.GRASS || material == Material.MYCEL || material == Material.SOIL)) {
            block.setType(Material.DIRT);
        }
        if (block.getRelative(BlockFace.DOWN).getType() == Material.GRASS || block.getRelative(BlockFace.DOWN).getType() == Material.MYCEL) {
            Bukkit.getServer().getScheduler().runTaskLater(BMain.instance, new BlockTask(block), 200L);
        }
    }
}
