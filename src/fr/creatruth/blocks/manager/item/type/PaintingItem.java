/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type;

import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;


import fr.creatruth.blocks.manager.utils.PaintingUtils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Painting;

import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashSet;

import java.util.Set;

public class PaintingItem extends BaseItem {

    private static final byte[] DATA = new byte[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25};

    public PaintingItem(ItemStack item, Materials materials) {
        super(item, materials);
        ib.setDataTable(DATA);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        HumanEntity human = event.getWhoClicked();
        Set<Painting> paintings = new HashSet<>();

        for (Entity e : human.getNearbyEntities(5, 5, 5)) {
            if (e instanceof Painting) {
                paintings.add((Painting) e);
            }
        }
        Vector dir = human.getEyeLocation().getDirection().normalize().multiply(0.25);
        Location loc = human.getEyeLocation().clone();
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
            getItemBuilder().setData((byte) painting.getArt().getId());
            updateName();
            event.setCursor(getItem());
        }
    }

    public void onPlaceEntity(HangingPlaceEvent event) {
        PaintingUtils.setArt(event.getEntity(), ib.getData());
    }
}
