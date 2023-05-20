package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Map;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Class responsible for removing edge from Graph.
 */
public class RemoveEdgeAction extends AbstractAction {
    private Map map;

    public RemoveEdgeAction(String name, Map map){
        super(name);
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        map.setAnEdge(null);
        map.removeEdge(map.getNodeOne());
        System.out.println("Successfully removed an Edge!");
        map.enableItem(map.getItemRemoveEdge());
    }
}
