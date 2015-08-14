/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item.type.seeds;


import fr.creatruth.blocks.manager.Materials;
import fr.creatruth.blocks.manager.item.BaseItem;
import fr.creatruth.blocks.manager.item.PickableItem;
import fr.creatruth.blocks.manager.item.SpecialBase;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.inventory.ItemStack;

class SeedItem extends PickableItem implements SpecialBase {

    protected static final byte[] DATA = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};

    protected Material seed;
    protected Material material;

    public SeedItem(ItemStack item, Materials materials) {
        super(item, materials);
    }

    @Override
    public void onPick(InventoryCreativeEvent event) {
        super.onPick(event);

        if (cursor.getType() == seed && block.getType() == material) {
            event.setCursor(getItem(seed, block.getData(), null));
        }
    }

    @Override
    public BaseItem getSpecialBase(byte data) {
        return specialItemBuilder(Material.SOIL, material, data);
    }
}
