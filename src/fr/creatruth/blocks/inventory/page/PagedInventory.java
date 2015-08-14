package fr.creatruth.blocks.inventory.page;

/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
import fr.creatruth.blocks.inventory.gui.CustomInv;
import fr.creatruth.blocks.inventory.gui.PreInventory;
import fr.creatruth.blocks.inventory.item.RegisteredItem;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un objet servant à lier des inventaires,
 * il doit être utilisé avec le GUIManager afin de
 * sauvegarder le joueur qui les utilise et sauvegarder
 * la chaine d'inventaire plus simplement.
 */
public class PagedInventory extends PagedList<CustomInv> {

    private int firstEmptySlot = 0;
    private int maxSlot;

    /**
     * Constructeur
     * @param invs Une liste d'inventaires pré-fabriqués.
     */
    public PagedInventory(List<PreInventory> invs) {
        super(build(invs), 1);
        this.maxSlot = list.size() * (list.get(0).getInv().getSize() - 9);
    }

    /**
     * Transformation des inventaires pré-formaté en
     * inventaire fixe.
     */
    private static List<CustomInv> build(List<PreInventory> list) {
        List<CustomInv> res = new ArrayList<>();

        int i = 1;
        for (PreInventory preInv : list) {
            CustomInv ci;

            if (list.size() > 1) {
                preInv.setTitle(Page.toFormat(preInv.getTitle(), i++, list.size()));
                ci = preInv.create();
                initHotBar(ci.getInv());
            }
            else
                ci = preInv.create();

            res.add(ci);
        }

        return res;
    }

    /**
     * Initialise la "HotBar" pour donner des instruments
     * de navigation au joueur.
     *
     * @param inv L'inventaire à initialiser.
     */
    private static void initHotBar(Inventory inv) {
        int next =              inv.getSize() - 4;
        int previous =          inv.getSize() - 6;

        inv.setItem(next,       RegisteredItem.getNext());
        inv.setItem(previous,   RegisteredItem.getPrevious());
    }

    /**
     * Récupère un inventaire suivant la page donnée.
     * @param page La page à récupérer.
     * @return L'inventaire à la page page.
     */
    public CustomInv getCustomInv(int page) {
        return getByPage(page).get(0);
    }

    /**
     * Défini des items de l'index 0 à la taille du tableau
     * sauf si celui ci dépasse la limite maximale de
     * l'inventaire.
     *
     * @param items Tableau d'items à ajouter.
     */
    public void setItems(ItemStack[] items) {
        for (int i = 0; i < items.length && i < maxSlot; i++)
            setItem(i, items[i], false);
    }
    /**
     * Ajoute un item si le premier slot vide trouvé,
     * sur le système actuel, si par exemple le dernier
     * slot vide était le 30, et qu'un item au slot 15 a
     * été retiré, la méthode va considérer le slot 15 comme
     * plein, et attendra de terminer de parcourir la taille
     * maximale de l'inventaire pour de nouveau rajouter un
     * item dans le slot 15.
     *
     * @param item L'item à ajouter à l'inventaire.
     */
    public void addItem(ItemStack item) {
        for (int i = firstEmptySlot; i < maxSlot; i++) {
            if (setItem(i, item, true)) {
                firstEmptySlot = i >= maxSlot ? 0 : ++i;
                return;
            }
        }
    }

    /**
     * Ajoute une item dans l'inventaire dans le slot demandé.
     *
     * @param slot Le numéro du slot de l'item.
     * @param item L'item à ajouter.
     * @return true si l'item a bien été ajouté.
     */
    public boolean setItem(int slot, ItemStack item) {
        return setItem(slot, item, false);
    }

    /**
     * Récupère d'abord la capacité d'un inventaire.
     *
     * Puis fait un produit en croix pour récupérer dans quel
     * inventaire doit être ajouté l'item. Si le slot est incrémenté
     * d'un, c'est pour l'ajuster le domaine de définition de 1 à max.
     * (Et non 0 à max)
     *
     * Ensuite, récupère le slot correspondant à l'inventaire final,
     * en pensant à revenir au bon domaine de définition.
     *
     * On récupère l'inventaire concerné, et on ajoute l'item en fonction
     * de la valeur boolean empty.
     *
     * @param slot Le numéro du slot de l'item.
     * @param item L'item à ajouter.
     * @param empty L'ancien item dans le slot demandé peut-être écrasé ?
     * @return true si l'item a bien été ajouté.
     */
    public boolean setItem(int slot, ItemStack item, boolean empty) {
        int invSize = maxSlot / list.size();
        int invIndex = (int) Math.ceil(((double) (++slot * list.size())) / maxSlot);
        int sSlot = slot - ((invIndex - 1) * invSize) - 1;

        Inventory inv = getCustomInv(invIndex).getInv();
        if (empty && inv.getItem(sSlot) != null)
            return false;

        inv.setItem(sSlot, item);
        return true;
    }
}