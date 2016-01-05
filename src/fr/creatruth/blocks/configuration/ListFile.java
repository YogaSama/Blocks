package fr.creatruth.blocks.configuration;

import fr.creatruth.development.item.ItemManager;

public class ListFile extends AConfigFile {

    /**
     * Constructeur.
     */
    public ListFile() {
        super("list.yml", false);
    }

    @Override
    public void loadContent() {
        for (String listName : getFileConfiguration().getKeys(false)) {
            ItemManager.getInstance().load(getFileConfiguration().getStringList(listName));
        }
    }
}
