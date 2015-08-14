/**
 * Blocks source,
 * you can modify sources for personal usage.
 *
 * @author Yoga_Sama
 */
package fr.creatruth.blocks.manager.item;

/**
 * A utiliser pour les blocs impossible à récupérer en main.
 */
public interface SpecialBase {

    /**
     * @return Permet de donner l'item qui correspond à un SpecialItem type SPECIAL.
     * @param data Le data voulu, il sera normalement réajusté pour rester valide.
     */
    BaseItem getSpecialBase(byte data);
}
