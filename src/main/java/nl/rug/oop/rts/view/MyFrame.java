package nl.rug.oop.rts.view;

import lombok.Getter;
import nl.rug.oop.rts.controller.*;
import nl.rug.oop.rts.model.*;

import javax.swing.*;
import java.awt.*;

/**
 * Class for MyFrame creates a frame with all the required specifications.
 */
@Getter
public class MyFrame extends JFrame {
    private Map map;
    private ObjectInfo objectInfo;

    /**
     * Constructor for the frame of the program.
     * @param map The model for the graph.
     */
    public MyFrame(Map map){
        //instantiations
        this.map = map;
        map.setFrame(this);
        DrawGraph drawGraph = new DrawGraph(map);
        objectInfo = new ObjectInfo(map);
        SelectNodes selectNodes = new SelectNodes(map);
        SelectEdges selectEdges = new SelectEdges(map);

        //mouse listeners
        drawGraph.addMouseMovementListener(selectNodes);
        drawGraph.addMouseMovementListener(selectEdges);
        objectInfo.addMouseMovementListener(selectNodes);
        objectInfo.addMouseMovementListener(selectEdges);

        //renaming stuff
        JTextField textfield = new JTextField();
        textfield.setPreferredSize(new Dimension(150,40));
        JButton button = new JButton(new Rename(map, textfield));
        button.setText("Rename");

        objectInfo.add(textfield);
        objectInfo.add(button);

        //frame stuff
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLayout(new BorderLayout());
        this.setSize(1200, 1000);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setTitle("My GUI");
        customMenuBar(map);

        //jSplitPane stuff
        JSplitPane jSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, drawGraph, objectInfo);
        this.getContentPane().add(jSplitPane);
    }

    /**
     * This method creates and returns the menu bar.
     * @param map The model of the graph.
     * @return returns the menu option bar.
     */
    public JMenuBar customMenuBar(Map map) {
        JMenuBar menuBar = new JMenuBar();
        JButton addNode = new JButton(new AddNodeAction("Add Node",map));
        JButton removeNode = new JButton(new RemoveNodeAction("Remove node", map));
        removeNode.setEnabled(false);
        map.setItemRemoveNode(removeNode);
        JButton addEdge = new JButton(new AddEdgeAction("Add Edge", map));
        addEdge.setEnabled(false);
        map.setItemAddEdge(addEdge);
        JButton removeEdge = new JButton(new RemoveEdgeAction("Remove Edge",map));
        removeEdge.setEnabled(false);
        map.setItemRemoveEdge(removeEdge);
        JButton addArmy = new JButton(new AddArmy("Add Army", map));
        addArmy.setEnabled(false);
        map.setItemAddArmy(addArmy);
        JButton removeArmy = new JButton(new RemoveArmy("Remove Army", map));
        removeArmy.setEnabled(false);
        map.setItemRemoveArmy(removeArmy);
        JButton removeEvent = new JButton(new RemoveEvent("Remove Event", map)); // removeEvent
        addArmy.setEnabled(false);
        map.setItemRemoveEvent(removeEvent);
        JButton addEvent = new JButton(new AddEvent("Add Event", map)); // addEvent
        addArmy.setEnabled(false);
        map.setItemAddEvent(addEvent);

        JButton beginSimulation = new JButton(new Simulation("Begin Simulation", map));
        beginSimulation.setEnabled(false);
        map.setItemBeginSimulation(beginSimulation);
        JButton saveSimulation = new JButton(new SaveSimulation("Save Simulation", map));
        saveSimulation.setEnabled(true);

        menuBar.add(addNode);
        menuBar.add(removeNode);
        menuBar.add(addEdge);
        menuBar.add(removeEdge);
        menuBar.add(addArmy);
        menuBar.add(removeArmy);
        menuBar.add(addEvent);
        menuBar.add(removeEvent);
        menuBar.add(beginSimulation);
        menuBar.add(saveSimulation);

        this.add(menuBar);
        this.setJMenuBar(menuBar);
        return menuBar;
    }

}
