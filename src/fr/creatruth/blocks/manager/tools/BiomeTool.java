/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.tools;

import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.manager.utils.BiomeUtils;
import fr.creatruth.blocks.manager.utils.BlockUtils;
import fr.creatruth.blocks.manager.utils.ItemUtils;
import fr.creatruth.blocks.messages.Message;
import fr.creatruth.blocks.player.Perm;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BiomeTool {

    public static Material MATERIAL = Config.getMeterWand();
    private static final Random RANDOM = new Random();

    public static void add(Player player, List<Biome> biomes, int radius) {
        String name = String.format(ItemPattern.BIOME_FORMAT, BiomeUtils.toString(biomes), radius);
        ItemStack tool = ItemUtils.setItem(Config.getBiomeWand(), name);
        player.getInventory().addItem(tool);
    }

    public static boolean isBiomeTool(ItemStack item) {
        return item != null && item.getType() == MATERIAL && ItemPattern.hasPattern(ItemPattern.P_BIOME, item);
    }

    private Player      player;
    private ItemStack   item;
    private List<Biome> biomes;
    private int         radius;

    /**
     * Constructeur
     * @param item l'item qui doit être testé en tant qu'outil pour les biomes avant.
     */
    public BiomeTool(Player player, ItemStack item) {
        this.player = player;
        this.item = item;
        String name = ItemUtils.getDisplayName(item);
        this.biomes = ItemPattern.getBiomes(name);
        this.radius = ItemPattern.getRadius(name);
    }

    public String getName() {
        String b = biomes.size() == 0 ? "???" : BiomeUtils.toString(biomes);
        return String.format(ItemPattern.BIOME_FORMAT, b, radius);
    }

    public ItemStack getItem() {
        return item;
    }

    public List<Biome> getBiomes() {
        return biomes;
    }

    public Biome getRandomBiome() {
        if (biomes.size() > 1)
            return biomes.get(RANDOM.nextInt(biomes.size()));
        else
            return biomes.get(0);
    }

    public int getRadius() {
        return radius;
    }

    public void setBiomes(List<Biome> biomes) {
        this.biomes = biomes;
        ItemUtils.setName(item, getName());
    }


    public void setRadius(int radius) {
        this.radius = radius;
        ItemUtils.setName(item, getName());
    }

    /**
     * PAINT EVENT
     *
     * @param p Le joueur qui peint.
     * @param bl Le bloc visé.
     */
    public void onPaint(Player p, Block bl) {
        if (biomes.size() == 0) return;

        boolean change = false;

        if (bl == null) {
            bl = BlockUtils.getTargetBlock(p, 50);
            if (bl.getType() == Material.AIR) {
                Message.EVENT_CLICK_NOTARGET.sendAlert(p);
                return;
            }
        }
        Location l = bl.getLocation();
        Set<Chunk> chunks = new HashSet<>();

        if (radius == 0) {
            if (canByApply(bl) && Perm.canBuild(p, l)) {
                bl.setBiome(getRandomBiome());
                chunks.add(bl.getChunk());
                change = true;
            }
        }
        else {
            World w = l.getWorld();
            int y = l.getBlockY();

            int bX = l.getBlockX();
            int bZ = l.getBlockZ();

            for (int x = bX - radius; x <= bX + radius; x++) {
                for (int z = bZ - radius; z <= bZ + radius; z++) {

                    if (((bX - x) * (bX - x)) + ((bZ - z) * (bZ - z)) <= radius * radius) {

                        bl = new Location(w, x, y, z).getBlock();
                        if (canByApply(bl) && Perm.canBuild(p, bl.getLocation())) {
                            bl.setBiome(getRandomBiome());
                            chunks.add(bl.getChunk());
                            change = true;
                        }
                    }
                }
            }
        }
        if (change) {
            for (Chunk c : chunks) {
                l.getWorld().refreshChunk(c.getX(), c.getZ());
            }
            Particle.FIREWORKS_SPARK.sendToPlayer(p, l.clone().add(.5, .5, .5), .7F, .7F, .7F, 0, 5);
        }
    }

    private boolean canByApply(Block block) {
        return biomes.size() > 1 || block.getBiome() != biomes.get(0);
    }

    /**
     * RADIUS EVENT
     * @param action Le clique du joueur.
     */
    public void onChangeRadius(Action action) {
        if (action == Action.RIGHT_CLICK_AIR) {
            int max = Perm.BIOME_LIMIT.has(player) ? 100 : Config.getBiomeMaxRadius();
            if (radius < max)
                setRadius(++radius);
        }
        else if (action == Action.LEFT_CLICK_AIR) {
            if (radius > 0)
                setRadius(--radius);
        }
    }
}
