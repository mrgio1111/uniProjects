package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Map;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Class for adding edge to the graph.
 */
public class AddEdgeAction extends AbstractAction {
    private Map map;

    public AddEdgeAction(String name, Map map){
        super(name);
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        map.enableItem(map.getItemAddEdge());
        map.setEdgeSelected(true);
    }
}
