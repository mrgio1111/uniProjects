package nl.rug.oop.rpg;

/**
 * Interactable interface that implements the method interact() to its classes.
 */
public interface Interactable {
    void interact(Player player, Room room);
}
