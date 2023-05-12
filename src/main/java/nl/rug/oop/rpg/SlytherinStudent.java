package nl.rug.oop.rpg;

/**
 * Class for SlytherinStudent. Extends abstract class NPC.
 */
public class SlytherinStudent extends NPC {
    public SlytherinStudent(String description, Room room, int health, int damage) {
        super(description, room, health, damage);
    }

    @Override
    public void interact(Player player, Room room) {
        System.out.println("\"Look who it is. Get away from us. Long live the Slytherin.\"");
        hiss();
    }

    public void hiss(){
        System.out.println("*hisses like a snake*");
        System.out.println("You roast him beautifully: \"If that was a mating call I'll pass.\"");
    }
}
