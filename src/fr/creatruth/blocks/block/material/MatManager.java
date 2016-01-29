package fr.creatruth.blocks.block.material;

import fr.creatruth.blocks.block.BaseBlock;
import fr.creatruth.blocks.block.TypeAndDataBlock;
import fr.creatruth.blocks.block.type.*;
import fr.creatruth.blocks.utils.DataUtils;
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
                        base = new LogBlock();
                        break;

                    case RED_MUSHROOM:
                        base = new DifferentItemBlock(mat, HUGE_MUSHROOM_2, false);
                        break;

                    case BROWN_MUSHROOM:
                        base = new DifferentItemBlock(mat, HUGE_MUSHROOM_1, false);
                        break;

                    case STEP:
                    case WOOD_STEP:
                    case STONE_SLAB2:
                        base = new DoubleStepBlock();
                        break;

                    case FURNACE:
                    case BURNING_FURNACE:
                        base = new FurnaceBlock();
                        break;

                    case DIRT:
                        base = new DifferentItemBlock(mat, SOIL, false);
                        break;

                    case PISTON_BASE:
                    case PISTON_STICKY_BASE:
                        base = new PistonBlock();
                        break;

                    case PISTON_EXTENSION:
                        base = new PistonExtensionBlock();
                        break;

                    case CAKE:
                        base = new DifferentItemBlock(mat, CAKE_BLOCK);
                        break;

                    case INK_SACK:
                    case COCOA:
                        base = new CocoaBlock();
                        break;

                    case BANNER:
                        base = new BannerBlock();
                        break;

                    case PORTAL:
                        base = new PortalBlock();
                        break;

                    case PAINTING:
                        base = new PaintingBlock();
                        break;

                    case REDSTONE_LAMP_OFF:
                        base = new RedstoneLampBlock();
                        break;

                    case CAULDRON_ITEM:
                        base = new DifferentItemBlock(mat, CAULDRON);
                        break;

                    case FENCE_GATE:
                    case ACACIA_FENCE_GATE:
                    case BIRCH_FENCE_GATE:
                    case DARK_OAK_FENCE_GATE:
                    case JUNGLE_FENCE_GATE:
                    case SPRUCE_FENCE_GATE:
                        base = new FenceGateBlock(mat);
                        break;

                    case FLOWER_POT:
                    case FLOWER_POT_ITEM:
                        base = new FlowerPotBlock();
                        break;

                    case CARROT_ITEM:
                        base = new DifferentItemBlock(mat, CARROT);
                        break;

                    case DAYLIGHT_DETECTOR:
                        base = new DifferentItemBlock(mat, DAYLIGHT_DETECTOR_INVERTED);
                        break;

                    case MELON_SEEDS:
                        base = new DifferentItemBlock(mat, MELON_STEM);
                        break;

                    case POTATO_ITEM:
                        base = new DifferentItemBlock(mat, POTATO);
                        break;

                    case PUMPKIN_SEEDS:
                        base = new DifferentItemBlock(mat, PUMPKIN_STEM);
                        break;

                    case SEEDS:
                        base = new DifferentItemBlock(mat, CROPS);
                        break;

                    case SUGAR_CANE:
                        base = new DifferentItemBlock(mat, SUGAR_CANE_BLOCK);
                        break;

                    case BED:
                        base = new BedItemBlock();
                        break;

                    case BED_BLOCK:
                        base = new BedBlock();
                        break;

                    case BREWING_STAND_ITEM:
                        base = new BrewingStandBlock();
                        break;

                    case SKULL_ITEM:
                        base = new HeadBlock();
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
                    case HAY_BLOCK:
                    case PUMPKIN:
                    case JACK_O_LANTERN:
                    case DISPENSER:
                    case DROPPER:
                    case TORCH:
                    case REDSTONE_TORCH_ON:
                    case REDSTONE_TORCH_OFF:
                    case ACACIA_DOOR:
                    case BIRCH_DOOR:
                    case DARK_OAK_DOOR:
                    case JUNGLE_DOOR:
                    case SPRUCE_DOOR:
                    case WOODEN_DOOR:
                    case IRON_DOOR_BLOCK:
                    case VINE:
                    case LEVER:
                    case WOOD_BUTTON:
                    case STONE_BUTTON:
                    case TRIPWIRE_HOOK:
                    case TRAP_DOOR:
                    case IRON_TRAPDOOR:
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