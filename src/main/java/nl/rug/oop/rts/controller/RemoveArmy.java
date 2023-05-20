package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Map;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This class removes armies from a node.
 */
public class RemoveArmy extends AbstractAction {

    private Map map;

    public RemoveArmy(String name, Map map){
        super(name);
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        map.removeArmy();
    }
}
