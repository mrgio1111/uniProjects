package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Map;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Class for removing a node from the graph.
 */
public class RemoveNodeAction extends AbstractAction {
    private Map map;

    public RemoveNodeAction(String name, Map map){
        super(name);
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        map.removeNode();
        map.setNodeOne(null);
        System.out.println("Successfully removed a node!");
        map.enableItem(map.getItemRemoveNode());
    }
}
