/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.block;

import fr.creatruth.blocks.block.item.ItemManager;

public class BaseBlock {

    protected ItemManager itemManager() {
        return ItemManager.getInstance();
    }
}
