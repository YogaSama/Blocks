/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.block.type;

import fr.creatruth.blocks.manager.block.BaseBlock;
import fr.creatruth.development.item.ItemBuilder;
import fr.creatruth.development.reflection.PackAPI;
import fr.creatruth.development.reflection.ClassAccess;
import fr.creatruth.development.reflection.ConstructorBuilder;

import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Method;

public class FlowerPotBlock extends BaseBlock {

    @Override
    public void onPlace(ItemBuilder builder, BlockPlaceEvent event) {
        super.onPlace(builder, event);

        block.setType(Material.FLOWER_POT);

        ItemStack item;
        switch ((byte) builder.getKey().getData()) {
            case 1:  item = new ItemStack(Material.RED_ROSE);             break;
            case 2:  item = new ItemStack(Material.YELLOW_FLOWER);        break;
            case 3:  item = new ItemStack(Material.SAPLING, 1, (byte) 0); break;
            case 4:  item = new ItemStack(Material.SAPLING, 1, (byte) 1); break;
            case 5:  item = new ItemStack(Material.SAPLING, 1, (byte) 2); break;
            case 6:  item = new ItemStack(Material.SAPLING, 1, (byte) 3); break;
            case 7:  item = new ItemStack(Material.SAPLING, 1, (byte) 4); break;
            case 8:  item = new ItemStack(Material.SAPLING, 1, (byte) 5); break;
            case 9:  item = new ItemStack(Material.RED_MUSHROOM);         break;
            case 10: item = new ItemStack(Material.BROWN_MUSHROOM);       break;
            case 11: item = new ItemStack(Material.DEAD_BUSH);            break;
            default: return;
        }

        try {
            // Accès aux classes.
            ClassAccess<?> cPosition     = ClassAccess.forName(PackAPI.NMS.get("BlockPosition"));
            ClassAccess<?> cCraftWorld   = ClassAccess.forName(PackAPI.OBC.get("CraftWorld"));
            ClassAccess<?> cWorld        = ClassAccess.forName(PackAPI.NMS.get("WorldServer"));
            ClassAccess<?> cPot          = ClassAccess.forName(PackAPI.NMS.get("TileEntityFlowerPot"));
            ClassAccess<?> cCraftItem    = ClassAccess.forName(PackAPI.OBC.get("inventory.CraftItemStack"));
            ClassAccess<?> cItemStack    = ClassAccess.forName(PackAPI.NMS.get("ItemStack"));
            ClassAccess<?> cItem         = ClassAccess.forName(PackAPI.NMS.get("Item"));

            // BlockPosition
            ConstructorBuilder bPosition = cPosition.getConstructor(int.class, int.class, int.class);
            Object position              = bPosition.newInstance(block.getX(), block.getY(), block.getZ());

            // WorldServer
            Object craftWorld            = cCraftWorld.getClazz().cast(block.getWorld());
            Object nms_World             = cCraftWorld.getDeclaredMethod("getHandle").invoke(craftWorld);

            // TimeEntityFlowerPot
            Method getTileEntity         = cWorld.getDeclaredMethod("getTileEntity", cPosition.getClazz()).getMethod();
            Object pot                   = cPot.getClazz().cast(cWorld.invoke(nms_World, getTileEntity, position));

            // ItemStack NMS
            Object nms_ItemStack         = cCraftItem.getMethod("asNMSCopy", ItemStack.class).invoke(null, item);
            Object nms_Item              = cItemStack.getDeclaredMethod("getItem").invoke(nms_ItemStack);
            Object nms_Data              = cItemStack.getDeclaredMethod("getData").invoke(nms_ItemStack);

            // Application des caractéristique de l'item au bloc.
            cPot.getMethod("a", cItem.getClazz(), int.class).invoke(pot, nms_Item, nms_Data);
            cPot.getMethod("update").invoke(null);
            cWorld.getMethod("notify", cPosition.getClazz()).invoke(nms_World, position);
            block.getWorld().refreshChunk(block.getChunk().getX(), block.getChunk().getZ());

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
