package nl.rug.oop.rpg;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class for Door. Door is inspectable and interactable.
 */
public abstract class Door implements Inspectable, Interactable, Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    private String description;

    public Door(String description, Room room) {
        this.description = description;
    }

    public String inspect() {
        return description;
    }

    public void interact(Player player, Room room){
        player.setPlayerRoom(room);
    }

}

