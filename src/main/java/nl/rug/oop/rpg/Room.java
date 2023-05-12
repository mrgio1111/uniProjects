package nl.rug.oop.rpg;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for RPG rooms. The rooms contain doors to other rooms, non-attackable NPCS and enemies.
 */
public class Room implements Inspectable, Serializable {
    @Serial
    private static final long serialVersionUID = 2L;
    private String description;
    private List<Door> doors;
    private List<NPC> NPCs;
    private List<Enemy> enemies;

    /**
     * Constructor for Room.
     * @param description this parameter contains the desired description for this room.
     */
    public Room(String description) {
        this.description = description;
        doors = new ArrayList<>();
        NPCs = new ArrayList<>();
        enemies = new ArrayList<>();
    }

    /**
     * method that inspects the room.
     * @return Returns the description of the room together with the number of doors this room has.
     */
    public String inspect() {
        System.out.println("You see: ");
        System.out.println(description + " The room has " + doors.size() + " doors.");
        return null;
    }

    /**
     * Returns the doors present in the 'doors' arrayList together with their index.
     */
    public void doorsList(){
        for (int i = 0; i < doors.size(); i++) {
            System.out.println("(" + i + ") " + doors.get(i).inspect());
        }
    }

    public void addDoor(Door door){
        doors.add(door);
    }

    /**
     * Returns the NPCs present in the 'NPCs' arrayList together with their index.
     */
    public void npcList(){
        for (int i = 0; i < NPCs.size(); i++) {
            System.out.println("(" + i + ") " + NPCs.get(i).inspect());
        }
    }

    public NPC getNPC(int i){
        return NPCs.get(i);
    }

    public Door getDoor(int i){
        return doors.get(i);
    }

    public void getBJDoor(BadJokesDoor BJDoor){
        BJDoor.doorIntro();
    }

    public void addNPC(NPC npc){
        NPCs.add(npc);
    }

    /**
     * Returns the enemies present in the 'enemies' arrayList together with their index.
     */
    public void enemyList(){
        for (int i = 0; i < enemies.size(); i++) {
            System.out.println("(" + i + ") " + enemies.get(i).inspect());
        }
    }

    public Enemy getEnemy(int i){
        return enemies.get(i);
    }

    public void addEnemies(Enemy enemy){
        enemies.add(enemy);
    }

    public void removeEnemies(Enemy enemy){
        enemies.remove(enemy);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<NPC> getNPCs() {
        return NPCs;
    }

    public List<Door> getDoors() {
        return doors;
    }
}
