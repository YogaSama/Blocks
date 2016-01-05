/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block;

public class OrientableBlock extends BaseBlock {

    public byte getOrientation() {
        return getOrientation(0);
    }

    public byte getReverseOrientation() {
        return getOrientation(180);
    }

    public byte getOrientation(int angle) {
        float yaw = player.getEyeLocation().getYaw();
        return (byte) (((yaw < 0 ? yaw + 405 : yaw + 45) + angle) % 360 / 90);
    }
}
