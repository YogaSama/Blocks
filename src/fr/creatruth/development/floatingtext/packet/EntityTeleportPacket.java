package fr.creatruth.development.floatingtext.packet;

import fr.creatruth.development.floatingtext.PacketLocation;
import fr.creatruth.development.reflection.PackAPI;
import fr.creatruth.development.reflection.ClassAccess;
import fr.creatruth.development.reflection.ConstructorBuilder;
import org.bukkit.Location;

public class EntityTeleportPacket extends EntityPacket {

    /**
     * @param id
     */
    public EntityTeleportPacket(int id, Location location) {
        PacketLocation pLoc = new PacketLocation(location);
        int x = pLoc.getX();
        int y = pLoc.getY();
        int z = pLoc.getZ();
        byte yaw = pLoc.getYaw();
        byte pitch = pLoc.getPitch();

        ClassAccess<?> access = ClassAccess.forName(PackAPI.NMS.get("PacketPlayOutEntityTeleport"));
        ConstructorBuilder cb = access.getConstructor(int.class, int.class, int.class, int.class, byte.class, byte.class, boolean.class);
        this.packet = cb.newInstance(id, x, y, z, yaw, pitch, false);
    }
}
