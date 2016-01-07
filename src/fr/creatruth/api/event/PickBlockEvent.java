package fr.creatruth.api.event;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

public class PickBlockEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private boolean                cancelled = false;

    private Block                  target;
    private InventoryCreativeEvent creativeEvent;

    public PickBlockEvent(Block target, InventoryCreativeEvent creativeEvent) {
        this.target        = target;
        this.creativeEvent = creativeEvent;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Block getTarget() {
        return target;
    }

    public boolean isTargetType(Material material) {
        return target.getType() == material;
    }

    public boolean containsTargetType(Material... materials) {
        for (Material material : materials)
            if (isTargetType(material))
                return true;
        return false;
    }

    public void setCursor(ItemStack item) {
        creativeEvent.setCursor(item);
    }

    public ItemStack getCursor() {
        return creativeEvent.getCursor();
    }

    public boolean isCursorType(Material material) {
        return creativeEvent.getCursor().getType() == material;
    }

    public boolean containsCursorType(Material... materials) {
        for (Material material : materials)
            if (isCursorType(material))
                return true;
        return false;
    }

    public Player getPlayer() {
        return (Player) creativeEvent.getWhoClicked();
    }
}
