/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.listener;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.runnable.SneakTask;
import fr.creatruth.globalapi.task.TaskManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class SneakListener extends AListener {

    @EventHandler(ignoreCancelled = true)
    public void onSneak(PlayerToggleSneakEvent event) {
        if (event.isSneaking()) {
            String taskName = event.getPlayer().getName();
            TaskManager.runSyncRepeatingTask(taskName, new SneakTask(taskName, event.getPlayer()), 3);
        }
        else
            BMain.getData(event.getPlayer()).setSneak(false);
    }
}