package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.development.item.ItemManager;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class BannerBlock extends BaseBlock {

    @Override
    public void onPick(Block target, InventoryCreativeEvent event) {
        super.onPick(target, event);

        if (cursor.getType() == Material.BANNER && (target.getType() == Material.WALL_BANNER || target.getType() == Material.STANDING_BANNER)) {
            BlockState state = target.getState();
            Banner    banner = (Banner) state;
            ItemStack   item = ItemManager.getInstance().getBuilder(cursor.getType(), target.getData()).build();
            ItemMeta    meta = item.getItemMeta();
            BannerMeta bMeta = (BannerMeta) meta;
            bMeta.setBaseColor(banner.getBaseColor());
            bMeta.setPatterns(banner.getPatterns());
            item.setItemMeta(bMeta);
            event.setCursor(item);
        }
    }
}
