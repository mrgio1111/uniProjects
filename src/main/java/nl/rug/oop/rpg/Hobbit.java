package nl.rug.oop.rpg;

/**
 * Class for Hobbit. Extends abstract class for NPC.
 */
public class Hobbit extends NPC{
    public Hobbit(String description, Room room, int health, int damage) {
        super(description, room, health, damage);
    }

    @Override
    public void interact(Player player, Room room) {
        System.out.println("\"What is a human doing in Shire? We don't want strangers!\"");
        unfriendly();;
    }

    public void unfriendly(){
        System.out.println("Goes back into his house closing door loudly in an unsociable, almost angry manner");
        System.out.println("*Shuts curtains closed*");
    }
}
