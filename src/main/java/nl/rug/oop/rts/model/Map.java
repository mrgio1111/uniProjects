package nl.rug.oop.rts.model;

import lombok.Getter;
import lombok.Setter;
import nl.rug.oop.rts.listeners.ListenerSupport;
import nl.rug.oop.rts.listeners.UpdateListener;
import nl.rug.oop.rts.view.MyFrame;
import javax.swing.*;
import java.util.*;

/**
 * Class for the model of the Graph.
 */
@Getter
@Setter
public class Map implements Jsonfiable{
    private List<Node> nodes;
    private List<Edge> edges;
    private int x;
    private int y;
    private int nodeIndex;
    private int pointX;
    private int pointY;
    private ListenerSupport listenerSupport;
    private Node nodeOne;
    private JButton itemRemoveNode;
    private JButton itemAddEdge;
    private JButton itemRemoveEdge;
    private JButton itemAddArmy;
    private JButton itemRemoveArmy;
    private JButton itemBeginSimulation;
    private JButton itemRemoveEvent;
    private JButton itemAddEvent;

    private boolean edgeSelected;
    private boolean edgeInfo;
    private boolean nodeSelected;
    private boolean edgeDrawn = false;
    private Edge edge;
    private int index;
    private Edge anEdge;
    private int latestEdgeIndex;
    private MyFrame frame;
    private String faction;
    private int armyGatheringX;
    private int armyGatheringY;
    private boolean fight;
    private boolean nazgul;
    private int gandalfdamage = 0;
    private boolean ringDestroyed;
    private int healthArmy;
    private int damageArmy;
    private List<Node> defeatedNodes;
    private List<Edge> defeatedEdges;
    private List<Node> frodoDefeatedNodes;

    /**
     * Constructor for map.
     * @param listenerSupport Listens to changes being done in the Map class and notifies the observers.
     */
    public Map(ListenerSupport listenerSupport){
        this.listenerSupport = listenerSupport;
        nodes = new ArrayList<>();
        edges = new ArrayList<>();
        defeatedEdges = new ArrayList<>();
        defeatedNodes = new ArrayList<>();
        frodoDefeatedNodes = new ArrayList<>();
        nodeIndex = -1;
    }

    /**
     * Simulation method.
     */
    public void simulate() {
        if(ringDestroyed){
            removeDefeated();
            System.out.println("Frodo Destroyed the ring! All Sauron's armies are destroyed.");
        }
        if(nodes != null) {
            for (Node node : nodes) {
                for (Edge edge : node.getEdges()) {
                    Random rand = new Random();
                    events(rand.nextInt(8), node);
                    edge.getNodeA().updatePosition(edge.initEdgeX2(edge) - 20, edge.initEdgeY2(edge));
                    if (ringDestroyed) {
                        frodoMadeIt();
                    }
                    battle(edge);
                    listenerSupport.notifyObservers();
                }
                if (ringDestroyed) {
                    node.removeEdges();
                }
            }
        }
        if(ringDestroyed){
            edges.removeAll(edges);
        }
        removeDefeated();
    }

    /**
     * Battle method.
     * @param edge Edge that connects the nodes that battle.
     */
    public void battle(Edge edge) {
        int nodeHealthA = 0;
        int nodeDamageA = 0;
        for(Army army : edge.getNodeA().getArmies()){
            nodeHealthA += army.getHealth();
            nodeDamageA += army.getDamage();
        }
        int nodeHealthB= 0;
        int nodeDamageB = 0;
        for(Army army : edge.getNodeB().getArmies()){
            nodeHealthB += army.getHealth();
            nodeDamageB += army.getDamage();
        }
        while(nodeHealthA > 0 && nodeHealthB > 0){
            nodeHealthA -= nodeDamageB;
            nodeHealthB -= nodeDamageA;
        }
        if(nodeHealthA > nodeHealthB){
            edge.getNodeB().removeArmies();
            defeatedNodes.add(edge.getNodeB());
            defeatedEdges.add(edge);
        } else {
            edge.getNodeA().removeArmies();
            defeatedNodes.add(edge.getNodeA());
            defeatedEdges.add(edge);
        }
    }

    /**
     * Removes defeated nodes from the map.
     */
    public void removeDefeated(){
        for(Node node : defeatedNodes){
            nodes.remove(node);
        }
        for(Edge edge : defeatedEdges){
            edges.remove(edge);
        }
        listenerSupport.notifyObservers();
    }

    /**
     * Frodo destroyed the ring. Nodes with Sauron's armies are removed from the graph.
     */
    public void frodoMadeIt(){
        if(ringDestroyed){
            for(Node node : frodoDefeatedNodes){
                nodes.remove(node);
            }
            System.out.println("Frodo destroyed the Ring! Sauron's armies are destroyed.");
        }
        listenerSupport.notifyObservers();
    }

    /**
     * Method that activates events.
     * @param eventIndex index of event to be activated.
     * @param node Node affected.
     */
    public void events(int eventIndex, Node node){
        if(eventIndex == 0 ){
            if(armiesCheck(node, eventIndex)) {
                Event event = new Event("Nazgul", node, this, listenerSupport);
                node.addEvent(event);
                event.whichArmy(true, 0,  node);
            }
        } else if (eventIndex == 1) {
            if(armiesCheck(node, eventIndex)) {
                Event event = new Event("Gandalf", node, this, listenerSupport);
                node.addEvent(event);
                event.whichArmy(false, 20, node);
            }
        } else if (eventIndex == 7) {
            Event event = new Event("Ring", node, this, listenerSupport);
            node.addEvent(event);
            ringDestroyed = true;
        }
        listenerSupport.notifyObservers();
    }

