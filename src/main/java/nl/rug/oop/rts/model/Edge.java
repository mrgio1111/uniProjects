package nl.rug.oop.rts.model;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.listeners.ListenerSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class for creating edges that connect nodes.
 */
@Getter
@Setter
public class Edge extends ListenerSupport implements Jsonfiable{
    /**
     * The variable COUNT auto increments the id of every newly created edge.
     */
    public static final AtomicInteger COUNT = new AtomicInteger(0);
    private int id;
    private String name;
    private Node nodeA;
    private Node nodeB;
    private final ListenerSupport listenerSupport;
    private int x1;
    private int x2;
    private int y1;
    private int y2;
    private InvisibleRect rect;
    private List<Army> armies;
    private List<Event> events;

    /**
     * Constructor for edge.
     * @param name Name of the edge.
     * @param nodeOne First node that the edge connects.
     * @param nodeTwo Second node that the edge connects.
     * @param listenerSupport Listener that listens to any changes in the Edge class.
     */
    public Edge(String name, Node nodeOne, Node nodeTwo, ListenerSupport listenerSupport){
        this.name = name;
        nodeA = nodeOne;
        nodeB = nodeTwo;
        id = COUNT.incrementAndGet();
        rect = new InvisibleRect(nodeOne, nodeTwo, listenerSupport);
        armies = new ArrayList<>();
        events = new ArrayList<>();
        this.listenerSupport = listenerSupport;
        // connects 2 nodes
    }

    public int initEdgeX1(Edge edge){
        x1 = edge.getNodeA().getCenterX();
        return x1;
    }

    public int initEdgeY1(Edge edge){
        y1 = edge.getNodeA().getCenterY();
        return y1;
    }

    public int initEdgeX2(Edge edge){
        x2 = edge.getNodeB().getCenterX();
        return x2;
    }

    public int initEdgeY2(Edge edge){
        y2 = edge.getNodeB().getCenterY();
        return y2;
    }

    /**
     * Implemented interface method.
     * @return Converts the class components to Json format.
     */
    public String stringToJson(){
        StringBuilder json = new StringBuilder();
        json.append("\n\t\"Edges\": ");
        json.append("{\n\t\t\"ID\": ").append(Integer.toString(id)).append(",\n");
        json.append("\n\t\t\"Name\": " + "\"").append(name).append("\"").append(",\n");
        json.append("\n\t\t\"NodeA\": " + "\"").append(nodeA.getName()).append("\"").append(",\n");
        json.append("\n\t\t\"NodeB\": ").append(nodeB.getName()).append(",\n");
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



