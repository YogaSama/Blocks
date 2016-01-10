/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.runnable;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.player.PlayerData;
import org.bukkit.entity.Player;

public class SneakTask implements Runnable {

    private String taskName;
    private Player player;
    private PlayerData playerData;
    private double locY;

    public SneakTask(String taskName, Player player) {
        this.taskName = taskName;
        this.player = player;
        this.playerData = BMain.getData(player);
        this.locY = player.getLocation().getY() + 2;
    }

    @Override
    public void run() {
        if (player.isOnline()) {
            if (player.isSneaking()) {
                if (player.isFlying()) {
                    double currentY = player.getLocation().getY();

                    if (locY > currentY)
                        playerData.setSneak(false);

                    else
                        playerData.setSneak(true);

                    this.locY = player.getLocation().getY();
                    return;
                }
                playerData.setSneak(true);
            }
        }
        TaskManager.cancelTaskByName(taskName);
    }
}