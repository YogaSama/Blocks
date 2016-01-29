/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * TEST CLASS
 *
 * @author Yoga_Sama
 */
package fr.creatruth.development;

import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.command.handle.ACommand;
import fr.creatruth.blocks.player.Perm;
import fr.creatruth.blocks.utils.DataUtils;
import fr.creatruth.globalapi.floatingtext.FloatingText;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class DevCmd extends ACommand {

    private Player dev;
    private Material[] mats;
    private int i = 1;

    @Override
    public void execute() {
        if (sender instanceof Player) {
            BMain.log("Dev command");
            dev = (Player) sender;
            i = 0;
            if (Perm.isDev(dev)) {
                mats = Material.values();sendAllMats(0, 100,  0, -33);
                sendAllMats(0, 100, 10, -33);
                sendAllMats(0, 100,  0,   3);
                sendAllMats(0, 100, 10,   3);
            }
        }
    }

    public void sendAllMats(int mnx, int mxx, int ry, int az) {
        for (int x = mnx; x < mxx; x += 2) {

            Material mat = mats[i++];
            if (!mat.isBlock()) continue;

            byte data = 0;
            for (int z = 0; z < 16 * 2; z += 2) {
                Location loc = dev.getLocation().add(x, ry,  az + z);

                if (data == 0) {
                    FloatingText text = new FloatingText("§a§l" + mat.name());
                    text.send(dev, loc.clone().add(0, 1.5, 0));
                }

                try {
                    if (DataUtils.exist(mat, data)) dev.sendBlockChange(loc, mat, data);
                } catch (Exception e) {}

                data++;
            }
        }
    }

    @Override
    public String name() {
        return "dev";
    }
}
