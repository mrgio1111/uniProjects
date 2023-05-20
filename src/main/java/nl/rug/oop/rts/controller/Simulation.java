package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Map;
import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This class is used to begin the simulation.
 */
public class Simulation extends AbstractAction {
    private Map map;

    public Simulation(String name, Map map){
        super(name);
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        map.simulate();
    }
}
