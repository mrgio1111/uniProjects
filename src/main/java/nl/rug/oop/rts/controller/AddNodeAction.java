package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Map;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This class is used to add node to the map.
 */
public class AddNodeAction extends AbstractAction {
    private Map map;

    public AddNodeAction(String name, Map map){
        super(name);
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        map.addNode("nodeX");
    }
}
