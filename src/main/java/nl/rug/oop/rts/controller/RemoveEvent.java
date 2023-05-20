package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Event;
import nl.rug.oop.rts.model.Map;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Objects;

/**
 * Removes event from node.
 */
public class RemoveEvent extends AbstractAction {
    private Map map;

    public RemoveEvent(String name, Map map){
        super(name);
        this.map = map;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String eventPicked = JOptionPane.showInputDialog("What Event do you want to remove? (Nazgul, Gandalf, Ring)");
        System.out.println(eventPicked + " Event deleted");
        Iterator<Event> eventIterator = map.getNodeOne().getEvents().iterator();
        while(eventIterator.hasNext()){
            Event event = eventIterator.next();
            if (Objects.equals(eventPicked, event.getName())) {
                map.eventToBeRemoved(eventPicked);
                eventIterator.remove();
            }
        }
    }
}

