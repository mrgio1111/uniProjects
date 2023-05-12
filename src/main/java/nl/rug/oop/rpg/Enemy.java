package nl.rug.oop.rpg;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class for Enemy. It's parent class is NPC.
 */
public class Enemy extends NPC implements Attackable, Serializable {
    @Serial
    private static final long serialVersionUID = 4L;

    public Enemy(String description, Room room, int health, int damage) {
        super(description, room, health, damage);
    }

    @Override
    public void interact(Player player, Room room) {
    }

    public int getHealth() {
        return super.getHealth();
    }

    public int getDamage() {
        return super.getDamage();
    }

    public void setHealth(int health) {
        super.setHealth(health);
    }

    public void setDamage(int damage) {
        super.setDamage(damage);
    }

    public void attack(Player player, Enemy enemy){
        int sum = player.getHealth() - super.getDamage();
        player.setHealth(sum);
    }
}
