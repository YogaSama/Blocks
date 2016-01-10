/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.runnable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.HashMap;
import java.util.Random;

import static fr.creatruth.blocks.BMain.*;

public class TaskManager {

    static HashMap<String, Integer> taskList    = new HashMap<>();
    static BukkitScheduler          scheduler   = Bukkit.getScheduler();

    public static HashMap<String, Integer> getTaskList() {
        return taskList;
    }

    public static String getUniqueName(String taskName) {
        String name;
        Random rdm = new Random();
        do {
            name = taskName + "_" + rdm.nextInt(100000);
        } while (taskExist(name));
        return name;
    }

    public static boolean taskExist(String taskName) {
        return taskName != null && taskList.containsKey(taskName);
    }

    public static int getTaskId(String taskName) {
        if (taskExist(taskName)) return taskList.get(taskName);
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
        cancelTaskByName(taskName);
        int id = runSyncRepeatingTask(runnable, refresh);
        taskList.put(taskName, id);
        return id;
    }

    public static void run(Runnable runnable) {
        scheduler.runTask(instance, runnable);
    }

    public static void runLater(Runnable runnable, long ticks) {
        scheduler.runTaskLater(instance, runnable, ticks);
    }

    public static int runSyncRepeatingTask(Runnable runnable, long refresh) {
        return scheduler.scheduleSyncRepeatingTask(instance, runnable, 0, refresh);
    }

    public static void runAsynchronously(Runnable runnable) {
        scheduler.runTaskAsynchronously(instance, runnable);
    }

    /*
     * TASKS
     */

    public static void sneakTask(Player player) {
        String taskName = player.getName();
        runSyncRepeatingTask(taskName, new SneakTask(taskName, player), 3);
    }

    /*public static void lineTask(Player player, BaseItem baseItem, Block block) {
        String taskName = UUID.randomUUID().toString();
        Attribute atb = baseItem.getItemBuilder().getAttribute();
        int length = atb.getInt(0);
        Face face = atb.getFace(1);
        Runnable r = new LineTask(taskName, player, block, face, length);
        TaskManager.runSyncRepeatingTask(taskName, r, Config.getLineDelayPerBlock());
    }*/
}
