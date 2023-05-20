package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Map;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Objects;

/**
 * Adds event to the node.
 */
public class AddEvent extends AbstractAction {
    private Map map;

    public AddEvent(String name, Map map){
        super(name);
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String eventPicked = JOptionPane.showInputDialog("What Event do you want to add? (Nazgul, Gandalf, Ring)");
        System.out.println(eventPicked + " Event added");
        if(Objects.equals(eventPicked, "Nazgul")){
            map.events(0, map.getNodeOne());
        }
        if(Objects.equals(eventPicked, "Gandalf")){
            map.events(1, map.getNodeOne());
        }
        if(Objects.equals(eventPicked, "Ring")){
            map.events(7, map.getNodeOne());
        }

    }
}

