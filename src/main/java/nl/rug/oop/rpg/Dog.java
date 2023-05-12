package nl.rug.oop.rpg;

/**
 * Class for Dog. Extends abstract class NPC.
 */
public class Dog extends NPC{
    /**
     * Constructor for dog.
     * @param description Description of the dog.
     * @param room The room the dog is placed in.
     * @param health The health of the dog.
     * @param damage The damage that the dog does in case it attacks.
     */
    public Dog(String description, Room room, int health, int damage) {
        super(description, room, health, damage);
    }

    /**
     * Method that is used when the user interacts with this object.
     * @param player The player of the game.
     * @param room The room that this object is in.
     */
    @Override
    public void interact(Player player, Room room) {
        System.out.println("\"Zzzzzz\"");
        farts();
    }

    /**
     * Behaviour of dog.
     */
    public void farts() {
        System.out.println("*farts*");
    }
}
