package nl.rug.oop.rpg;

/**
 * Class for Wizard. Extends abstract class NPC.
 */
public class Wizard extends NPC{
    public Wizard(String description, Room room, int health, int damage) {
        super(description, room, health, damage);
    }

    @Override
    public void interact(Player player, Room room) {
        System.out.println(" \"Hmm, good evening young boy.\"");
        puff();
    }

    public void puff(){
        System.out.println("*puffs his pipe*");
    }
}
