/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block;

import org.bukkit.entity.Player;

abstract public class OrientableBlock extends BaseBlock implements Placeable {

    public byte getOrientation(Player player) {
        return getOrientation(player, 0);
    }

    public byte getOrientation(Player player, int angle) {
        float yaw = player.getEyeLocation().getYaw();
        return (byte) (((yaw < 0 ? yaw + 405 : yaw + 45) + angle) % 360 / 90);
    }
}
