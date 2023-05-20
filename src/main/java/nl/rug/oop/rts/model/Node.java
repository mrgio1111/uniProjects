package nl.rug.oop.rts.model;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.listeners.ListenerSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;



/**
 * Class that contains all the information about nodes.
 */
@Getter
@Setter
public class Node extends ListenerSupport implements Jsonfiable {
    /**
     * The countAuto variable auto increments the id of every node created.
     */
    public static final AtomicInteger COUNT = new AtomicInteger(0);
    private int id;
    private String name;
    private List<Edge> edges;
    private int x;
    private int y;
    private int width;
    private int height;
    private final ListenerSupport listenerSupport;
    private List<Army> armies;
    private List<Event> events;

    /**
     * Constructor for node.
     * @param name name of the node.
     * @param x x dimension in the plane.
     * @param y y dimension in the plane.
     * @param width Width of node rectangle in the graph.
     * @param height Height of node rectangle in the graph.
     * @param listenerSupport Listens to any changes in the node class.
     */
    public Node(String name, int x, int y, int width, int height, ListenerSupport listenerSupport){
        this.name = name;
        id = COUNT.incrementAndGet();
        this.x = x;
        this.width = width;
        this.y = y;
        this.height = height;
        edges = new ArrayList<>();
        armies = new ArrayList<>();
        events = new ArrayList<>();
        this.listenerSupport = listenerSupport;
    }

    public Node(String name, ListenerSupport listenerSupport){
        this(name, 400, 400, 40, 40, listenerSupport);
        listenerSupport.notifyObservers();
    }

    public void addEvent(Event event){
        events.add(event);
        listenerSupport.notifyObservers();
    }

    /**
     * Prints events that take effect on a node.
     * @param j Event index from event array.
     * @return Empty string return.
     */
    public String printEvents(int j){
        for(int i = j; i < events.size(); i++){
            return events.get(i).getName();
        }
        return "";
    }

    public void addArmy(Army army){
        armies.add(army);
        listenerSupport.notifyObservers();
    }

    public int invisibleRectangleCoordsX(){
        return x+width;
    }

    public int invisibleRectangleCoordsY(){
        return y+height;
    }

    /**
     * Adds Edge to the node.
     * @param edge The node that the edge is added to.
     */
    public void addEdgeToNode(Edge edge) {
        edges.add(edge);
        listenerSupport.notifyObservers();
    }

    public void removeEdges(){
        edges.removeAll(edges);
        listenerSupport.notifyObservers();
    }

    public void removeArmies(){
        armies.removeAll(armies);
        listenerSupport.notifyObservers();
    }

    public boolean isInBounds(int pointX, int pointY) {
        listenerSupport.notifyObservers();
        return pointX >= x && pointX <= x+width && pointY >= y && pointY <= y + height;
    }

    /**
     * Updates nodes position regarding its x, y dimensions.
     * @param x x dimension in the plane.
     * @param y y dimension in the plane.
     */
    public void updatePosition(int x, int y){
        this.x = x;
        this.y = y;
        listenerSupport.notifyObservers();
    }

    public int getCenterX(){
        listenerSupport.notifyObservers();
        return x + width/2;
    }

    public int getCenterY(){
        listenerSupport.notifyObservers();
        return y + height/2;
    }

    /**
     * Implemented interface method.
     * @return Converts the class components to Json format.
     */
    public String stringToJson(){
        StringBuilder json = new StringBuilder();
        json.append("\n\t\"Nodes\": ");
        json.append("{\n\t\t\"Name\": " + "\"").append(name).append("\"").append(",\n");
        json.append("\n\t\t\"ID\": ").append(Integer.toString(id)).append(",\n");
        for (Army army : armies) {
            json.append(army.stringToJson());
        }
        for (Event event : events) {
            json.append(event.stringToJson());
        }
        json.append("\n\t}");
        return json.toString();
    }
}
