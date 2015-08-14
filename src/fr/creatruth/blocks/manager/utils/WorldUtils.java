/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.utils;

import org.bukkit.World;

import java.lang.reflect.Method;

public class WorldUtils {

    private static Method methodHandle;
    private static Class craftWorld_Class;

    public static void setStaticWorld(World world, boolean _static) throws Exception {
        Object craftWorld = getCraftWorldClass().cast(world);
        Object nms_World = ReflectUtils.invoke(craftWorld, getHandle(craftWorld));
        ReflectUtils.setValue(nms_World, "isStatic", _static);
    }

    public static Class getCraftWorldClass() throws Exception {
        if (craftWorld_Class == null)
            craftWorld_Class = Class.forName("org.bukkit.craftbukkit." + ReflectUtils.getServerVersion()  + ".CraftWorld");

        return craftWorld_Class;
    }

    private static Method getHandle(Object craftWorld) {
        if (methodHandle == null)
            methodHandle = ReflectUtils.reflexiveMethod(craftWorld.getClass(), "getHandle");

        return methodHandle;
    }
}
