/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.listener;

import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.ItemBuilder;
import fr.creatruth.blocks.manager.tools.BiomeTool;
import fr.creatruth.blocks.manager.tools.ItemPattern;
import fr.creatruth.blocks.manager.utils.BlockUtils;
import fr.creatruth.blocks.manager.utils.ItemUtils;
import fr.creatruth.blocks.manager.tools.Meter;
import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.player.PlayerData;

import fr.creatruth.blocks.manager.utils.InfoUtils;
import fr.creatruth.blocks.runnable.TaskManager;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.PressurePlate;

import java.util.HashSet;
import java.util.Set;

public class InteractListener extends AListener {

    /**
     * Permet de déclencher les changements de data si le joueur est
     * en créatif, permet aussi de récupérer des informations sur les blocs.
     *
     * Si cela est précisé dans la configuration, les interactions sur les
     * oeufs d'enderdragon peuvent être annulées.
     *
     * Les PISTON_MOVING_PIECE sont aussi bloqués afin d'éviter qu'ils
     * soient cassés par erreur.
     */
    @EventHandler(priority = EventPriority.LOW)
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Action action = event.getAction();
        PlayerData pd = BMain.getData(player);
        ItemStack item = event.getItem();

        pd.setLastBlockFace(event.getBlockFace());
        /*
         * PHYSICAL ACTION
         */
        if (action == Action.PHYSICAL) {
            if (block.getData() > 0 && block.getState().getData() instanceof PressurePlate)
                event.setCancelled(true);
        }
        /*
         * SNEAK ACTION
         */
        else if (pd.isSneaking() && player.getGameMode() == GameMode.CREATIVE) {

            if (item != null) {
                BiomeTool tool = new BiomeTool(player, item);

                if (block == null && pd.has(PlayerData.Toggle.CHANGE) && canChangeFix(player)) {
                    if (BiomeTool.isBiomeTool(item)) {
                        tool.onChangeRadius(action);
                        event.setCancelled(true);
                    }
                    else {
                        String name = ItemUtils.getDisplayName(item);
                        if (name.equals("") || ItemPattern.hasPattern(ItemPattern.P_BLOCK, item)) {
                            BaseItem bi = BaseItem.toBaseItem(item);
                            if (bi != null) {
                                bi.onSwitch(action);
                                event.setCancelled(true);
                            }
                        }
                    }
                }
            }
            else if (action == Action.RIGHT_CLICK_BLOCK && pd.has(PlayerData.Toggle.INFO)) {
                InfoUtils.getBlockInfo(block, player, block.getLocation());
                event.setCancelled(true);
            }
        }
        /*
         * METER
         */
        else if (Meter.isMeter(item)) {
            Meter meter = new Meter(item);
            meter.updatePos(event);
        }
        /*
         * BIOME TOOL
         */
        else if (BiomeTool.isBiomeTool(item)) {
            BiomeTool tool = new BiomeTool(player, item);
            if (action == Action.RIGHT_CLICK_BLOCK || action == Action.RIGHT_CLICK_AIR) {
                tool.onPaint(player, block);
                event.setCancelled(true);
            }
        }
        /*
         * SENSIBLE BLOCK CLICK
         */
        else if (action == Action.RIGHT_CLICK_BLOCK) {
            if (block.getType() == Material.PISTON_MOVING_PIECE)
                event.setCancelled(true);
        }
    }

    /**
     * Petit fixe pour corriger le fait que de viser le sol permet de
     * changer de data sans vraiment viser de l'air.
     *
     * @param player Le joueur à tester
     * @return false si le joueur regarde trop bas et qu'il vise un bloc
     *         trop proche.
     */
    private static boolean canChangeFix(Player player) {
        return player.getLocation().getPitch() < 60 || BlockUtils.getExactlyTargetBlock(player, 3).getType() == Material.AIR;
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onPlace(BlockPlaceEvent event) {
        if (event.isCancelled()) return;

        Player player = event.getPlayer();
        PlayerData pd = BMain.getData(player);

        if (pd.has(PlayerData.Toggle.BLOCK)) {
            if (ItemPattern.hasPattern(ItemPattern.P_BLOCK, event.getItemInHand())) {
                BaseItem bi = BaseItem.toBaseItem(event.getItemInHand());
                if (bi != null) {
                    BaseBlock bb = BaseBlock.toBlock(bi);
                    if (bb != null) {
                        bb.onPlace(event);
                    }
                    if (bi.getItemBuilder().getAttributes().getType() == ItemBuilder.Type.LINE) {
                        TaskManager.lineTask(player, bi, event.getBlockPlaced());
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = false)
    public void onBlockToEvent(BlockFromToEvent event) {
        if (!event.isCancelled() && event.getBlock().getType() == Material.DRAGON_EGG && !Config.isDragonEggTeleport())
            event.setCancelled(true);
    }
}
