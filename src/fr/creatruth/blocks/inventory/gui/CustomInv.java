/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.inventory.gui;

import fr.creatruth.blocks.inventory.item.CustomItem;
import fr.creatruth.blocks.inventory.item.ItemType;
import fr.creatruth.blocks.inventory.page.PagedInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

/**
 * Représente une inventaire de préfabrication.
 */
public class CustomInv {

    private Inventory inv;

    public CustomInv(Inventory inv) {
        this.inv = inv;
    }

    public Inventory getInv() {
        return inv;
    }

    public void setInv(Inventory inv) {
        this.inv = inv;
    }

    /**
     * Cette méthode peut-être override afin
     * d'être utilisé par les classes filles.
     *
     * Par défaut les cliques dans les inventaires
     * spéciaux sont annulés.
     */
    public boolean onInteract(Player player, PagedInventory pi, InventoryClickEvent event) {
        switch (CustomItem.decodeId(event.getCurrentItem())) {
            case ItemType.FIX:
                event.setCancelled(true);
                return true;
            case ItemType.CLOSE:
                event.setCancelled(true);
                player.closeInventory();
                return true;
            case ItemType.NEXT_PAGE:
                event.setCancelled(true);
                player.openInventory(pi.getCustomInv(pi.next()).getInv());
                return true;
            case ItemType.PREVIOUS_PAGE:
                event.setCancelled(true);
                player.openInventory(pi.getCustomInv(pi.previous()).getInv());
                return true;
            default:
                return false;
        }
    }
}