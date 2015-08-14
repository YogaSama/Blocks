/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;

public class BaseBlock {

    /**
     * Convertir l'item en bloc.
     * @param baseItem L'item a transformer.
     * @return L'item spécial.
     */
    public static BaseBlock toBlock(BaseItem baseItem) {
        return Materials.getBaseBlock(baseItem);
    }

    protected BaseItem  baseItem;
    protected Material  material;
    protected byte      data;

    protected Player    player;
    protected Block     block;

    public BaseBlock(BaseItem baseItem) {
        this.baseItem = baseItem;
        this.material = baseItem.getItemBuilder().getMaterial();
        this.data = baseItem.getItemBuilder().getData();
    }

    public BaseItem getBaseItem() {
        return baseItem;
    }

    public Player getPlayer() {
        return player;
    }

    public Block getBlock() {
        return block;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public void onPlace(BlockPlaceEvent event) {
        player = event.getPlayer();
        block = event.getBlock();
    }
}
