/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * TEST CLASS
 *
 * @author Yoga_Sama
 */
package fr.creatruth.development;

import fr.creatruth.blocks.command.handle.ACommand;
import fr.creatruth.blocks.player.Perm;
import org.bukkit.Material;
import org.bukkit.entity.LeashHitch;
import org.bukkit.entity.Player;

public class DevCmd extends ACommand {

    @Override
    public void execute() {
        if (sender instanceof Player) {
            Player dev = (Player) sender;
            if (Perm.isDev(dev)) {
                dev.getLocation().getBlock().setType(Material.FENCE);
                LeashHitch lh = dev.getWorld().spawn(dev.getLocation(), LeashHitch.class);
                dev.getLocation().getBlock().setType(Material.AIR);
            }
        }
    }
  ;
    @Override
    public String name() {
        return "dev";
    }
}
