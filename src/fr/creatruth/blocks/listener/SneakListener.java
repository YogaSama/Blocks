/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.listener;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.runnable.TaskManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class SneakListener extends AListener {

    @EventHandler(ignoreCancelled = false)
    public void onSneak(PlayerToggleSneakEvent event) {
        if (event.isCancelled()) return;

        if (event.isSneaking())
            TaskManager.sneakTask(event.getPlayer());

        else
            BMain.getData(event.getPlayer()).setSneak(false);
    }
}