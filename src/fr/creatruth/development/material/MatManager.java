package fr.creatruth.development.material;

import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.blocks.manager.block.TypeAndDataBlock;
import fr.creatruth.blocks.manager.block.type.*;
import fr.creatruth.blocks.manager.utils.DataUtils;
import org.bukkit.Material;

import java.util.*;

import static org.bukkit.Material.*;

public class MatManager {

    private static final Map<Material, State> map;

    static {
        map = new HashMap<>();

        for (Material mat : values()) {
            try {
                if (mat.getMaxDurability() > 0) {
                    map.put(mat, new DammageableState(mat.getMaxDurability()));
                    continue;
                }

                Set<Byte> set = new HashSet<>();
                for (byte data = 0; data < 16; data++) {
                    if (DataUtils.exist(mat, data))
                        set.add(data);
                }

                State state = new State(set);
                BaseBlock base;

                switch (mat) {
                    case LOG:
                    case LOG_2:
                        base     = new LogBlock();
                        break;

                    case RED_MUSHROOM:
                        base     = new HugeMushroomBlock(Material.HUGE_MUSHROOM_2);
                        break;

                    case BROWN_MUSHROOM:
                        base     = new HugeMushroomBlock(Material.HUGE_MUSHROOM_1);
                        break;

                    case STEP:
                    case WOOD_STEP:
                    case STONE_SLAB2:
                        base     = new DoubleStepBlock();
                        break;

                    case FURNACE:
                    case BURNING_FURNACE:
                        base     = new FurnaceBlock();
                        break;

                    case DIRT:
                        base     = new SoilBlock();
                        break;

                    case PISTON_EXTENSION:
                        base     = new PistonExtensionBlock();
                        break;

                    case BANNER:
                        base     = new BannerBlock();
                        break;

                    case PORTAL:
                        base    = new PortalBlock();
                        break;

                    case PAINTING:
                        base    = new PaintingBlock();
                        break;

                    case FLOWER_POT:
                    case FLOWER_POT_ITEM:
                        base    = new FlowerPotBlock();
                        break;

                    case CARROT_ITEM:
                    case CARROT:
                        base    = new SeedBlock(CARROT_ITEM, CARROT);
                        break;

                    case MELON_SEEDS:
                    case MELON_STEM:
                        base    = new SeedBlock(MELON_SEEDS, MELON_STEM);
                        break;

                    case POTATO_ITEM:
                    case BAKED_POTATO:
                        base    = new SeedBlock(POTATO_ITEM, POTATO);
                        break;

                    case PUMPKIN_SEEDS:
                    case PUMPKIN_STEM:
                        base    = new SeedBlock(PUMPKIN_SEEDS, PUMPKIN_STEM);
                        break;

                    case SEEDS:
                    case CROPS:
                        base    = new SeedBlock(SEEDS, CROPS);
                        break;

                    case SUGAR_CANE:
                    case SUGAR_CANE_BLOCK:
                        base    = new SeedBlock(SUGAR_CANE, SUGAR_CANE_BLOCK);
                        break;

                    case BED:
                        base    = new BedItemBlock();
                        break;

                    case BED_BLOCK:
                        base    = new BedBlock();
                        break;

                    case BREWING_STAND_ITEM:
                        base    = new BrewingStandBlock();
                        break;

                    case ACACIA_STAIRS:
                    case BIRCH_WOOD_STAIRS:
                    case BRICK_STAIRS:
                    case COBBLESTONE_STAIRS:
                    case DARK_OAK_STAIRS:
                    case JUNGLE_WOOD_STAIRS:
                    case NETHER_BRICK_STAIRS:
                    case QUARTZ_STAIRS:
                    case RED_SANDSTONE_STAIRS:
                    case SMOOTH_STAIRS:
                    case SANDSTONE_STAIRS:
                    case SPRUCE_WOOD_STAIRS:
                    case WOOD_STAIRS:
                    case ANVIL:
                    case QUARTZ_BLOCK:
                    case HOPPER:
                    case PISTON_STICKY_BASE:
                    case PISTON_BASE:
                    case HAY_BLOCK:
                        base = null;
                        break;

                    default:
                        if (mat.isBlock())
                            base = new TypeAndDataBlock();
                        else
                            base = null;
                }

                state.setBase(base);
                map.put(mat, state);
            }
            catch (Exception e) {}
        }
    }

    public static State getState(Material material) {
        return map.get(material);
    }
}