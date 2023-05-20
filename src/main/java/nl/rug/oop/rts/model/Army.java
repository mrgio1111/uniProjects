package nl.rug.oop.rts.model;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.listeners.ListenerSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Army class.
 */
@Getter
@Setter
public class Army extends ListenerSupport implements Jsonfiable {
    private int unitNumber;
    private String faction;
    private List<Unit> units;
    private Map map;
    private int health;
    private int damage;
    private final ListenerSupport listenerSupport;

    /**
     * Army constructor.
     * @param faction Army faction.
     * @param map Map model.
     * @param listenerSupport listenerSupport object so the view is notified when an Army is created.
     */
    public Army(String faction, Map map, ListenerSupport listenerSupport) {
        Random rand = new Random();
        this.unitNumber = rand.nextInt(50 - 10) + 10;
        this.faction = faction;
        units = new ArrayList<>();
        this.map = map;
        giveNames(faction, unitNumber);
        health = armyHealth();
        damage = armyDamage();
        this.listenerSupport = listenerSupport;
        listenerSupport.notifyObservers();
    }

    /**
     * Gives names to army units.
     * @param faction Army faction.
     * @param unitNumber Number of units.
     */
    public void giveNames(String faction, int unitNumber){
        if(Objects.equals(faction, "Men")){
            for (int i = 0; i < unitNumber; i++) {
                units.add(new Unit(MenUnitNames.randomName().name(), faction, 100, 13));
            }
        }
        if(Objects.equals(faction, "Elves")){
            for (int i = 0; i < unitNumber; i++) {
                units.add(new Unit(ElvesUnitNames.randomName().name(), faction, 180, 10));
            }
        }
        if(Objects.equals(faction, "Dwarves")){
            for (int i = 0; i < unitNumber; i++) {
                units.add(new Unit(DwarvesUnitNames.randomName().name(), faction, 140, 20));
            }
        }
        if(Objects.equals(faction, "Isengard")){
            for (int i = 0; i < unitNumber; i++) {
                units.add(new Unit(IsengardUnitNames.randomName().name(), faction, 200, 12));
            }
        }
        if(Objects.equals(faction, "Mordor")){
            for (int i = 0; i < unitNumber; i++) {
                units.add(new Unit(MordorUnitNames.randomName().name(), faction, 70, 8));
            }
        }
    }

    /**
     * Sets army health based on number of Units and faction.
     * @return returns army's health.
     */
    public int armyHealth(){
        int healthA = 0;
        healthA += unitNumber * this.getUnits().get(0).getHealth();
        System.out.println("Army health = " + healthA);
        return healthA;
    }

    /**
     * Sets army damage based on number of Units and faction.
     * @return returns army's damage.
     */
    public int armyDamage(){
        int damageA = 0;
        damageA += unitNumber * this.getUnits().get(0).getDamage();
        System.out.println("Army damage = " + damageA);
        return damageA;
    }

    /**
     * Implemented interface method.
     * @return Converts the class components to Json format.
     */
    public String stringToJson(){
        StringBuilder json = new StringBuilder();
        json.append("\n\t\t\"Armies\": ");
        json.append("{\n\t\t\t\"UnitNumber\": ").append(Integer.toString(unitNumber)).append(",\n");
        json.append("\n\t\t\t\"Faction\": " + "\"").append(faction).append("\"").append(",\n");
        json.append("\n\t\t\t\"Health\": ").append(Integer.toString(health)).append(",\n");
        json.append("\n\t\t\t\"Damage\": ").append(Integer.toString(damage)).append(",\n");
        for (Unit unit : units) {
            json.append(unit.stringToJson());
        }
        json.append("\n\t\t}");
        return json.toString();
    }
}
