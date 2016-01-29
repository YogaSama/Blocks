/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.player;

import org.bukkit.entity.HumanEntity;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataHandler {

    private HashMap<UUID, PlayerData> playerDataMap;

    public PlayerDataHandler() {
        playerDataMap = new HashMap<>();
    }

    public PlayerData getData(HumanEntity player) {
        if (!playerDataMap.containsKey(player.getUniqueId())) {
            playerDataMap.put(player.getUniqueId(), new PlayerData(player.getUniqueId()));
        }
        return playerDataMap.get(player.getUniqueId());
    }

    public void remove(HumanEntity player) {
        playerDataMap.remove(player.getUniqueId());
    }
}
