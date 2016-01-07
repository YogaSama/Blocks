/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.listener;

import fr.creatruth.api.event.BlocksPlaceEvent;
import fr.creatruth.api.event.SwitchBlockEvent;
import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.blocks.manager.tools.BiomeTool;
import fr.creatruth.blocks.manager.tools.ItemPattern;
import fr.creatruth.blocks.manager.utils.BlockUtils;
import fr.creatruth.blocks.manager.utils.ItemUtils;
import fr.creatruth.blocks.manager.tools.Meter;
import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.player.PlayerData;

import fr.creatruth.blocks.manager.utils.InfoUtils;
import fr.creatruth.development.material.MatData;
import fr.creatruth.development.material.MatManager;
import fr.creatruth.development.material.State;
import fr.creatruth.development.item.*;
import org.bukkit.Bukkit;
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
        Block block    = event.getClickedBlock();
        Action action  = event.getAction();

        /*
         * PHYSICAL ACTION
         */
        if (action == Action.PHYSICAL) {
            if (block.getData() > 0 && block.getState().getData() instanceof PressurePlate)
                event.setCancelled(true);
            return;
        }

        Player player  = event.getPlayer();
        PlayerData pd  = BMain.getData(player);
        ItemStack item = event.getItem();

        pd.setLastBlockFace(event.getBlockFace());

        /*
         * METER
         */
        if (Meter.isMeter(item)) {
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
        else if (action          == Action.RIGHT_CLICK_BLOCK &&
                 block.getType() == Material.PISTON_MOVING_PIECE) {
                 event.setCancelled(true);
        }
        /*
         * SNEAK ACTION
         */
        else if (pd.isSneaking() && player.getGameMode() == GameMode.CREATIVE) {

            if (item != null) {
                if (    block == null &&
                        pd.has(PlayerData.Toggle.CHANGE) &&
                        canChangeFix(player)) {

                    MatData md;
                    if (!item.hasItemMeta())
                        md = new MatData(item.getTypeId(), item.getDurability());

                    else if (ItemPattern.hasPattern(ItemPattern.P_BLOCK, item)) {
                        md = ItemPattern.getMatData(ItemUtils.getDisplayName(item));
                    }
                    else
                        return;

                    ItemList list = ItemManager.getInstance().get(md.toString());
                    if (list != null) {
                        SwitchBlockEvent.Direction direction = SwitchBlockEvent.Direction.from(action);
                        if (direction != null) {
                            SwitchBlockEvent switchEvent = new SwitchBlockEvent(player, direction, list);
                            Bukkit.getPluginManager().callEvent(switchEvent);
                            if (!switchEvent.isCancelled()) {
                                if (switchEvent.getDirection() == SwitchBlockEvent.Direction.NEXT)
                                    player.setItemInHand(list.nextItem(item));
                                else
                                    player.setItemInHand(list.previousItem(item));
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
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        PlayerData pd = BMain.getData(player);
        if (!pd.has(PlayerData.Toggle.BLOCK))
            return;

        ItemStack item = event.getItemInHand();
        if (!ItemPattern.hasPattern(ItemPattern.P_BLOCK, item))
            return;

        ItemBuilder builder = ItemManager.getInstance().getBuilder(item);
        if (builder == null)
            return;

        State state = MatManager.getState(builder.getKey().getMaterial());
        BaseBlock baseBlock = state.getBase();

        if (baseBlock != null) {
            BlocksPlaceEvent blocksEvent = new BlocksPlaceEvent(builder, event);
            Bukkit.getPluginManager().callEvent(blocksEvent);
            if (!blocksEvent.isCancelled()) baseBlock.onPlace(blocksEvent);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onBlockToEvent(BlockFromToEvent event) {
        if (    event.getBlock().getType() == Material.DRAGON_EGG &&
                !Config.isDragonEggTeleport())

            event.setCancelled(true);
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
}
