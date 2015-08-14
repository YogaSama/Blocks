/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.inventory.page;

/**
 * Représente des pages quelconques.
 * L'objet current désigne la page actuelle.
 * L'objet limit désigne le nombre de page maximum qu'il y a.
 */
public class Page {

    public static final String FORMAT = "%1$d / %2$d";
    public static final String STRING_FORMAT = "%s §0▶ §f%d§7/§f%d";

    protected int current;
    protected int limit;

    /**
     * Constructeurs
     */
    public Page() {
        this(1);
    }

    public Page(int limit) {
        this.limit = limit;
        setCurrent(1);
    }

    /**
     * @return La page actuelle.
     */
    public int getCurrent() {
        return current;
    }

    /**
     * @return Le nombre de pages maximal.
     */
    public int getLimit() {
        return limit;
    }

    /**
     * Redéfini la page actuelle.
     *
     * @param current La nouvelle page.
     * @return this
     */
    public Page setCurrent(int current) {
        if (current > limit)
            this.current = limit;
        else if (current < 1)
            this.current = 1;
        else
            this.current = current;

        return this;
    }

    /**
     * Redéfini le nombre limite de pages.
     *
     * @param limit La nouvelle limite.
     * @return this
     */
    public Page setLimit(int limit) {
        this.limit = limit;
        setCurrent(current);
        return this;
    }

    /**
     * @return Tant que la page actuelle est en dessous de la page limite renvois true.
     */
    public boolean hasNext() {
        return current < limit;
    }

    /**
     * @return La page actuelle après l'incrémentation ou la première page
     * si la première page est atteinte.
     */
    public int next() {
        return ++current > limit ? current = 1 : current;
    }

    public boolean hasPrevious() {
        return current > 1;
    }

    public int previous() {
        return --current < 1 ? current = limit : current;
    }

    public String toFormat(String s) {
        return toFormat(s, current, limit);
    }

    public static String toFormat(String s, int current, int limit) {
        return String.format(STRING_FORMAT, s, current, limit);
    }

    @Override
    public String toString() {
        return String.format(FORMAT, current, limit);
    }
}