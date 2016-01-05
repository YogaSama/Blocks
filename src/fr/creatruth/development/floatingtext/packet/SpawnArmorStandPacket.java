package fr.creatruth.development.floatingtext.packet;

import fr.creatruth.development.floatingtext.MetadataIndex;
import fr.creatruth.development.floatingtext.PacketLocation;
import fr.creatruth.development.floatingtext.WatcherBuilder;
import fr.creatruth.development.reflection.PackAPI;
import fr.creatruth.development.reflection.ClassAccess;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

public class SpawnArmorStandPacket extends EntityPacket {

    private static WatcherBuilder builder;

    static {
        builder = new WatcherBuilder();
        builder.set   (MetadataIndex.STATUS, 0);
        builder.setBit(MetadataIndex.STATUS,           ArmorStandFlag.INVISIBLE.VALUE,        true);
        builder.set   (MetadataIndex.HEALTH, .5F);
        builder.set   (MetadataIndex.SHOW_NAME_TAG, 1);
        builder.setBit(MetadataIndex.ARMORSTAND_FLAGS, ArmorStandFlag.SMALL_ARMORSTAND.VALUE, true);
        builder.setBit(MetadataIndex.ARMORSTAND_FLAGS, ArmorStandFlag.REMOVE_BASEPLATE.VALUE, true);
        builder.setBit(MetadataIndex.ARMORSTAND_FLAGS, ArmorStandFlag.HAS_ARMS.VALUE,         false);
        builder.setBit(MetadataIndex.ARMORSTAND_FLAGS, ArmorStandFlag.HAS_GRAVITY.VALUE,      false);
        builder.setBit(MetadataIndex.ARMORSTAND_FLAGS, ArmorStandFlag.MARKER.VALUE,           true);
    }

    private WatcherBuilder watcherBuider;

    /**
     *
     * @param location
     * @param name
     */
    public SpawnArmorStandPacket(int id, Location location, String name) {
        ClassAccess<?> access = ClassAccess.forName(PackAPI.NMS.get("PacketPlayOutSpawnEntityLiving"));
        this.packet = access.newInstance();
        this.watcherBuider = builder.clone();

        setId(id);
        setEntityType(EntityType.ARMOR_STAND);
        setLocation(location);
        setName(name);
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        set("a", id);
    }

    /**
     *
     * @param type
     */
    public void setEntityType(EntityType type) {
        set("b", type.getTypeId());
    }

    /**
     *
     * @param location
     */
    public void setLocation(Location location) {
        PacketLocation pckLoc = new PacketLocation(location);
        set("c", pckLoc.getX());
        set("d", pckLoc.getY());
        set("e", pckLoc.getZ());
        set("i", pckLoc.getYaw());
        set("j", pckLoc.getPitch());
        set("k", pckLoc.getAngle());
    }

    /**
     *
     * @param velocity
     */
    public void setVelocity(Vector velocity) {
        set("f", velocity.getX());
        set("g", velocity.getY());
        set("h", velocity.getZ());
    }

    /**
     *
     * @param text
     * @return
     */
    private void setName(String text) {
        watcherBuider.set(MetadataIndex.NAME_TAG, text);
        set("l", watcherBuider.build());
    }

    public enum ArmorStandFlag {
        SMALL_ARMORSTAND(0x01),
        HAS_GRAVITY(0x02),
        HAS_ARMS(0x04),
        REMOVE_BASEPLATE(0x08),
        MARKER(0x10),
        INVISIBLE(0x20),
        ;

        private final int VALUE;

        ArmorStandFlag(int value) {
            this.VALUE = value;
        }
    }
}
