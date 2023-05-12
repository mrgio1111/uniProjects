package nl.rug.oop.rpg;

/**
 * Abstract class Mom. Extends abstract class NPC.
 */
public class Mom extends NPC{
    public Mom(String description, Room room, int health, int damage) {
        super(description, room, health, damage);
    }

    @Override
    public void interact(Player player, Room room) {
        System.out.println("\"Hello child! Come eat your meal it's getting cold! \"");
        momStuff();
    }

    public void momStuff(){
        System.out.println("Your mom fixes some creases in your clothes and gives you a kiss on the cheek");
    }
}
