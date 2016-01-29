/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.listener;

import static fr.creatruth.blocks.BMain.instance;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public abstract class AListener implements Listener {

    private static final PluginManager pluginManager = instance.getServer().getPluginManager();

    protected AListener() {
        pluginManager.registerEvents(this, instance);
    }

    public static void enable() {
        new InteractListener();
        new PhysicsListener();
        new PaintingPlaceListener();
        new MoveListener();
        new DropListener();
        new SneakListener();
        new InventoryCreativeListener();
    }
}