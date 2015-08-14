/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.inventory.page;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente une liste sous forme de pages.
 * @param <T> Récupére la généricité de l'objet List.
 */
public class PagedList<T> extends Page {

    public static final int DEFAULT_SIZE = 9;

    protected List<T> list;
    protected int size;

    /**
     * Constructeurs
     * @param list La liste à découper sous forme de pages.
     */
    public PagedList(List<T> list) {
        this(list, DEFAULT_SIZE);
    }

    public PagedList(List<T> list, int size) {
        super((int) (((double) list.size()) / size));
        this.list = list;
        setSize(size);
    }

    public List<T> getList() {
        return list;
    }

    public int getSize() {
        return size;
    }

    /**
     * Si la taille de la liste vaut 1, alors inutile de récupérer
     * une partie de la liste.
     */
    public final List<T> getByPage() {
        return getByPage(current);
    }

    /**
     * Pas safe si la page est invalide.
     *
     * @param page La page à utiliser.
     * @return Une partie de la liste en fonction de la page actuelle.
     */
    public final List<T> getByPage(int page) {
        List<T> res = new ArrayList<>();

        if (size > 1) {
            for (int i = (page - 1) * size; i < list.size() && i < page * size; i++) {
                res.add(list.get(i));
            }
        }
        else
            res.add(list.get(page - 1));

        return res;
    }

    /**
     * Redimensionne le nombre d'élèments par page.
     * La taille ne peut pas être inférieure à 1.
     *
     * @param size La nouvelle taille.
     */
    public void setSize(int size) {
        if (size < 1)
            this.size = 1;
        else
            this.size = size;
    }
}