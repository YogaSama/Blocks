package fr.creatruth.api.event;

import fr.creatruth.blocks.block.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlocksPlaceEvent extends Event implements Cancellable {

    private static final HandlerList handlers = new HandlerList();

    private boolean         cancelled = false;

    private BlockPlaceEvent place;
    private ItemBuilder     builder;

    public BlocksPlaceEvent(ItemBuilder builder, BlockPlaceEvent place) {
        this.builder = builder;
        this.place   = place;
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

    public ItemBuilder getBuilder() {
        return builder;
    }

    public Material getMaterial() {
        return builder.getKey().getMaterial();
    }

    public byte getData() {
        return (byte) builder.getKey().getData();
    }

    public Block getBlock() {
        return place.getBlock();
    }

    public Player getPlayer() {
        return place.getPlayer();
    }
}
