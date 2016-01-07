package fr.creatruth.api.event;

import fr.creatruth.development.item.ItemList;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.Action;

public class SwitchBlockEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private boolean   cancelled = false;

    private Player    player;
    private Direction direction;
    private ItemList  itemList;

    public SwitchBlockEvent(Player player, Direction direction, ItemList itemList) {
        this.player    = player;
        this.direction = direction;
        this.itemList  = itemList;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Player getPlayer() {
        return player;
    }

    public Direction getDirection() {
        return direction;
    }

    public ItemList getItemList() {
        return itemList;
    }

    public enum Direction {
        PREVIOUS,
        NEXT
        ;

        public static Direction from(Action action) {
            switch (action) {
                case RIGHT_CLICK_AIR:
                case RIGHT_CLICK_BLOCK: return NEXT;
                case LEFT_CLICK_AIR:
                case LEFT_CLICK_BLOCK:  return PREVIOUS;

                default:                return null;
            }
        }
    }
}
