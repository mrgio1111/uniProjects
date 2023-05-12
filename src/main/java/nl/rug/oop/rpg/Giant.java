package nl.rug.oop.rpg;

/**
 * Class for Giant. Extends abstract class NPC.
 */
public class Giant extends NPC {
    /**
     * Constructor for giant.
     * @param description Description of the giant.
     * @param room Room that the giant is placed in.
     * @param health The health of the giant.
     * @param damage The damage that the giant deals in case he attacks.
     */
    public Giant(String description, Room room, int health, int damage) {
        super(description, room, health, damage);
    }

    @Override
    public void interact(Player player, Room room) {
        System.out.println("\"Greetings! Welcome to Hogwarts, the School of Witchcraft and Wizadry. \"");
        friendlyTap();
    }

    public void friendlyTap(){
        System.out.println("He taps you on the shoulder in a friendly manner.");
        System.out.println("*you fall*");
    }
}
