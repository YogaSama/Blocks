/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.inventory.gui;

import fr.creatruth.blocks.inventory.page.PagedInventory;
import fr.creatruth.blocks.listener.AListener;
import fr.creatruth.blocks.runnable.TaskManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Gère les évènements liés aux inventaires.
 */
public class GUIListener extends AListener {

    /**
     * Lorsque le joueur ferme un inventaire, on le retire
     * par précaution de la liste des joueurs ayant un
     * inventaire ouvert.
     *
     * @param event L'évènement qui se déclenche au moment de
     *              fermer un inventaire.
     */
    @EventHandler
    public void onClose(final InventoryCloseEvent event) {
        TaskManager.runLater(new Runnable() {
            @Override
            public void run() {
                HumanEntity p = event.getPlayer();

                if (p.getOpenInventory().getType() != InventoryType.CHEST)
                    GUIManager.getInstance().remove(p.getUniqueId());
            }
        }, 2L);
    }

    /**
     * Lorsqu'un joueur clique sur ou en dehors d'un inventaire.
     * On vérifie si le joueur est dans la liste du GUIManager,
     * si oui on test si il clique sur un item de l'inventaire.
     * Puis on lance l'évènement onInteract() de l'inventaire
     * custom qui prend le relai.
     *
     * @param event L'évènmement qui se déclenche au moment de
     *              cliquer dans un inventaire.
     */
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        GUIManager gm = GUIManager.getInstance();
        UUID uuid = event.getWhoClicked().getUniqueId();

        if (gm.contain(uuid)) {
            ItemStack current = event.getCurrentItem();

            if (current != null) {
                Material material = current.getType();

                if (material != Material.AIR) {
                    Player player = Bukkit.getPlayer(uuid);
                    PagedInventory pi = gm.get(uuid);
                    CustomInv ci = pi.getCustomInv(pi.getCurrent());
                    ci.onInteract(player, pi, event);
                }
            }
        }
    }
}
