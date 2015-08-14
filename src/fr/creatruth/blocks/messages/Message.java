/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.messages;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

public enum Message {

    LINE("&e------------------------------------------"),

    HELP_SHOW("Show \"{0}\" command help."), // Affiche l''aide de la commande \"{0}"\".
    HELP_BIOME_WAND("Give you a biomes wand."), // Vous donne l''outil des biomes.
    HELP_BIOME_LIST("Show the biomes list."), // Affiche la liste des biomes.
    HELP_BIOME_SETBIOME("Change the biome of your &ehand tool&f."), // Change le biome de votre &eoutil en main&f.
    HELP_BIOME_SETRADIUS("Change the radius of your &ehand tool&f."), // Change le rayon de votre &eoutil en main&f.
    HELP_BLOCKS_HOWTO("&eHow to switch between blocks ?"), // &eComment switcher entre les blocs ?
    HELP_BLOCKS_ANSWER("&7 You must be in creative mode with block in your hand, looking air and sneak and click (left or right) to change data or type item."), // &7 Vous devez être en mode créatif avec un bloc en main, regarder en direction de l''air, vous accroupir et faire un clique droit ou gauche pour changer le data ou le type de l''item.
    HELP_BLOCK_BASE("Give you a block."), // Vous donne un bloc.
    HELP_BLOCK_OTHER("Give a block to specified player."), // Donne un bloc au joueur spécifié.
    HELP_BLOCK_SPECIAL("Transform your handed block into special format."), // Transforme le bloc de votre main dans un format spécial.
    HELP_BLOCK_LIST("Open an inventory with special blocks."), // Ouvre un inventaire avec des blocs spéciaux.
    HELP_HEAD_OWN("Give your own head."), // Vous donne votre tête.
    HELP_HEAD_OTHER("Give head of specified player."), // Vous donne la tête du joueur spéficié.
    HELP_LINE_DEFAULT("Convert handed item into line builder with default parameters."), // Converti l''item en main en constructeur de ligne avec les paramètres par défaut.
    HELP_LINE_BASE("Convert handed item into line builder."), // Converti l''item en main en constructeur de ligne.
    HELP_LINE_ADVANCED("Convert specified item into line builder."), //Converti l''item spécifié en constructeur de ligne.
    HELP_LINE_OFF("Remove \"/line\" attributes."), // Retire l''attribut \"/line\" de l''item.
    HELP_METER("Left click to set pos1, and right click to set pos2."), // Clique gauche pour définir la pos1, clique droit pour définir la pos2.
    HELP_TOGGLE_LIST("Show your own list."), // Affiche votre liste.
    HELP_TOGGLE_OTHER("Show parameters of speficied player."), // Affiche les paramètres du joueur spécifié.
    HELP_TOGGLE_INFO("Enable / Disable \"Sneak + Right Click\" on blocks to have informations."), // Active / Désactive le clique droit sur les blocs pour avoir des informations.
    HELP_TOGGLE_CHANGE("Enable / Disable \"Sneak + Click\" to switch items."), // Active / Désactive le changement de data / matériaux en accroupi.
    HELP_TOGGLE_MIDDLE("Enable / Disable middle click on block in creative mode."), // Active / Désactive le clique molette du plugin.
    HELP_TOGGLE_BLOCK("Enable / Disable special blocks placement."), // Active / Désactive le placement des blocs spéciaux.
    HELP_TOGGLE_ALL("Enable / Disable all \"toggle\"."), // Active / Désactive les \"toggle\".
    HELP_VISION_AQUA("Enable / Disable aqua vision."), // Active / Désactive la vision aquatique.
    HELP_VISION_NIGHT("Enable / Disable night vision."), // Active / Désactive la vision nocturne.
    HELP_RELOAD("Reload plugin."), // Recharge le plugins.

