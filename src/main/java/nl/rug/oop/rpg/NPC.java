package nl.rug.oop.rpg;

import java.io.Serial;
import java.io.Serializable;

/**
 * Abstract class for NPC. NPC is Inspectable and Interactable. It is also the sueprclass of Enemy class.
 */
public abstract class NPC implements Inspectable, Interactable, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private String description;
    private int health;
    private int damage;

    /**
     * Constructor for NPC.
     * @param description Parameter for the description of the NPC.
     * @param room Parameter that is responsible for placing the NPC in the room that its creator chooses.
     * @param health Parameter that instantiates health to the NPC.
     * @param damage Parameter that instantiates the amount of damage the NPC deals.
     */
    public NPC(String description, Room room, int health, int damage) {
        this.description = description;
        this.health = health;
        this.damage = damage;
    }

    public String inspect(){
        return description;
    }

    public abstract void interact(Player player, Room room);

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

}
