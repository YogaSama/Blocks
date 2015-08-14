package fr.creatruth.blocks.runnable;


import fr.creatruth.blocks.BMain;
import fr.creatruth.blocks.configuration.Config;
import fr.creatruth.blocks.manager.tools.Face;
import fr.creatruth.blocks.player.Perm;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class LineTask implements Runnable {

    private String      taskName;
    private Player      player;
    private Block       origin;
    private Block       previous;
    private Block       current;
    private Material    material;
    private byte        data;
    private BlockFace   dir;
    private int         length;
    private boolean     next;

    public LineTask(String taskName, Player player, Block origin, Face face, int length) {
        this.taskName = taskName;
        this.player = player;
        this.origin = origin;
        this.dir = face == Face._BLOCK ? BMain.getData(player).getLastBlockFace() : face.getBlockFace();
        this.previous = origin;
        this.current = origin.getRelative(dir);
        this.material = origin.getType();
        this.data = origin.getData();
        this.length = --length;
    }

    public void run() {
        if (player == null) {
            TaskManager.cancelTaskByName(taskName);
            return;
        }

        if (0 <= current.getY() && current.getY() < 255) {
            if (Config.isLineBreakOrigin())
                next = origin.getType() == material && setLine();
            else
                next = setLine();

            previous = current;
            current = current.getRelative(dir);
        }
        else
            TaskManager.cancelTaskByName(taskName);

        if (!next || --length <= 0)
            TaskManager.cancelTaskByName(taskName);
    }

    private boolean setLine() {
        try {
            if (current.isEmpty() && Perm.canBuild(player, current.getLocation())) {
                current.setType(material);
                current.setData(data);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
