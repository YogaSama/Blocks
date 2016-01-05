/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.utils;

import fr.creatruth.development.floatingtext.FloatingText;
import net.minecraft.server.v1_8_R2.*;
import org.bukkit.*;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R2.CraftWorld;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import particle.ParticleEffect;

import java.util.HashSet;
import java.util.Set;

public class BlockUtils {

    private static final Set<Material> DEFAULT;
    private static final Set<Material> TRANSPARENTS;

    static {
        DEFAULT = new HashSet<>();
        DEFAULT.add(Material.AIR);

        TRANSPARENTS = new HashSet<>();
        TRANSPARENTS.add(Material.AIR);
        TRANSPARENTS.add(Material.WATER);
        TRANSPARENTS.add(Material.LAVA);
        TRANSPARENTS.add(Material.STATIONARY_WATER);
        TRANSPARENTS.add(Material.STATIONARY_LAVA);
    }

    public static Block getTargetBlock(HumanEntity human, int distance) {
        return human.getTargetBlock(DEFAULT, distance);
    }

    public static Block getExactlyTargetBlock(HumanEntity human, int distance) {
        return getExactlyTargetBlock(human, TRANSPARENTS, distance, .02);
    }

    public static Block getExactlyTargetBlock(HumanEntity human, Set<Material> transparent, int distance, double precision) {
        if (precision > 1)  precision = 1;
        if (distance > 120) distance = 120;

        Location   o = human.getEyeLocation();
        Location loc = o.clone();
        Vector   dir = o.getDirection().normalize().multiply(.05);
        Block      b = null;

        WorldServer ws = ((CraftWorld) human.getWorld()).getHandle();

        while (b == null && loc.distance(o) < distance) {

            Vec3D v1 = new Vec3D(loc.getX() + precision, loc.getY() + precision, loc.getZ() + precision);
            Vec3D v2 = new Vec3D(loc.getX() - precision, loc.getY() - precision, loc.getZ() - precision);

            MovingObjectPosition mop = ws.rayTrace(v1, v2, true);
            if (mop != null && mop.entity == null) {

                b = human.getWorld().getBlockAt(mop.a().getX(), mop.a().getY(), mop.a().getZ());

                if (transparent.size() > 0) {
                    Material mat = b.getType();
                    if (transparent.contains(mat))
                        b = null;
                }
            }

            loc.add(dir);
        }

        if (b == null) b = loc.getBlock();
        return b;
    }

    /*public static MovingObjectPosition rayTrace(WorldServer ws, Vec3D vec3d, Vec3D vec3d1) {
        return rayTrace(ws, vec3d, vec3d1, true, false);
    }

    public static MovingObjectPosition rayTrace(WorldServer ws, Vec3D vector1, Vec3D vector2, boolean flag, boolean solid) {
        if (Double.isNaN(vector1.a) || Double.isNaN(vector1.b) || Double.isNaN(vector1.c))
            return null;

        if (Double.isNaN(vector2.a) || Double.isNaN(vector2.b) || Double.isNaN(vector2.c))
            return null;

        int x2 = MathHelper.floor(vector2.a);
        int y2 = MathHelper.floor(vector2.b);
        int z2 = MathHelper.floor(vector2.c);

        int x1  = MathHelper.floor(vector1.a);
        int y1  = MathHelper.floor(vector1.b);
        int z1  = MathHelper.floor(vector1.c);

        BlockPosition blockposition              = new BlockPosition(x1, y1, z1);
        IBlockData iblockdata                    = ws.getType(blockposition);
        net.minecraft.server.v1_8_R2.Block block = iblockdata.getBlock();
        MovingObjectPosition mop1;

        if ((!solid || block.a(ws, blockposition, iblockdata) != null) && block.a(iblockdata, flag)) {

            mop1 = block.a(ws, blockposition, vector1, vector2);
            if (mop1 != null) return mop1;
        }

        mop1 = null;

        for (int i = 50; i > 0; i--) {
            if (Double.isNaN(vector1.a) || Double.isNaN(vector1.b) || Double.isNaN(vector1.c))
                return null;

            // Si les 2 vecteurs ont les mêmes coordonnées.
            if (x1 == x2 && y1 == y2 && z1 == z2)
                return mop1;

            boolean flagX, flagY, flagZ;
            flagX = flagY = flagZ = true;

            double dax, day, daz; dax = day = daz = 999;

            /*
             * Si vec3d1 a sont axe x > à vec3d.x
             * On augmente vec3d.x de1,
             *
            if      (x2 > x1)  dax = x1 + 1;
            else if (x2 < x1)  dax = x1;
            else               flagX = false;

            // Idem avec y
            if      (y2 > y1) day = y1 + 1;
            else if (y2 < y1) day = y1;
            else              flagY = false;

            // Idem avec z
            if      (z2 > z1) daz = z1 + 1;
            else if (z2 < z1) daz = z1;
            else              flagZ = false;

            double dfx, dfy, dfz; dfx = dfy = dfz = 999;

            /*
             * Différence de distance entre les 2 vecteurs.
             *
            double      dx = vector2.a - vector1.a;
            double      dy = vector2.b - vector1.b;
            double      dz = vector2.c - vector1.c;

            if (flagX) dfx = (dax - vector1.a) / dx;
            if (flagY) dfy = (day - vector1.b) / dy;
            if (flagZ) dfz = (daz - vector1.c) / dz;

            if (dfx == -0) dfx = -1.0E-4;
            if (dfy == -0) dfy = -1.0E-4;
            if (dfz == -0) dfz = -1.0E-4;

            EnumDirection enumdirection;
            if      (dfx < dfy && dfx < dfz) {
                enumdirection = x2 > x1 ? EnumDirection.WEST : EnumDirection.EAST;
                vector1 = new Vec3D(dax, vector1.b + dy * dfx, vector1.c + dz * dfx);
            }
            else if (dfy < dfz) {
                enumdirection = y2 > y1 ? EnumDirection.DOWN : EnumDirection.UP;
                vector1 = new Vec3D(vector1.a + dx * dfy, day, vector1.c + dz * dfy);
            }
            else {
                enumdirection = z2 > z1 ? EnumDirection.NORTH : EnumDirection.SOUTH;
                vector1 = new Vec3D(vector1.a + dx * dfz, vector1.b + dy * dfz, daz);
            }

            x1 = MathHelper.floor(vector1.a) - (enumdirection == EnumDirection.EAST  ? 1 : 0);
            y1 = MathHelper.floor(vector1.b) - (enumdirection == EnumDirection.UP    ? 1 : 0);
            z1 = MathHelper.floor(vector1.c) - (enumdirection == EnumDirection.SOUTH ? 1 : 0);

            blockposition                             = new BlockPosition(x1, y1, z1);
            IBlockData iblockdata1                    = ws.getType(blockposition);
            net.minecraft.server.v1_8_R2.Block block1 = iblockdata1.getBlock();

            if (!solid || block1.a(ws, blockposition, iblockdata1) != null) {
                if (block1.a(iblockdata1, flag)) {

                    MovingObjectPosition mop2 = block1.a(ws, blockposition, vector1, vector2);
                    if (mop2 != null)
                        return mop2;
                }
                // Air ET fluide avec data > 0 ET (Escalier ?).
                else
                    mop1 = new MovingObjectPosition(MovingObjectPosition.EnumMovingObjectType.MISS, vector1, enumdirection, blockposition);
            }
        }
        return mop1;
    }*/
}
