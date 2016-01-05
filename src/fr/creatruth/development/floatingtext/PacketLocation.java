package fr.creatruth.development.floatingtext;

import org.bukkit.Location;

public class PacketLocation {

    int     x;
    int     y;
    int     z;
    byte    yaw;
    byte    pitch;
    byte    angle;

    public PacketLocation(Location loc) {
        this.x      =   floor(loc.getX() * 32);
        this.y      =   floor(loc.getY() * 32);
        this.z      =   floor(loc.getZ() * 32);
        this.yaw    =   (byte) ((int) loc.getYaw()   * 256F / 360F);
        this.pitch  =   (byte) ((int) loc.getPitch() * 256F / 360F);
        this.angle  =   (byte) ((int) loc.getPitch() * 256F / 360F);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public byte getYaw() {
        return yaw;
    }

    public byte getPitch() {
        return pitch;
    }

    public byte getAngle() {
        return angle;
    }

    private int floor(double value) {
        int tmp = (int) value;
        return value < (double) tmp ? tmp - 1 : tmp;
    }
}
