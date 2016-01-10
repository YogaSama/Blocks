package fr.creatruth.blocks.block.type;

import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.block.BaseBlock;
import fr.creatruth.development.material.MatData;
import org.bukkit.Material;
import org.bukkit.block.Banner;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class BannerBlock extends BaseBlock {

    @Override
    public void onPick(PickBlockEvent event) {
        if (event.isCursorType(Material.BANNER) && event.containsTargetType(Material.WALL_BANNER, Material.STANDING_BANNER)) {
            Banner    banner = (Banner) event.getTarget().getState();
            MatData       md = new MatData(event.getCursor().getType(), event.getTarget().getData());
            ItemStack   item = itemManager().getBuilder(md).build();
            ItemMeta    meta = item.getItemMeta();
            BannerMeta bMeta = (BannerMeta) meta;
            bMeta.setBaseColor(banner.getBaseColor());
            bMeta.setPatterns(banner.getPatterns());
            item.setItemMeta(bMeta);
            event.setCursor(item);
        }
    }
}
