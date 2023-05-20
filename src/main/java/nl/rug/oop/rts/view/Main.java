package nl.rug.oop.rts.view;

import com.formdev.flatlaf.FlatDarculaLaf;
import nl.rug.oop.rts.listeners.ListenerSupport;
import nl.rug.oop.rts.model.Map;
import javax.swing.*;

/**
 * Main class which instantiates the RTS game.
 */
public class Main {

    /**
     * Main class method.
     * @param args arguments entered.
     */
    public static void main(String[] args) {
        FlatDarculaLaf.setup(); // Dark mode
        ListenerSupport listenerSupport = new ListenerSupport();
        Map map = new Map(listenerSupport);
        SwingUtilities.invokeLater(() -> {
            new MyFrame(map);
        });
    }
}