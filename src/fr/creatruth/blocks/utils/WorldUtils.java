/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.utils;

import fr.creatruth.globalapi.reflection.ClassAccess;
import fr.creatruth.globalapi.reflection.FieldAccess;
import fr.creatruth.globalapi.reflection.PackAPI;
import org.bukkit.World;

public class WorldUtils {

    public static void setStaticWorld(World world, boolean clientSide) throws Exception {
        ClassAccess cCraftWorld = ClassAccess.forName(PackAPI.OBC.get("CraftWorld"));
        Object craftWorld       = cCraftWorld.getClazz().cast(world);
        Object nms_World        = cCraftWorld.invoke(craftWorld, cCraftWorld.getMethod("getHandle").getMethod());
        new FieldAccess(nms_World).set("isClientSide", clientSide);
    }
}