    COMMAND_BIOME_RECEIVED("You have received the &ebiome tool&7 !"), // Vous avez reçu l''&eoutil à biome&7 !
    COMMAND_BIOME_LIST("Valid biomes :\n{0}"), // Biomes valides :
    COMMAND_BIOME_SETRADIUS("New radius : &3{0}"), // Nouveau rayon : &3{0}
    COMMAND_BIOME_LARGERADIUS("Be careful : You are using a large radius"), // Attention : Vous utilisez un grand rayon !
    COMMAND_BIOME_SETBIOME("New biome : &e{0}"), // Nouveau biome :&e{0}

    COMMAND_BLOCK_SPECIAL("Item changed item into special item !"), // Item changé en item spécial !
    COMMAND_BLOCK_SPECIALSUFFIX(" (&e&oSpecial&7)"), // (&e&oSpécial&7)
    COMMAND_BLOCK_RECEIVED("You have received &5{0}&f. {1}"), // Vous avez reçu le l''item &5{0}&f. {1}
    COMMAND_BLOCK_SEND("You have send &5{0}&f to &6{1}&f."), // Vous avez donné l''item &5{0} à &6{1}&f.

    COMMAND_ERROR_SPECIALNOTAPPLY("This item can''t be convert into special item."), // Cet item ne peut pas être transformé en item spécial.
    COMMAND_ERROR_NOACCESS("You don''t have access to this command !"), //  Vous n''avez pas accès à cette commande !
    COMMAND_ERROR_USE("You can''t use this material / item."), // Vous ne pouvez pas utiliser ce matériau / item.
    COMMAND_ERROR_NOTVALIDMATERIAL("Not valid material !"), // Matériaux non valide !
    COMMAND_ERROR_HELPNOTFOUND("&cNo help found."), // &cPas d''aide trouvée.
    COMMAND_ERROR_PLAYERNOTFOUND("Player &6{0}&c not found."), // Le joueur &6{0}&c n''a pas été trouvé.
    COMMAND_ERROR_STATE("You must add \"&6on&c\" or \"&6off&c\" argument."), // Vous devez ajouter l''argument \"&6on&c\" ou \"&6off&c\".

    COMMAND_COMMON_ENABLED("&aenabled"), // &aactivé
    COMMAND_COMMON_DISABLED("&cdisabled"), // &cdésactivé

    COMMAND_METER_RECEIVE("You have receive a &emeter&7 !"), // Vous avez reçu le &emètre&7.

    COMMAND_BLOCKS_RELOAD("&aPlugin reloaded !"), // &aPlugin rechargé !

    COMMAND_HEAD_YOURHEAD("You''ve received your own head."), // Vous avez reçu votre tête.
    COMMAND_HEAD_HEADRECEIVED("You have received &e{0}&7''s head."), // Vous avez reçu la tête de &e{0}&7.

    COMMAND_VISION_NIGHT("Night vision {0}&7."), // Vision nocturne {0}&7.
    COMMAND_VISION_AQUA("Aqua vision {0}&7."), // Vision aquatique {0}&7.

    COMMAND_LINE_BLACKLISTMAT("This material is blacklisted."), // Ce matériaux est blacklisté.
    COMMAND_LINE_GIVE("{3}", //
            "- Line applied on : &3{0}", // - Line appliqué sur : &3{0}
            "- Size : &e{1}", // - Taille : &e{1}
            "- Direction : &e{2}", // - Direction : &e{2}
            "{3}"), //
    COMMAND_LINE_HANDITEM("Handed item"), // Item en main
    COMMAND_LINE_VALIDFACES("&7Valid directions :", // &7Directions valides :
            "&f{0}"), //
    COMMAND_LINE_OFF("Line removed."), // Ligne supprimée.

