/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.utils.PaintingUtils;

import fr.creatruth.development.item.ItemBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Painting;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class PaintingBlock extends BaseBlock {

    @Override
    public void onPick(Block target, InventoryCreativeEvent event) {
        HumanEntity human = event.getWhoClicked();
        Set<Painting> paintings = new HashSet<>();

        for (Entity e : human.getNearbyEntities(5, 5, 5)) {
            if (e instanceof Painting) {
                paintings.add((Painting) e);
            }
        }

        Location loc = human.getEyeLocation();
        Vector dir   = loc.getDirection().normalize().multiply(0.25);
        double minDistance = 5;

        Painting painting = null;

        for (int i = 0; i < 20; i++) {
            loc.add(dir);
            for (Painting current : paintings) {
                double distance = loc.distance(current.getLocation());

                if (distance < minDistance) {
                    painting = current;
                    minDistance = distance;
                }

                if (painting != null && loc.clone().add(dir).getBlock().getType() != Material.AIR)
                    break;
            }
        }

        if (painting != null) {
            /*getItemBuilder().setData((byte) painting.getArt().getId());
            updateName();
            event.setCursor(getItem());*/
        }
    }

    public void onPlaceEntity(ItemBuilder builder, HangingPlaceEvent event) {
        PaintingUtils.setArt(event.getEntity(), builder.getKey().getData());
    }
}
