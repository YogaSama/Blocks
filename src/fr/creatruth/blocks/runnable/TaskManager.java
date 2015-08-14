/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.runnable;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.tools.Attributes;
import fr.creatruth.blocks.manager.tools.Face;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.UUID;

public class TaskManager {

    static HashMap<String, Integer> taskList = new HashMap<>();
    static BukkitScheduler scheduler = Bukkit.getScheduler();
    static BMain instance = BMain.instance;

    public static boolean taskExist(String taskName) {
        return taskList.containsKey(taskName);
    }

    public static int getTaskId(String taskName) {
        if (taskExist(taskName))
            return taskList.get(taskName);
        return 0;
    }

    public static boolean cancelTaskByName(String taskName) {
        if (taskExist(taskName)) {
            int taskId = getTaskId(taskName);
            taskList.remove(taskName);
            scheduler.cancelTask(taskId);
            return true;
        }
        return false;
    }

    public static int runSyncRepeatingTask(String taskName, Runnable runnable, long refresh) {
        if (taskExist(taskName))
            cancelTaskByName(taskName);

        int id = runSyncRepeatingTask(runnable, refresh);
        taskList.put(taskName, id);
        return id;
    }

    public static int runSyncRepeatingTask(Runnable runnable, long refresh) {
        return scheduler.scheduleSyncRepeatingTask(instance, runnable, refresh, refresh);
    }

    public static void runAsynchronously(Runnable runnable) {
        scheduler.runTaskAsynchronously(instance, runnable);
    }

    public static void run(Runnable runnable) {
        scheduler.runTask(instance, runnable);
    }

    public static void runLater(Runnable runnable, long ticks) {
        scheduler.runTaskLater(instance, runnable, ticks);
    }

    /*
     * TASKS
     */

    public static void sneakTask(Player player) {
        String taskName = player.getName();
        runSyncRepeatingTask(taskName, new SneakTask(taskName, player), 3);
    }

    public static void lineTask(Player player, BaseItem baseItem, Block block) {
        String taskName = UUID.randomUUID().toString();
        Attributes atb = baseItem.getItemBuilder().getAttributes();
        int length = atb.getInt(0);
        Face face = atb.getFace(1);
        Runnable r = new LineTask(taskName, player, block, face, length);
        TaskManager.runSyncRepeatingTask(taskName, r, Config.getLineDelayPerBlock());
    }
}
