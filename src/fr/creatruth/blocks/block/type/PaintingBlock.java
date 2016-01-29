/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block.type;

import fr.creatruth.api.event.PickBlockEvent;
import fr.creatruth.blocks.block.BaseBlock;
import fr.creatruth.blocks.block.Pickable;
import fr.creatruth.blocks.block.item.ItemBuilder;
import fr.creatruth.blocks.block.material.MatData;
import org.bukkit.Art;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Painting;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class PaintingBlock extends BaseBlock implements Pickable {

    @Override
    public void onPick(PickBlockEvent event) {
        HumanEntity human = event.getPlayer();
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
            MatData md = new MatData(Material.PAINTING, (byte) painting.getArt().getId());
            event.setCursor(itemManager().getBuilder(md).build());
        }
    }

    public void onPlaceEntity(ItemBuilder builder, HangingPlaceEvent event) {
        Entity entity = event.getEntity();
        Painting painting = entity instanceof Painting ? (Painting) entity : null;
        if (painting != null)
            painting.setArt(Art.getById(builder.getKey().getData()));
    }
}
