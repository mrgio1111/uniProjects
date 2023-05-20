package nl.rug.oop.rts.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Unit Class.
 */
@Setter
@Getter
public class Unit implements Jsonfiable{
    private int health;
    private int damage;
    private String unitName;

    /**
     * Constructor for unit class.
     * @param unitName Number of units.
     * @param faction Faction the unit belongs at.
     * @param health Health of the unit.
     * @param damage Damage of the unit.
     */
    public Unit(String unitName, String faction, int health, int damage) {
        this.unitName = unitName;
        this.health = health;
        this.damage = damage;
    }

    /**
     * Implemented interface method.
     * @return Converts the class components to Json format.
     */
    public String stringToJson(){
        StringBuilder json = new StringBuilder();
        json.append("\n\t\t\t\"Units\": ");
        json.append("{\n\t\t\t\t\"UnitName\": " + "\"").append(unitName).append("\"").append(",\n");
        json.append("\n\t\t\t\t\"Health\": ").append(Integer.toString(health)).append(",\n");
        json.append("\n\t\t\t\t\"Damage\": ").append(Integer.toString(damage)).append(",\n");
        json.append("\n\t\t\t}");
        return json.toString();
    }
}
