/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.inventory.gui;

import fr.creatruth.blocks.inventory.page.PagedInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.*;

/**
 * Garde en mémoire les inventaires spéciaux ouvert
 * par les joueurs.
 */
public class GUIManager {

    private static GUIManager instance;

    private Map<UUID, PagedInventory> handle;

    private GUIManager() {
        handle = new HashMap<>();
    }

    public static GUIManager getInstance() {
        return instance == null ?  instance = new GUIManager() : instance;
    }

    public PagedInventory get(UUID uuid) {
        return handle.get(uuid);
    }

    public boolean contain(UUID uuid) {
        return handle.containsKey(uuid);
    }

    public PagedInventory set(Player player, String title, int rows, Collection collection) {
        int maxPage = (int) Math.ceil((double) collection.size() / ((rows - 1) * 9));
        return set(player, title, rows, maxPage);
    }

    public PagedInventory set(Player player, String title, int maxPage) {
        return set(player, title, 6, maxPage);
    }

    public PagedInventory set(Player player, String title, int rows, int maxPage) {
        List<PreInventory> list = new ArrayList<>();
        for (int i = 0; i < maxPage; i++)
            list.add(new PreInventory(rows, title));

        return set(player, new PagedInventory(list));
    }

    public PagedInventory set(Player player, PagedInventory pi) {
        handle.put(player.getUniqueId(), pi);
        Inventory inv = pi.getCustomInv(1).getInv();
        player.openInventory(inv);
        return pi;
    }

    public boolean remove(UUID uuid) {
        return handle.remove(uuid) != null;
    }
}
