package nl.rug.oop.rpg;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class for Player.
 */
public class Player implements Serializable {
    @Serial
    private static final long serialVersionUID = 5L;
    private String playerMame;
    private Room playerRoom;
    private int damage;
    private int health;

    /**
     * Constructor for player.
     * @param playerMame Name of the player.
     * @param room Room the player is placed in.
     * @param health Health of the player.
     * @param damage Damage that the player deals.
     */
    public Player(String playerMame, Room room, int health, int damage) {
        this.playerMame = playerMame;
        playerRoom = room;
        this.health = health;
        this.damage = damage;
    }

    public void setPlayerRoom(Room playerRoom) {
        this.playerRoom = playerRoom;
    }

    public Room getPlayerRoom() {
        return playerRoom;
    }

    public void attack(Player player, Enemy enemy){
        int sum = enemy.getHealth() - damage;
        enemy.setHealth(sum);
    }

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