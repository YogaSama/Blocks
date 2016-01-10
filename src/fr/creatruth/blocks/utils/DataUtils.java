package fr.creatruth.blocks.utils;

import fr.creatruth.development.reflection.PackAPI;
import fr.creatruth.development.reflection.ClassAccess;
import org.bukkit.Material;

public class DataUtils {

    private static boolean     init;
    private static ClassAccess cCraft;
    private static ClassAccess cBlock;
    private static ClassAccess cData;

    static {
        try {
            cCraft  = ClassAccess.forName(PackAPI.OBC.get("util.CraftMagicNumbers"));
            cData   = ClassAccess.forName(PackAPI.NMS.get("IBlockData"));
            cBlock  = ClassAccess.forName(PackAPI.NMS.get("Block"));
            init    = true;
        } catch (Exception e) {}
    }

    public static boolean exist(Material material, byte data) {
        return getRealData(material, data) == data;
    }

    public static byte getRealData(Material material, byte data) {
        if (!init) return data;

        Object block     = cCraft.getMethod("getBlock",  Material.class).invoke(null, material);
        Object blockData = cBlock.getMethod("fromLegacyData", int.class).invoke(block, data);

        if (blockData == null) return data;
        int realData = (int) cBlock.getMethod("toLegacyData", cData.getClazz()).invoke(block, blockData);
        return (byte) realData;
    }
}
