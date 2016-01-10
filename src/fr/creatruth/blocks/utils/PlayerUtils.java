package fr.creatruth.blocks.utils;

import fr.creatruth.development.reflection.PackAPI;
import fr.creatruth.development.reflection.ClassAccess;
import fr.creatruth.development.reflection.FieldAccess;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class PlayerUtils {

    private static Class getCraftPlayer() {
        return ClassAccess.forName(PackAPI.OBC.get("entity.CraftPlayer")).getClazz();
    }

    private static Class getPacketClass() {
        return ClassAccess.forName(PackAPI.NMS.get("Packet")).getClazz();
    }

    public static void send(Player player, Object packet) {
        Object          cp = getCraftPlayer().cast(player);
        ClassAccess access = new ClassAccess<>(cp.getClass());
        Object      invoke = access.getDeclaredMethod("getHandle").invoke(cp);
        Object  connection = new FieldAccess(invoke).get("playerConnection");

        ClassAccess connectAccess = new ClassAccess<>(connection.getClass());
        Method         sendPacket = connectAccess.getDeclaredMethod("sendPacket", getPacketClass()).getMethod();

        connectAccess.invoke(connection, sendPacket, packet);
    }
}
