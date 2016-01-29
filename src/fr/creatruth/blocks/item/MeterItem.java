/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.item;

import fr.creatruth.blocks.utils.*;
import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.messages.Message;

import fr.creatruth.globalapi.customitem.CustomItem;
import fr.creatruth.globalapi.customitem.action.InteractableItem;
import fr.creatruth.globalapi.external.ParticleEffect;
import fr.creatruth.globalapi.inventory.InventoryHistory;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MeterItem extends CustomItem implements InteractableItem {

    private static MeterItem        instance;

    private MeterItem() {
        super(new ItemStack(Material.STRING));
        CUSTOM_ITEM_MAP.register(this);
    }

    public static MeterItem getInstance() {
        if (instance == null) instance = new MeterItem();
        instance.getItem().setType(Config.getMeterWand());
        return instance;
    }

    @Override
    public void onInteract(InventoryHistory history, PlayerInteractEvent e) {
        Meter m  = new Meter(e.getItem());
        Player p = e.getPlayer();
        Block b  = e.getClickedBlock();

        switch (e.getAction()) {
            case LEFT_CLICK_BLOCK:
                m.setPos1(b.getLocation());
                m.updateMeter(p.getLocation().toVector());
                Message.EVENT_METER_FIRSTCLICK.sendRaw(p,
                                                       Double.toString(m.pos1.getX()),
                                                       m.pos1.getY(),
                                                       Double.toString(m.pos1.getZ())
                );
                m.sendParticles(p, m.pos1);
                break;

            case RIGHT_CLICK_BLOCK:
            case RIGHT_CLICK_AIR:
                if (m.pos1 != null) {
                    b = b == null ? BlockUtils.getTargetBlock(p, 201) : b;

                    if (b != null && b.getType() != Material.AIR) {
                        m.setPos2(b.getLocation());
                        m.updateMeter(p.getLocation().toVector());

                        int dx          = MathsUtils.getDistance(m.pos1.getX(), m.pos2.getX());
                        int dy          = MathsUtils.getDistance(m.pos1.getY(), m.pos2.getY());
                        int dz          = MathsUtils.getDistance(m.pos1.getZ(), m.pos2.getZ());
                        double distance = MathsUtils.getDistance(m.pos1, m.pos2);

                        Message.EVENT_METER_INFO.sendRaw(p,
                                                         Double.toString(m.pos2.getX()),
                                                         m.pos2.getY(),
                                                         Double.toString(m.pos2.getX()),
                                                         Double.toString(distance),
                                                         Integer.toString(dx), dy,
                                                         Integer.toString(dz),
                                                         Integer.toString(dx * dy * dz)
                        );
                        m.sendParticles(p, m.pos1);
                        m.sendParticles(p, m.pos2);
                    }
                }
                else
                    Message.EVENT_METER_ERROR.send(p, Message.Type.ALERT);
                break;

            default:
                return;
        }
        e.setCancelled(true);
    }

    public static class Meter {

        private static final Vector     CORRECTION    = new Vector(.5, .5, .5);

        private static final String     BASIC_NAME    = "§3Meter : §c???";
        private static final String     FORMAT        = "§3Meter : §6%.2f";

        private static final String     COORD_FORMAT  = "§7Pos%d : (§6%.0f§7, §6%.0f§7, §6%.0f§7)";
        private static final Pattern    COORD_PATTERN = Pattern.compile("\\(.{2}(-?\\d+).{2}, .{2}(-?\\d+).{2}, .{2}(-?\\d+).{2}\\)");

        private String                  name;
        private ItemStack               item;
        private Vector                  pos1;
        private Vector                  pos2;

        public Meter(ItemStack item) {
            this.name = ItemUtils.getDisplayName(item);
            this.item = item;
            this.pos1 = extractPos1(item);
        }

        public Vector extractPos1(ItemStack item) {
            List<String> l = ItemUtils.getLore(item);

            if (l != null && l.size() > 0) {
                Matcher m = COORD_PATTERN.matcher(l.get(0));
                if (m.find()) {
                    double d1 = NumberUtils.getDouble(m.group(1), 0);
                    double d2 = NumberUtils.getDouble(m.group(2), 0);
                    double d3 = NumberUtils.getDouble(m.group(3), 0);

                    return new Vector(d1, d2, d3);
                }
            }
            return null;
        }

        public void updateMeter(Vector vector) {
            if (pos1 != null && vector != null) {
                ItemUtils.setName(item, String.format(FORMAT, getDistance(vector)));
            }
            else {
                ItemUtils.setName(item, BASIC_NAME);
            }
        }

        private double getDistance(Vector vector) {
            return vector.distance(pos1.clone().add(CORRECTION));
        }

        public void setPos1(Location location) {
            pos1 = location.toVector();
            pos2 = null;
            setLore(MathsUtils.getDistance(0, 0));
        }

        public void setPos2(Location location) {
            pos2 = location.toVector();
            setLore(MathsUtils.getDistance(pos1, pos2));
        }

        private void setLore(double distance) {
            List<String> lore = new ArrayList<>();
            lore.add(String.format(COORD_FORMAT, 1, pos1.getX(), pos1.getY(), pos1.getZ()));

            if (pos2 != null) {
                lore.add(String.format(COORD_FORMAT, 2, pos2.getX(), pos2.getY(), pos2.getZ()));
                lore.add("");
                lore.add(String.format("§fDistance : §e%f", distance));
            }
            else {
                lore.add("§7Pos2 : §c???");
            }

            ItemUtils.setLore(item, lore);
        }

        private void sendParticles(Player player, Vector pos) {
            double x = pos.getBlockX() + .5;
            double y = pos.getBlockY() + .7;
            double z = pos.getBlockZ() + .5;

            World w = player.getWorld();
            Location[] locations = {
                    new Location(w, x, y + 0.5, z),
                    new Location(w, x, y - 0.5, z),
                    new Location(w, x + 0.5, y, z),
                    new Location(w, x - 0.5, y, z),
                    new Location(w, x, y, z + 0.5),
                    new Location(w, x, y, z - 0.5)
            };

            for (Location location : locations) ParticleEffect.FIREWORKS_SPARK.display(0, 0, 0, 0, 1, location, player);
        }
    }
}
