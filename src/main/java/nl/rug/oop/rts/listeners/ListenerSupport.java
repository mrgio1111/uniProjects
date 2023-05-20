package nl.rug.oop.rts.listeners;

import java.util.HashSet;
import java.util.Set;

/**
 * The following class updates the view and the model.
 */
public class ListenerSupport {
    private Set<UpdateListener> listeners;

    public ListenerSupport(){
        listeners = new HashSet<>();
    }

    public void addListener(UpdateListener listener){
        listeners.add(listener);
    }

    public void notifyObservers(){
        listeners.forEach(UpdateListener::update);
    }
}
