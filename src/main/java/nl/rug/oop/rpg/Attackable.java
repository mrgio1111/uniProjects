package nl.rug.oop.rpg;

/**
 * Attackable interface that implements the method attack() to its classes.
 */
public interface Attackable {
    void attack(Player player, Enemy enemy);
}
