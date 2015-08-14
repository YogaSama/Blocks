/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.player;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerDataHandler {

    private HashMap<UUID, PlayerData> playerDataMap;
    private HashMap<String, PlayerData> playerDataOfflineMap;

    public PlayerDataHandler() {
        playerDataMap = new HashMap<>();
        playerDataOfflineMap = new HashMap<>();
    }

    public PlayerData getData(Player player) {
        if (player.getUniqueId() != null) {
            if (!playerDataMap.containsKey(player.getUniqueId())) {
                playerDataMap.put(player.getUniqueId(), new PlayerData(player.getUniqueId()));
            }
            return playerDataMap.get(player.getUniqueId());
        }
        else {
            if (!playerDataOfflineMap.containsKey(player.getName())) {
                playerDataOfflineMap.put(player.getName(), new PlayerData(null));
            }
            return playerDataOfflineMap.get(player.getName());
        }
    }

    public void remove(Player player) {
        playerDataMap.remove(player.getUniqueId());
        playerDataOfflineMap.remove(player.getName());
    }
}
