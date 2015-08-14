/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.inventory.gui;

import org.bukkit.Bukkit;

/**
 * Pré-crée des inventaires avec leur nombre de rangées et
 * leur titre provisoire.
 */
public class PreInventory {

    private int rows;
    private String title;

    public PreInventory(int rows, String title) {
        setRows(rows);
        setTitle(title);
    }

    public int getRows() {
        return rows;
    }

    public String getTitle() {
        return title;
    }

    public void setRows(int rows) {
        if (rows < 1)
            this.rows = 1;
        else if (rows > 6)
            this.rows = 6;
        else
            this.rows = rows;
    }

    public void setTitle(String title) {
        if (title.length() < 33)
            this.title = title;
        else {
            String res = title.substring(0, 29);
            if (res.lastIndexOf("§") == res.length() - 1) {
                res = res.substring(0, 28);
            }
            this.title = res + "...";
        }
    }

    /**
     * Crée un inventaire en utilisant les paramètres donnés.
     * @return Un inventaire customisé.
     */
    public final CustomInv create() {
        return new CustomInv(Bukkit.createInventory(null, 9 * this.rows, title));
    }
}