/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager;

import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.manager.block.*;
import fr.creatruth.blocks.manager.block.type.*;
import fr.creatruth.blocks.manager.item.*;
import fr.creatruth.blocks.manager.item.type.*;
import fr.creatruth.blocks.manager.item.type.armor.*;
import fr.creatruth.blocks.manager.item.type.armor.horse.BardingItem;
import fr.creatruth.blocks.manager.item.type.liquid.LavaItem;
import fr.creatruth.blocks.manager.item.type.liquid.WaterItem;
import fr.creatruth.blocks.manager.item.type.redstone.*;
import fr.creatruth.blocks.manager.item.type.seeds.*;
import fr.creatruth.blocks.manager.item.type.other.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

public enum Materials {

    AIR                     (0, false, null, null),
    STONE                   (1, SoilBlock.class, BaseItem.class, new byte[]{0, 1, 2, 3, 4, 5, 6}),
    GRASS                   (2, SoilBlock.class, DirtItem.class),
    DIRT                    (3, SoilBlock.class, DirtItem.class, new byte[]{0, 1, 2}),
    COBBLESTONE             (4, SoilBlock.class),
    WOOD                    (5, DataBlock.class, BaseItem.class, new byte[]{0, 1, 2, 3, 4, 5}),
    SAPLING                 (6, TypeAndDataBlock.class, SaplingItem.class, new byte[]{0, 1, 2, 3, 4, 5}),
    BEDROCK                 (7),
    WATER                   (8, false, TypeAndDataBlock.class, WaterItem.class),
    STATIONARY_WATER        (9, false, TypeAndDataBlock.class, WaterItem.class, new byte[]{0, 1, 2, 3, 4, 5, 6, 7}),
    LAVA                    (10, false, TypeAndDataBlock.class, LavaItem.class),
    STATIONARY_LAVA         (11, false, TypeAndDataBlock.class, LavaItem.class, new byte[]{0, 1, 2, 3, 4, 5, 6, 7}),
    SAND                    (12, SoilBlock.class, BaseItem.class, new byte[]{0, 1}),
    GRAVEL                  (13, SoilBlock.class),
    GOLD_ORE                (14, null, OreBlockItem.class),
    IRON_ORE                (15, null, OreBlockItem.class),
    COAL_ORE                (16, null, OreBlockItem.class),
    LOG                     (17, LogBlock.class, LogItem.class, new byte[]{0, 1, 2, 3, 12, 13, 14, 15}),
    LEAVES                  (18, DataBlock.class, BaseItem.class, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}),
    SPONGE                  (19, null, BaseItem.class, new byte[]{0, 1}),
    GLASS                   (20),
    LAPIS_ORE               (21, null, OreBlockItem.class),
    LAPIS_BLOCK             (22),
    DISPENSER               (23, DispenserBlock.class, DispenserItem.class),
    SANDSTONE               (24, DataBlock.class, BaseItem.class, new byte[]{0, 1, 2}),
    NOTE_BLOCK              (25),
    BED_BLOCK               (26, false, BedBlock.class, BedBlockItem.class),
    POWERED_RAIL            (27, TypeAndDataBlock.class, RailItem.class),
    DETECTOR_RAIL           (28, TypeAndDataBlock.class, RailItem.class),
    PISTON_STICKY_BASE      (29, PistonBaseBlock.class, PistonBaseItem.class),
    WEB                     (30),
    LONG_GRASS              (31, TypeAndDataBlock.class, PlantItem.class, new byte[]{0, 1, 2}),
    DEAD_BUSH               (32, TypeAndDataBlock.class, PlantItem.class),
    PISTON_BASE             (33, PistonBaseBlock.class, PistonBaseItem.class),
    PISTON_EXTENSION        (34, false, PistonExtensionBlock.class, PistonExtensionItem.class),
    WOOL                    (35, null, BaseItem.class, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}),
    PISTON_MOVING_PIECE     (36, false, TypeAndDataBlock.class, PistonMovingPieceItem.class),
    YELLOW_FLOWER           (37, TypeAndDataBlock.class, PlantItem.class),
    RED_ROSE                (38, TypeAndDataBlock.class, PlantItem.class, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8}),
    BROWN_MUSHROOM          (39, TypeAndDataBlock.class, PlantItem.class),
    RED_MUSHROOM            (40, TypeAndDataBlock.class, PlantItem.class),
    GOLD_BLOCK              (41),
    IRON_BLOCK              (42),
    DOUBLE_STEP             (43, null, DoubleStepItem.class, new byte[]{0, 1, 2, 3, 4, 5, 6, 7}),
    STEP                    (44, null, BaseItem.class, new byte[]{0, 1, 2, 3, 4, 5, 6, 7}),
    BRICK                   (45),
    TNT                     (46, DataBlock.class, BaseItem.class, new byte[]{0, 1}),
    BOOKSHELF               (47),
    MOSSY_COBBLESTONE       (48),
    OBSIDIAN                (49),
    TORCH                   (50, TorchBlock.class, TorchItem.class),
    FIRE                    (51, false),
    MOB_SPAWNER             (52),
    WOOD_STAIRS             (53, null, StairItem.class),
    CHEST                   (54, null, null),
    REDSTONE_WIRE           (55, false, DefaultRedstoneBlock.class, RedstoneWireItem.class),
    DIAMOND_ORE             (56, null, OreBlockItem.class),
    DIAMOND_BLOCK           (57),
    WORKBENCH               (58),
    CROPS                   (59, false, TypeAndDataBlock.class, SeedsItem.class),
    SOIL                    (60, SoilBlock.class, SoilItem.class),
    FURNACE                 (61, FurnaceBlock.class, FurnaceItem.class),
    BURNING_FURNACE         (62, FurnaceBlock.class, FurnaceItem.class),
    SIGN_POST               (63, false),
    WOODEN_DOOR             (64, false),
    LADDER                  (65, LadderBlock.class, LadderItem.class),
    RAILS                   (66, TypeAndDataBlock.class, RailItem.class),
    COBBLESTONE_STAIRS      (67, null, StairItem.class),
    WALL_SIGN               (68, false),
    LEVER                   (69, LeverBlock.class, LeverItem.class),
    STONE_PLATE             (70, TypeAndDataBlock.class, PlateItem.class),
    IRON_DOOR_BLOCK         (71, false),
    WOOD_PLATE              (72, TypeAndDataBlock.class, PlateItem.class),
    REDSTONE_ORE            (73, null, OreBlockItem.class),
    GLOWING_REDSTONE_ORE    (74, false),
    REDSTONE_TORCH_OFF      (75, false, RedstoneTorchBlock.class, RedstoneTorchItem.class),
    REDSTONE_TORCH_ON       (76, RedstoneTorchBlock.class, RedstoneTorchItem.class),
    STONE_BUTTON            (77, ButtonBlock.class, ButtonItem.class),
    SNOW                    (78, TypeAndDataBlock.class, SnowItem.class),
    ICE                     (79),
    SNOW_BLOCK              (80),
    CACTUS                  (81, TypeAndDataBlock.class, CactusItem.class),
    CLAY                    (82),
    SUGAR_CANE_BLOCK        (83, false, TypeAndDataBlock.class, SugarCaneItem.class),
    JUKEBOX                 (84),
    FENCE                   (85),
    PUMPKIN                 (86, PumpkinBlock.class, PumpkinItem.class),
    NETHERRACK              (87),
    SOUL_SAND               (88),
    GLOWSTONE               (89),
    PORTAL                  (90, PortalBlock.class, PortalItem.class),
    JACK_O_LANTERN          (91, PumpkinBlock.class, PumpkinItem.class),
    CAKE_BLOCK              (92, false, TypeAndDataBlock.class, CakeItem.class),
    DIODE_BLOCK_OFF         (93, false, RedstoneDiodeBlock.class, RedstoneDiodeItem.class),
    DIODE_BLOCK_ON          (94, false, RedstoneDiodeBlock.class, RedstoneDiodeItem.class),
    STAINED_GLASS           (95, null, BaseItem.class, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}),
    TRAP_DOOR               (96, TrapDoorBlock.class, TrapDoorItem.class),
    MONSTER_EGGS            (97, null, BaseItem.class, new byte[]{0, 1, 2, 3, 4, 5}),
    SMOOTH_BRICK            (98, null, BaseItem.class, new byte[]{0, 1, 2, 3}),
    HUGE_MUSHROOM_1         (99, DataBlock.class, HugeMushroom1Item.class, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}),
    HUGE_MUSHROOM_2         (100, DataBlock.class, HugeMushroom2Item.class, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}),
    IRON_FENCE              (101),
    THIN_GLASS              (102),
    MELON_BLOCK             (103),
    PUMPKIN_STEM            (104, false, TypeAndDataBlock.class, PumpkinSeedsItem.class),
    MELON_STEM              (105, false, TypeAndDataBlock.class, MelonSeedsItem.class),
    VINE                    (106, VineBlock.class, VineItem.class),
    FENCE_GATE              (107, FenceGateBlock.class, FenceGateItem.class),
    BRICK_STAIRS            (108, null, StairItem.class),
    SMOOTH_STAIRS           (109, null, StairItem.class),
    MYCEL                   (110, SoilBlock.class, DirtItem.class),
    WATER_LILY              (111, TypeAndDataBlock.class, WaterLilyItem.class),
    NETHER_BRICK            (112),
    NETHER_FENCE            (113),
    NETHER_BRICK_STAIRS     (114, null, StairItem.class),
    NETHER_WARTS            (115, false, TypeAndDataBlock.class, NetherWartsItem.class),
    ENCHANTMENT_TABLE       (116),
    BREWING_STAND           (117, false, TypeAndDataBlock.class, BrewingStandItem.class),
    CAULDRON                (118, false, DataBlock.class, CauldronItem.class),
    ENDER_PORTAL            (119),
    ENDER_PORTAL_FRAME      (120, DefaultOrientableBlock.class, DefaultOrientableItem.class),
    ENDER_STONE             (121),
    DRAGON_EGG              (122),
    REDSTONE_LAMP_OFF       (123, RedstoneLampBlock.class, RedstoneLampItem.class),
    REDSTONE_LAMP_ON        (124, false, RedstoneLampBlock.class, RedstoneLampItem.class),
    WOOD_DOUBLE_STEP        (125, DataBlock.class, DoubleStepItem.class),
    WOOD_STEP               (126, null),
    COCOA                   (127, DefaultOrientableBlock.class, CocoaItem.class),
    SANDSTONE_STAIRS        (128, null, StairItem.class),
    EMERALD_ORE             (129, null, OreBlockItem.class),
    ENDER_CHEST             (130, null, null),
    TRIPWIRE_HOOK           (131, TripWireHookBlock.class, TripWireHookItem.class),
    TRIPWIRE                (132, false, null, TripWireItem.class),
    EMERALD_BLOCK           (133),
    SPRUCE_WOOD_STAIRS      (134, null, StairItem.class),
    BIRCH_WOOD_STAIRS       (135, null, StairItem.class),
    JUNGLE_WOOD_STAIRS      (136, null, StairItem.class),
    COMMAND                 (137),
    BEACON                  (138),
    COBBLE_WALL             (139),
    FLOWER_POT              (140, false, TypeAndDataBlock.class, FlowerPotItem.class),
    CARROT                  (141, false, TypeAndDataBlock.class, CarrotItem.class),
    POTATO                  (142, false, TypeAndDataBlock.class, PotatoItem.class),
    WOOD_BUTTON             (143, ButtonBlock.class, ButtonItem.class),
    SKULL                   (144, false, null, HeadItem.class),
    ANVIL                   (145, null, DefaultOrientableItem.class),
    TRAPPED_CHEST           (146, null, null),
    GOLD_PLATE              (147, TypeAndDataBlock.class, PlateItem.class),
    IRON_PLATE              (148, TypeAndDataBlock.class, PlateItem.class),
    REDSTONE_COMPARATOR_OFF (149, false, RedstoneComparatorBlock.class, RedstoneComparatorItem.class),
    REDSTONE_COMPARATOR_ON  (150, false, RedstoneComparatorBlock.class, RedstoneComparatorItem.class),
    DAYLIGHT_DETECTOR       (151),
    REDSTONE_BLOCK          (152),
    QUARTZ_ORE              (153),
    HOPPER                  (154, null, null),
    QUARTZ_BLOCK            (155, null, QuartzItem.class),
    QUARTZ_STAIRS           (156, null, StairItem.class),
    ACTIVATOR_RAIL          (157, TypeAndDataBlock.class, RailItem.class),
    DROPPER                 (158, DispenserBlock.class, DispenserItem.class),
    STAINED_CLAY            (159, DataBlock.class, BaseItem.class, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}),
    STAINED_GLASS_PANE      (160, DataBlock.class, BaseItem.class, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}),
    LEAVES_2                (161, DataBlock.class, BaseItem.class, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15}),
    LOG_2                   (162, LogBlock.class, LogItem.class, new byte[]{0, 1, 2, 3, 12, 13, 14, 15}),
    ACACIA_STAIRS           (163, null, StairItem.class),
    DARK_OAK_STAIRS         (164, null, StairItem.class),
    SLIME_BLOCK             (165), // TODO
    BARRIER                 (166), // TODO
    IRON_TRAPDOOR           (167), // TODO
    PRISMARINE              (168), // TODO
    SEA_LANTERN             (169), // TODO
    HAY_BLOCK               (170, HayBlock.class, HayItem.class),
    CARPET                  (171, TypeAndDataBlock.class, CarpetItem.class),
    HARD_CLAY               (172),
    COAL_BLOCK              (173),
    PACKED_ICE              (174),
    DOUBLE_PLANT            (175, TypeAndDataBlock.class, DoublePlantItem.class),
    STANDING_BANNER         (176), // TODO
    WALL_BANNER             (177), // TODO
    DAYLIGHT_DETECTOR_INVERTED(178), // TODO
    RED_SANDSTONE           (179), // TODO
    RED_SANDSTONE_STAIRS    (180, null, StairItem.class),
    DOUBLE_STONE_SLAB2      (181), // TODO
    STONE_SLAB2             (182), // TODO
    SPRUCE_FENCE_GATE       (183), // TODO
    BIRCH_FENCE_GATE        (184), // TODO
    JUNGLE_FENCE_GATE       (185), // TODO
    DARK_OAK_FENCE_GATE     (186), // TODO
    ACACIA_FENCE_GATE       (187), // TODO
    SPRUCE_FENCE            (188), // TODO
    BIRCH_FENCE             (189), // TODO
    JUNGLE_FENCE            (190), // TODO
    DARK_OAK_FENCE          (191), // TODO
    ACACIA_FENCE            (192), // TODO
    SPRUCE_DOOR             (193), // TODO
    BIRCH_DOOR              (194), // TODO
    JUNGLE_DOOR             (195), // TODO
    ACACIA_DOOR             (196), // TODO
    DARK_OAK_DOOR           (197), // TODO
    IRON_SPADE              (256, null, null),
    IRON_PICKAXE            (257, null, null),
    IRON_AXE                (258, null, null),
    FLINT_AND_STEEL         (259, null, null),
    APPLE                   (260, null, FoodItem.class),
    BOW                     (261, null, null),
    ARROW                   (262, null, null),
    COAL                    (263, null, OreItem.class, new byte[]{0, 1}),
    DIAMOND                 (264, null, OreItem.class),
    IRON_INGOT              (265, null, OreItem.class),
    GOLD_INGOT              (266, null, OreItem.class),
    IRON_SWORD              (267, null, null),
    WOOD_SWORD              (268, null, null),
    WOOD_SPADE              (269, null, null),
    WOOD_PICKAXE            (270, null, null),
    WOOD_AXE                (271, null, null),
    STONE_SWORD             (272, null, null),
    STONE_SPADE             (273, null, null),
    STONE_PICKAXE           (274, null, null),
    STONE_AXE               (275, null, null),
    DIAMOND_SWORD           (276, null, null),
    DIAMOND_SPADE           (277, null, null),
    DIAMOND_PICKAXE         (278, null, null),
    DIAMOND_AXE             (279, null, null),
    STICK                   (280, null, null),
    BOWL                    (281, null, null),
    MUSHROOM_SOUP           (282, null, FoodItem.class),
    GOLD_SWORD              (283, null, null),
    GOLD_SPADE              (284, null, null),
    GOLD_PICKAXE            (285, null, null),
    GOLD_AXE                (286, null, null),
    STRING                  (287, null, null),
    FEATHER                 (288, null, null),
    SULPHUR                 (289, null, null),
    WOOD_HOE                (290, null, null),
    STONE_HOE               (291, null, null),
    IRON_HOE                (292, null, null),
    DIAMOND_HOE             (293, null, null),
    GOLD_HOE                (294, null, null),
    SEEDS                   (295, DataBlock.class, SeedsItem.class),
    WHEAT                   (296, null, null),
    BREAD                   (297, null, FoodItem.class),
    LEATHER_HELMET          (298, null, ArmorLeatherItem.class),
    LEATHER_CHESTPLATE      (299, null, ArmorLeatherItem.class),
    LEATHER_LEGGINGS        (300, null, ArmorLeatherItem.class),
    LEATHER_BOOTS           (301, null, ArmorLeatherItem.class),
    CHAINMAIL_HELMET        (302, null, ArmorChainItem.class),
    CHAINMAIL_CHESTPLATE    (303, null, ArmorChainItem.class),
    CHAINMAIL_LEGGINGS      (304, null, ArmorChainItem.class),
    CHAINMAIL_BOOTS         (305, null, ArmorChainItem.class),
    IRON_HELMET             (306, null, ArmorIronItem.class),
    IRON_CHESTPLATE         (307, null, ArmorIronItem.class),
    IRON_LEGGINGS           (308, null, ArmorIronItem.class),
    IRON_BOOTS              (309, null, ArmorIronItem.class),
    DIAMOND_HELMET          (310, null, ArmorDiamondItem.class),
    DIAMOND_CHESTPLATE      (311, null, ArmorDiamondItem.class),
    DIAMOND_LEGGINGS        (312, null, ArmorDiamondItem.class),
    DIAMOND_BOOTS           (313, null, ArmorDiamondItem.class),
    GOLD_HELMET             (314, null, ArmorGoldItem.class),
    GOLD_CHESTPLATE         (315, null, ArmorGoldItem.class),
    GOLD_LEGGINGS           (316, null, ArmorGoldItem.class),
    GOLD_BOOTS              (317, null, ArmorGoldItem.class),
    FLINT                   (318, null, null),
    PORK                    (319, null, FoodItem.class),
    GRILLED_PORK            (320, null, FoodItem.class),
    PAINTING                (321, null, PaintingItem.class),
    GOLDEN_APPLE            (322, null, FoodItem.class, new byte[]{0, 1}),
    SIGN                    (323, null, null),
    WOOD_DOOR               (324, null, DoorItem.class),
    BUCKET                  (325, null, BucketItem.class),
    WATER_BUCKET            (326, null, BucketItem.class),
    LAVA_BUCKET             (327, null, BucketItem.class),
    MINECART                (328, null, MinecartItem.class),
    SADDLE                  (329, null, null),
    IRON_DOOR               (330, null, DoorItem.class),
    REDSTONE                (331, DefaultRedstoneBlock.class, RedstoneWireItem.class),
    SNOW_BALL               (332, null, null),
    BOAT                    (333, null, null),
    LEATHER                 (334, null, null),
    MILK_BUCKET             (335, null, BucketItem.class),
    CLAY_BRICK              (336, null, null),
    CLAY_BALL               (337, null, null),
    SUGAR_CANE              (338, DataBlock.class, SugarCaneItem.class),
    PAPER                   (339, null, null),
    BOOK                    (340, null, null),
    SLIME_BALL              (341, null, null),
    STORAGE_MINECART        (342, null, MinecartItem.class),
    POWERED_MINECART        (343, null, MinecartItem.class),
    EGG                     (344, null, null),
    COMPASS                 (345, null, null),
    FISHING_ROD             (346, null, null),
    WATCH                   (347, null, null),
    GLOWSTONE_DUST          (348, null, null),
    RAW_FISH                (349, null, FoodItem.class, new byte[]{0, 1, 2, 2}),
    COOKED_FISH             (350, null, FoodItem.class, new byte[]{0, 1}),
    INK_SACK                (351, null),
    BONE                    (352, null, null),
    SUGAR                   (353, null, null),
    CAKE                    (354, DataBlock.class, CakeItem.class),
    BED                     (355, null, BedItem.class),
    DIODE                   (356, RedstoneDiodeBlock.class, RedstoneDiodeItem.class),
    COOKIE                  (357, null, FoodItem.class),
    MAP                     (358, null, null),
    SHEARS                  (359, null, null),
    MELON                   (360, null, FoodItem.class),
    PUMPKIN_SEEDS           (361, DataBlock.class, PumpkinSeedsItem.class),
    MELON_SEEDS             (362, DataBlock.class, MelonSeedsItem.class),
    RAW_BEEF                (363, null, FoodItem.class),
    COOKED_BEEF             (364, null, FoodItem.class),
    RAW_CHICKEN             (365, null, FoodItem.class),
    COOKED_CHICKEN          (366, null, FoodItem.class),
    ROTTEN_FLESH            (367, null, FoodItem.class),
    ENDER_PEARL             (368, null, null),
    BLAZE_ROD               (369, null, null),
    GHAST_TEAR              (370, null, null),
    GOLD_NUGGET             (371, null, null),
    NETHER_STALK            (372, DataBlock.class, NetherWartsItem.class),
    POTION                  (373, null, null),
    GLASS_BOTTLE            (374, null, null),
    SPIDER_EYE              (375, null, FoodItem.class),
    FERMENTED_SPIDER_EYE    (376, null, null),
    BLAZE_POWDER            (377, null, null),
    MAGMA_CREAM             (378, null, null),
    BREWING_STAND_ITEM      (379, DataBlock.class, BrewingStandItem.class),
    CAULDRON_ITEM           (380, DataBlock.class, CauldronItem.class),
    EYE_OF_ENDER            (381, null, null),
    SPECKLED_MELON          (382, null, null),
    MONSTER_EGG             (383, null, BaseItem.class, new byte[]{0, 50, 51, 52, 54, 55, 56 , 57, 58, 59, 60, 61, 62, 65, 66, 67, 68, 90, 91, 92, 93, 94, 95, 96, 98, 100, 101, 120}),
    EXP_BOTTLE              (384, null, null),
    FIREBALL                (385, null, null),
    BOOK_AND_QUILL          (386, null, null),
    WRITTEN_BOOK            (387, null, null),
    EMERALD                 (388, null, OreItem.class),
    ITEM_FRAME              (389, null, null),
    FLOWER_POT_ITEM         (390, DataBlock.class, FlowerPotItem.class),
    CARROT_ITEM             (391, DataBlock.class, CarrotItem.class),
    POTATO_ITEM             (392, DataBlock.class, PotatoItem.class),
    BAKED_POTATO            (393, null, FoodItem.class),
    POISONOUS_POTATO        (394, null, FoodItem.class),
    EMPTY_MAP               (395, null, null),
    GOLDEN_CARROT           (396, null, FoodItem.class),
    SKULL_ITEM              (397, null, HeadItem.class),
    CARROT_STICK            (398, null, null),
    NETHER_STAR             (399, null, null),
    PUMPKIN_PIE             (400, null, FoodItem.class),
    FIREWORK                (401, null, null),
    FIREWORK_CHARGE         (402, null, null),
    ENCHANTED_BOOK          (403, null, null),
    REDSTONE_COMPARATOR     (404, RedstoneComparatorBlock.class, RedstoneComparatorItem.class),
    NETHER_BRICK_ITEM       (405, null, null),
    QUARTZ                  (406, null, null),
    EXPLOSIVE_MINECART      (407, null, MinecartItem.class),
    HOPPER_MINECART         (408, null, MinecartItem.class),
    PRISMARINE_SHARD        (409, null, null), // TODO
    PRISMARINE_CRYSTALS     (410, null, null), // TODO
    RABBIT                  (411, null, FoodItem.class), // TODO
    COOKED_RABBIT           (412, null, FoodItem.class), // TODO
    RABBIT_STEW             (413, null, FoodItem.class), // TODO
    RABBIT_FOOT             (414, null, null), // TODO
    RABBIT_HIDE             (415, null, null), // TODO
    ARMOR_STAND             (416, null, null), // TODO
    IRON_BARDING            (417, null, BardingItem.class),
    GOLD_BARDING            (418, null, BardingItem.class),
    DIAMOND_BARDING         (419, null, BardingItem.class),
    LEASH                   (420, null, null),
    NAME_TAG                (421, null, null),
    COMMAND_MINECART        (422, null, MinecartItem.class),
    MUTTON                  (423, null, FoodItem.class), // TODO
    COOKED_MUTTON           (424, null, FoodItem.class), // TODO
    BANNER                  (425, null, null), // TODO
    SPRUCE_DOOR_ITEM        (427, null, DoorItem.class),
    BIRCH_DOOR_ITEM         (428, null, DoorItem.class),
    JUNGLE_DOOR_ITEM        (429, null, DoorItem.class),
    ACACIA_DOOR_ITEM        (430, null, DoorItem.class),
    DARK_OAK_DOOR_ITEM      (431, null, DoorItem.class),
    GOLD_RECORD             (2256, null, RecordItem.class),
    GREEN_RECORD            (2257, null, RecordItem.class),
    RECORD_3                (2258, null, RecordItem.class),
    RECORD_4                (2259, null, RecordItem.class),
    RECORD_5                (2260, null, RecordItem.class),
    RECORD_6                (2261, null, RecordItem.class),
    RECORD_7                (2262, null, RecordItem.class),
    RECORD_8                (2263, null, RecordItem.class),
    RECORD_9                (2264, null, RecordItem.class),
    RECORD_10               (2265, null, RecordItem.class),
    RECORD_11               (2266, null, RecordItem.class),
    RECORD_12               (2267, null, RecordItem.class);

    public static final HashMap<Integer, Materials> BY_ID;

    static {
        BY_ID = new HashMap<>();
        for (Materials materials : values()) {
            BY_ID.put(materials.id, materials);
        }
    }

    private int                         id;
    private boolean                     visible;
    private Class<? extends BaseBlock>  blockClass;
    private Class<? extends BaseItem>   itemClass;
    private byte[]                      dataTable;

    Materials(int id) {
        this(id, DataBlock.class);
    }

    Materials(int id, boolean visible) {
        this(id, visible, DataBlock.class);
    }

    Materials(int id, Class<? extends BaseBlock> blockClass) {
        this(id, blockClass, DefaultPickableItem.class);
    }

    Materials(int id, boolean visible, Class<? extends BaseBlock> blockClass) {
        this(id, visible, blockClass, DefaultPickableItem.class);
    }

    Materials(int id, Class<? extends BaseBlock> blockClass, Class<? extends BaseItem> itemClass) {
        this(id, true, blockClass, itemClass);
    }

    Materials(int id, Class<? extends BaseBlock> blockClass, Class<? extends BaseItem> itemClass, byte[] dataTable) {
        this(id, true, blockClass, itemClass, dataTable);
    }

    Materials(int id, boolean visible, Class<? extends BaseBlock> blockClass, Class<? extends BaseItem> itemClass) {
        this(id, visible, blockClass, itemClass, new byte[]{});
    }

    Materials(int id, boolean visible, Class<? extends BaseBlock> blockClass, Class<? extends BaseItem> itemClass, byte[] dataTable) {
        this.id = id;
        this.visible = visible;
        this.blockClass = blockClass;
        this.itemClass = itemClass;
        this.dataTable = dataTable;
    }

    public int getId() {
        return id;
    }

    public boolean isVisible() {
        return visible;
    }

    public byte[] getDataTable() {
        return dataTable;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setDataTable(byte[] dataTable) {
        this.dataTable = dataTable;
    }

    public Class getItemClass() {
        return itemClass;
    }

    public Class getBlockClass() {
        return blockClass;
    }

    public Materials setItemClass(Class<? extends BaseItem> clazz) {
        this.itemClass = clazz;
        return this;
    }

    public Materials setBlockClass(Class<? extends BaseBlock> clazz) {
        this.blockClass = clazz;
        return this;
    }

    public static Materials getMaterials(Material material) {
        if (!Config.getBlocksBlackList().contains(material.name())) {
            try {
                return valueOf(material.name());
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
        return null;
    }

    public static BaseItem getBaseItem(Material material, ItemStack item) {
        Materials m = getMaterials(material);

        return m != null ? getBaseItem(m, item) : null;
    }

    public static BaseItem getBaseItem(Materials materials, ItemStack item) {
        Class<? extends BaseItem> clazz = materials.itemClass;
        if (clazz != null) {
            try {
                return clazz.getConstructor(ItemStack.class, Materials.class).newInstance(item, materials);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static BaseBlock getBaseBlock(BaseItem baseItem) {
        Class<? extends BaseBlock> clazz = baseItem.getMaterials().blockClass;
        if (clazz != null) {
            try {
                return clazz.getConstructor(BaseItem.class).newInstance(baseItem);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}