    COMMAND_TOGGLE_INFO("Right click on blocks for informations.\n({0}&f)"), // Clique droit sur les blocs pour des informations.\n({0}&f)
    COMMAND_TOGGLE_BLOCK("Block placement by blocks plugin.\n({0}&f)"), // Placement des blocs par le plugin.\n({0}&f)
    COMMAND_TOGGLE_CHANGE("Switch in sneak mode with handed block.\n({0}&f)"), // Changement des items en main avec le mode accroupi.\n({0}&f)
    COMMAND_TOGGLE_MIDDLE("Get a copy of the target block with middle click.\n({0}&f)"), // Avoir une copie du bloc visé avec le clique molette.\n({0}&f)
    COMMAND_TOGGLE_LIST("List of your parameters :"), // Liste de vos paramètres :
    COMMAND_TOGGLE_LISTOTHER("List of {0}''s parameters :"), // Liste des paramètres de {0} :
    COMMAND_TOGGLE_CHANGEDOTHER("Toggle \"{0}\" of {1} changed !"), // Toggle \"{0}\" changé pour {1} !

    EVENT_METER_INFO("&4&l> &7Second position set. (&6{0}&7, &6{1}&7, &6{2}&7)", // &4&l> &7Deuxième position définie. (&6{0}&7, &6{1}&7, &6{2}&7)
            "- &7Distance :&6 {3}", //
            "- &7Dimensions :&6 {4}&7,&6 {5}&7,&6 {6}", //
            "- &7Number of blocks :&6 {7}"), // - &7Nombre de blocs :&6 {7}
    EVENT_METER_FIRSTCLICK("&7First position set. (&6{0}&7, &6{1}&7, &6{2}&7)"), // &4&l> &7Première position définie. (&6{0}&7, &6{1}&7, &6{2}&7)
    EVENT_METER_ERROR("First position must be defined !"), // La première position doit être définie !

    EVENT_CLICK_INFO("ID : &2{0}  &7Data : &2{1}  &7Name : &2{2}", // ID : &2{0}  &7Data : &2{1}  &7Nom : &2{2}
            "&7Coords: &6{3}&7, &6{4}&7, &6{5}"), //
    EVENT_CLICK_SKULL("ID : &2{0}  &7Data : &2{1}  &7Name : &2{2}  &7Pseudo : &6{6}", // "ID : &2{0}  &7Data : &2{1}  &7Nom : &2{2}  &7Pseudo : &6{6}
            "&7Coords : &6{3}&7, &6{4}&7, &6{5}"), //

    EVENT_CLICK_NOTARGET("No target found."); // Pas de cible trouvée.

    private String message;
    private List<String> messages;

    Message(String... messages) {
        this.message = "§4Message not define §c" + (this.name().toLowerCase().replace('_', '.'));
        this.messages = Arrays.asList(messages);
    }

    public String getMessage() {
        return this.message;
    }

    public List<String> getMessages() {
        return this.messages;
    }

    public String getMessage(Object... args) {
        return MessageFormat.format(getMessage(), args);
    }

    public void setMessage(List<String> messages) {
        this.message = ChatColor.translateAlternateColorCodes('&', buildString(messages.toArray(new String[messages.size()])));
    }

    private String buildString(String... strings) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            sb.append(strings[i]);
            if (i < strings.length - 1)
                sb.append("\n§r");
        }
        return sb.toString();
    }

    public void send(CommandSender sender, Type type, Object... args) {
        if (sender != null) {
            sender.sendMessage(MessageFormat.format(getMessage(this, type), args));
        }
    }

    public void sendAlert(CommandSender sender, Object... args) {
        send(sender, Type.ALERT, args);
    }

    public void sendRaw(CommandSender sender, Object... args) {
        send(sender, Type.RAW, args);
    }

    public String getMessage(Message name, Type type) {
        return String.format(type.getPattern(), name.getMessage());
    }

    public enum Type {

        CLASSIC("§4§l> §7%s"),
        ALERT("§4§l> §c%s"),
        RAW("§c%s"),
        BLOCK("§9Blocks §7§l>§r %s");

        private String pattern;

        Type(String pattern) {
            this.pattern = pattern;
        }

        protected String getPattern() {
            return this.pattern;
        }
    }
}