    /**
     * Checks for the appropriate army to add an event.
     * @param node Node affected by event
     * @param event Event index.
     * @return returns true or false.
     */
    public boolean armiesCheck(Node node, int event){
        for(Army army : node.getArmies()){
            if(Objects.equals(army.getFaction(), "Men") && (event == 0 || event == 1)){
                return true;
            }
            if(Objects.equals(army.getFaction(), "Dwarves") && (event == 0 || event == 1)){
                return true;
            }
            if(Objects.equals(army.getFaction(), "Elves") && (event == 0 || event == 1)){
                return true;
            }
        }
        return false;
    }

    /**
     * Method that removes event.
     * @param eventPicked event picked by name.
     */
    public void eventToBeRemoved(String eventPicked){
        if (Objects.equals(eventPicked, "Nazgul")) {
            reverseEventNazgul();
        }
        if (Objects.equals(eventPicked, "Gandalf")) {
            reverseEventGandalf();
        }
    }

    /**
     * Method that when the Nazgul event is removed, it reverses the changes back to normal.
     */
    public void reverseEventNazgul(){
        int newHealth = 0;
        for(Army army : nodeOne.getArmies()){
            for(Unit unit : army.getUnits()){
                unit.setHealth(unit.getHealth()*2);
                newHealth += unit.getHealth();
            }
            System.out.println("Reversed army health: " + newHealth);
        }
    }

    /**
     * Method that when the Gandalf event is removed, it reverses the changes back to normal.
     */
    public void reverseEventGandalf(){
        for(Army army : nodeOne.getArmies()){
            for(Unit unit : army.getUnits()){
                unit.setDamage(unit.getDamage()-20);
            }
        }
    }

    /**
     * Removes a node from the map.
     */
    public void removeNode(){
        for (Edge edge : nodeOne.getEdges()) {
            if (this.getEdges().contains(edge)){
                edges.remove(edge);
            }
        }
        nodeOne.removeEdges();
        nodes.remove(nodeOne);
        listenerSupport.notifyObservers();
    }

    public void addNode(String name){
        nodes.add(new Node(name, listenerSupport));
    }

    public void removeArmy(){
        nodeOne.removeArmies();
    }

    public void renameNode(String name){
        nodeOne.setName(name);
        listenerSupport.notifyObservers();
    }

    public void renameEdge(String name){
        anEdge.setName(name);
        listenerSupport.notifyObservers();
    }

    /**
     * Adds edge to the node.
     * @param name Name of the edge.
     * @param nodeA First node that the edge connects with.
     * @param nodeB Second node that the edge connects with.
     */
    public void addEdge(String name, Node nodeA, Node nodeB){
        Edge edge = new Edge(name, nodeA, nodeB, listenerSupport);
        edges.add(edge);
        nodeOne.addEdgeToNode(edge);
        listenerSupport.notifyObservers();
    }

    /**
     * Method that removes edge from the map.
     * @param node Node that the edge originates from.
     */
    public void removeEdge(Node node){
        edges.remove(latestEdgeIndex);
        for (int i = 0; i < node.getEdges().size(); i++) {
            if(latestEdgeIndex == i){
                node.getEdges().remove(i);
            }
        }
        listenerSupport.notifyObservers();
    }

    /**
     * Enables the usage of certain buttons.
     * @param item the button passed into the method.
     */
    public void enableItem(JButton item){
        item.setEnabled(true);
        if(nodeOne == null){
            item.setEnabled(false);
        }
    }

    /**
     * Checks if a node is selected.
     * @param x x coordinate of plane.
     * @param y y coordinate of plane.
     * @return returns the node if the click happens to be in its bounds.
     */
    public Node selectNode(int x, int y){
        for (Node node: nodes) {
            if(node.isInBounds(x, y)){
                if(edgeSelected){
                    addEdge("edgeX", nodes.get(index), node);
                    edgeSelected = false;
                }else {
                    nodeOne = node;
                    index = nodes.indexOf(node); // gets nodes index;
                    anEdge = null;
                }
                enableItem(itemAddArmy);
                enableItem(itemRemoveArmy);
                enableItem(itemRemoveNode);
                enableItem(itemAddEdge);
                enableItem(itemBeginSimulation);
                return node;
            }
        }
        return null;
    }

    /**
     * Checks if an edge is selected.
     * @param x x coordinate of plane.
     * @param y y coordinate of plane.
     * @return returns the node if the click happens to be in its bounds.
     */
    public Edge selectEdge(int x, int y){
        for (Edge edge: edges) {
            if(edge.getRect().isInBounds(x, y)){
                latestEdgeIndex = edges.indexOf(edge);
                anEdge = edge;
                enableItem(itemRemoveEdge);
                return edge;
            }
        }
        return null;
    }

    public void addListener(UpdateListener listener){
        listenerSupport.addListener(listener);
    }

    /**
     * Implemented interface method.
     * @return Converts the class components to Json format.
     */
    public String stringToJson(){
        StringBuilder json = new StringBuilder();
        json.append("{");
        for(Node node : nodes){
            json.append(node.stringToJson());
        }
        for(Edge edge : edges){
            json.append(edge.stringToJson());
        }
        json.append("\n}");
        return json.toString();
    }
}
