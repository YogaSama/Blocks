package fr.creatruth.development.floatingtext;

import fr.creatruth.blocks.runnable.TaskManager;
import fr.creatruth.development.floatingtext.packet.*;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class FloatingText {

    private static int  entity_id = -10;

    private int         id;
    private boolean     destroy;
    private String      text;
    private String      taskName;
    private Location    location;

    /**
     *
     * @param text
     */
    public FloatingText(String text) {
        this.id         =   entity_id--;
        this.destroy    =   false;
        this.text       =   text;
        this.taskName   =   null;
    }

    /**
     *
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     *
     * @return
     */
    public Location getLocation() {
        return location;
    }

    /**
     *
     * @return
     */
    public String getTaskName() {
        return taskName == null ? taskName = TaskManager.getUniqueName("TEXT") : taskName;
    }

    /**
     *
     * @return
     */
    public boolean isDestroy() {
        return destroy;
    }

    /**
     *
     * @param player
     * @param text
     */
    public void setText(Player player, String text) {
        new EntityRenamePacket(id, text).send(player);
        this.text = text;
    }

    /**
     *
     * @param player
     * @param distance
     */
    public void display(final Player player, final double distance, final double ajustY) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                if (player.isValid()) {
                    Location loc = player.getEyeLocation();
                    Vector vector = player.getLocation().getDirection();
                    vector.multiply(distance);
                    loc.add(vector.toLocation(player.getWorld()));
                    teleport(player, loc.add(0, ajustY, 0));
                }
            }
        };
        send(player, r);
    }

    /**
     *
     * @param player
     * @param distance
     * @param tick
     */
    public void displayTemp(final Player player, double distance, double ajustY, long tick) {
        display(player, distance, ajustY);
        TaskManager.runLater(new Runnable() {
            @Override
            public void run() {
                TaskManager.cancelTaskByName(taskName);
                if (player.isValid())
                    destroy(player);
            }
        }, tick);
    }

    /**
     *
     * @param player
     * @param location
     */
    public void send(Player player, Location location) {
        EntityPacket packet = new SpawnArmorStandPacket(id, ajustLocation(location), text);
        packet.send(player);
        this.location = location;
    }

    /**
     *
     * @param player
     * @param runnable
     */
    public void send(Player player, Runnable runnable) {
        EntityPacket packet = new SpawnArmorStandPacket(id, player.getLocation(), text);
        packet.send(player);
        if (TaskManager.taskExist(taskName)) destroy(player);
        TaskManager.runSyncRepeatingTask(getTaskName(), runnable, 1);
    }

    /**
     *
     * @param player
     * @param location
     * @param tick
     */
    public void sendTemp(final Player player, Location location, long tick) {
        send(player, location);
        TaskManager.runLater(new Runnable() {
            @Override
            public void run() {
                if (player.isValid())
                    destroy(player);
            }
        }, tick);
    }

    /**
     *
     * @param player
     * @param runnable
     * @param tick
     */
    public void sendTemp(final Player player, Location location, Runnable runnable, long tick) {
        send(player, location);
        if (TaskManager.taskExist(taskName)) destroy(player);
        TaskManager.runSyncRepeatingTask(getTaskName(), runnable, 1);
        TaskManager.runLater(new Runnable() {
            @Override
            public void run() {
                TaskManager.cancelTaskByName(taskName);
                if (player.isValid())
                    destroy(player);
            }
        }, tick);
    }

    /**
     *
     * @param player
     * @param location
     */
    public void teleport(Player player, Location location) {
        EntityPacket packet = new EntityTeleportPacket(id, ajustLocation(location));
        packet.send(player);
        this.location = location;
    }

    /**
     * Replace le texte au bon niveau.
     * @param location
     * @return
     */
    private Location ajustLocation(Location location) {
        return location.clone().subtract(0, .4, 0);
    }

    /**
     *
     * @param player
     */
    public void destroy(Player player) {
        if (!destroy) {
            TaskManager.cancelTaskByName(taskName);
            EntityPacket packet = new EntityDestroyPacket(id);
            packet.send(player);
            this.destroy = true;
        }
    }
}
