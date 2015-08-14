/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.tools;

import static fr.creatruth.blocks.manager.utils.ReflectUtils.*;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public enum Particle {

    FIREWORKS_SPARK("fireworksSpark");

    private static boolean enable;

    private static Class packetClass;
    private static Class craftPlayerClass;
    private static Class particlePacketClass;

    private String particleName;

    Particle(String particleName) {
        this.particleName = particleName;
    }

    public void sendToPlayer(Player player, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) {
        try {
            this.sendToPlayer(player, (float) location.getX(), (float) location.getY(), (float) location.getZ(), offsetX, offsetY, offsetZ, speed, count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendToPlayer(Player player, float x, float y, float z, float offsetX, float offsetY, float offsetZ, float speed, int count) throws Exception {
        Object packet = getPacket();

        if (enable) {
            setStaticValue(packet, "a", this.particleName);
            setStaticValue(packet, "b", x);
            setStaticValue(packet, "c", y);
            setStaticValue(packet, "d", z);
            setStaticValue(packet, "e", offsetX);
            setStaticValue(packet, "f", offsetY);
            setStaticValue(packet, "g", offsetZ);
            setStaticValue(packet, "h", speed);
            setStaticValue(packet, "i", count);

            send(player, packet);
        }
    }

    private static Object getPacket() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        Class clazz = getParticlePacket();
        if (clazz == null) {
            enable = false;
            throw new ClassNotFoundException("Particle class not found or don't work with your server version.");
        }
        enable = true;

        return clazz.newInstance();
    }

    private static Class getParticlePacket() throws ClassNotFoundException {
        if (particlePacketClass == null) {
            particlePacketClass = Class.forName("net.minecraft.server." + getServerVersion() + ".PacketPlayOutWorldParticles");
        }
        return particlePacketClass;
    }

    private static Class getCraftPlayer() throws ClassNotFoundException {
        if (craftPlayerClass == null) {
            craftPlayerClass = Class.forName("org.bukkit.craftbukkit." + getServerVersion() + ".entity.CraftPlayer");
        }
        return craftPlayerClass;
    }

    private static Class getPacketClass() throws ClassNotFoundException {
        if (packetClass == null) {
            packetClass = Class.forName("net.minecraft.server." + getServerVersion() + ".Packet");
        }
        return packetClass;
    }

    private static void send(Player player, Object packet) throws Exception {
        Object cp = getCraftPlayer().cast(player);
        Object pc = getValue(invoke(cp, reflexiveMethod(cp.getClass(), "getHandle")), "playerConnection");
        invoke(pc, reflexiveMethod(pc.getClass(), "sendPacket", getPacketClass()), packet);
    }
}
