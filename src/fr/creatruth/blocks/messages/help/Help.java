/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.messages.help;

import fr.creatruth.blocks.command.*;

public class Help {

    public static void initHelp() {
        BlockCmd.loadHelp();
        HeadCmd.loadHelp();
        LineCmd.loadHelp();
        MeterCmd.loadHelp();
        ToggleCmd.loadHelp();
        VisionCmd.loadHelp();
        BlocksCmd.loadHelp();
        BiomeCmd.loadHelp();
    }
}
