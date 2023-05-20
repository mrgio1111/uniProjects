package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Map;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;
import java.util.Random;

/**
 * Adds Army to node.
 */

public class AddArmy extends AbstractAction {
    private Map map;
    private String[] factions = {"Men", "Elves", "Dwarves", "Isengard", "Mordor"};

    public AddArmy(String name, Map map){
        super(name);
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = JOptionPane.showInputDialog("What faction is the army? (Men, Elves, Dwarves, Isengard, Mordor)");
        System.out.println(name + " army created");
        for (String faction : factions) {
            if (Objects.equals(name, faction)) {
                Random rand= new Random();
                Army army = new Army(name, map , map.getListenerSupport());
                map.getNodeOne().addArmy(army);
                map.events(rand.nextInt(5), map.getNodeOne());
                map.getItemRemoveArmy().setEnabled(true);
            }
        }
        if (Objects.equals(name, "Mordor")) {
            map.getFrodoDefeatedNodes().add(map.getNodeOne());
        }
        if (Objects.equals(name, "Isengard")) {
            map.getFrodoDefeatedNodes().add(map.getNodeOne());
        }
    }
}
