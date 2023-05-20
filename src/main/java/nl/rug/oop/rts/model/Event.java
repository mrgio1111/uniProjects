package nl.rug.oop.rts.model;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.listeners.ListenerSupport;
import java.util.Objects;

/**
 * Class for Event.
 */
@Getter
@Setter
public class Event extends ListenerSupport implements Jsonfiable {
    private String name;
    private Node node;
    private Map map;
    private boolean sauron;
    private final ListenerSupport listenerSupport;

    /**
     * Constructor for event class.
     * @param name Name of event.
     * @param node Node that the event is added.
     * @param map Map model.
     * @param listenerSupport listenerSupport object.
     */
    public Event(String name, Node node, Map map, ListenerSupport listenerSupport){
        this.name = name;
        this.node = node;
        this.map = map;
        this.listenerSupport = listenerSupport;
        listenerSupport.notifyObservers();
    }

    /**
     * This method goes through all events and applies the event to the required army faction.
     * @param nazgul 1st event. Reduces health of troops by half.
     * @param gandalfdamage 2nd event. Increases troops damage.
     * @param node Node affected by event.
     */
    public void whichArmy(boolean nazgul, int gandalfdamage, Node node){
        for(Army army : node.getArmies()){
            int newHealth = 0;
            int newDamage = 0;
            nazgulInit(army, nazgul, newHealth);
            gandalfInit(army, gandalfdamage, newDamage);
        }
    }

    /**
     * Nazgul event.
     * @param army Army affected.
     * @param nazgul Boolean variable that is true when this event is applied.
     * @param newHealth New Health of troops after event application.
     */
    public void nazgulInit(Army army, boolean nazgul, int newHealth){
        if(nazgul){
            if(Objects.equals(army.getFaction(), "Men")){
                System.out.println("Nazguls arrived!");
                for(Unit unit : army.getUnits()){
                    unit.setHealth(unit.getHealth()/2);
                    newHealth += unit.getHealth();
                }
                System.out.println("Mens health is down by half!");
                System.out.println("New Army health: " + newHealth);
            }
            if(Objects.equals(army.getFaction(), "Elves")){
                System.out.println("Nazguls arrived!");
                for(Unit unit : army.getUnits()){
                    unit.setHealth(unit.getHealth()/2);
                    newHealth += unit.getHealth();
                }
                System.out.println("Elves health is down by half!");
                System.out.println("New Army health: " + newHealth);
            }
            if(Objects.equals(army.getFaction(), "Dwarves")){
                System.out.println("Nazguls arrived!");
                for(Unit unit : army.getUnits()){
                    unit.setHealth(unit.getHealth()/2);
                    newHealth += unit.getHealth();
                }
                System.out.println("Dwarves health is down by half!");
                System.out.println("New Army health: " + newHealth);
            }
        }
    }

    /**
     * Gandalf event.
     * @param army Armiy effected
     * @param gandalfdamage The actual damage increase. (20 points).
     * @param newDamage The new damage after the application of the event.
     */
    public void gandalfInit(Army army, int gandalfdamage, int newDamage){
        if(gandalfdamage > 0){
            if(Objects.equals(army.getFaction(), "Men")){
                System.out.println("Gandalf arrived");
                for(Unit unit : army.getUnits()){
                    unit.setDamage(unit.getDamage()+gandalfdamage);
                    newDamage += unit.getDamage();
                }
                System.out.println("Gandalf increased Mens damage");
                System.out.println("New Army damage: " + newDamage);
            }
            if(Objects.equals(army.getFaction(), "Elves")){
                System.out.println("Gandalf arrived");
                for(Unit unit : army.getUnits()){
                    unit.setDamage(unit.getDamage()+gandalfdamage);
                    newDamage += unit.getDamage();
                }
                System.out.println("Gandalf increased Elves damage");
                System.out.println("New Army damage: " + newDamage);
            }
            if(Objects.equals(army.getFaction(), "Dwarves")){
                System.out.println("Gandalf arrived");
                for(Unit unit : army.getUnits()){
                    unit.setDamage(unit.getDamage()+gandalfdamage);
                    newDamage += unit.getDamage();
                }
                System.out.println("Gandalf increased Dwarves damage");
                System.out.println("New Army damage: " + newDamage);
            }
        }
    }

    /**
     * Implemented interface method.
     * @return Converts the class components to Json format.
     */
    public String stringToJson(){
        return "\n\t\t\"Events\": " +
                "{\n\t\t\t\"Name\": " + "\"" + name + "\"" + ",\n" +
                "\n\t\t\t\"Node\": " + "\"" + node.getName() + "\"" + ",\n" +
                "\n\t\t}";
    }
}
