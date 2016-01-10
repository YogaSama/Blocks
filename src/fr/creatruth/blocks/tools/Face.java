/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.tools;

import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.HashMap;

public enum Face {

    _NORTH       (BlockFace.NORTH,        "North", "north", "n"),
    _EAST        (BlockFace.EAST,         "East", "east", "e"),
    _SOUTH       (BlockFace.SOUTH,        "South", "south", "s"),
    _WEST        (BlockFace.WEST,         "West", "west", "w"),
    _UP          (BlockFace.UP,           "Up", "up", "u"),
    _DOWN        (BlockFace.DOWN,         "Down", "down", "d"),
    _NORTH_EAST  (BlockFace.NORTH_EAST,   "North_East", "north_east", "ne", "north_e", "northe"),
    _NORTH_WEST  (BlockFace.NORTH_WEST,   "North_West", "north_west", "nw", "north_w", "northw"),
    _SOUTH_EAST  (BlockFace.SOUTH_EAST,   "South_East", "south_east", "se", "south_e", "southe"),
    _SOUTH_WEST  (BlockFace.SOUTH_WEST,   "South_West", "south_west", "sw", "south_w", "southw"),
    _BLOCK       (null,                   "Block", "block", "bl", "blocks", "bloc"),
    _PLAYER      (null,                   "Player direction", "player", "pl");

    private static final HashMap<String, Face>  BY_KEY;
    private static final String                 VALID_FACES;

    static {
        BY_KEY = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (Face f : values()) {
            for (String k : f.keys) {
                BY_KEY.put(k, f);
            }
            sb.append(f.keys[0].toUpperCase()).append("§b<>§f");
        }
        VALID_FACES = sb.substring(0, sb.length() - 6);
    }

    private BlockFace   blockFace;
    private String      string;
    private String[]    keys;

    Face(BlockFace blockFace, String string, String... keys) {
        this.blockFace = blockFace;
        this.string = string;
        this.keys = keys;
    }

    public BlockFace getBlockFace() {
        return blockFace;
    }

    public String[] getKeys() {
        return keys;
    }

    @Override
    public String toString() {
        return string;
    }

    public static String getValidFaces() {
        return VALID_FACES;
    }

    public static Face getByKey(String s, Face defaultFace) {
        Face face = BY_KEY.get(s.toLowerCase());
        return face == null ? defaultFace : face;
    }

    public static Face getByOrientation(Player player) {
        float yaw = player.getEyeLocation().getYaw();
        int yawValue = (byte) (((yaw < 0 ? yaw + 360 : yaw) + 22.5 + 180) % 360 / 45);

        float pitch = player.getEyeLocation().getPitch();
        int pitchValue = pitch < -45 ? 0 : pitch < 45 ? 1 : 2;

        switch (pitchValue) {
            case 0:
                return _UP;
            case 1:
                switch (yawValue) {
                    case 0: return _NORTH;
                    case 1: return _NORTH_EAST;
                    case 2: return _EAST;
                    case 3: return _SOUTH_EAST;
                    case 4: return _SOUTH;
                    case 5: return _SOUTH_WEST;
                    case 6: return _WEST;
                    case 7: return _NORTH_WEST;
                }
            default:
                return _DOWN;
        }
    }
}
