package nl.rug.oop.rts.controller;

import lombok.Getter;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Map;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class that helps the user select the preferred nodes.
 */
@Getter
public class SelectEdges extends MouseAdapter {
    private Map map;
    private Edge selectedEdge = null;

    /**
     * Constructor for SelectedNodes class.
     * @param map instantiated map that contains nodes/edges etc.
     */
    public SelectEdges(Map map) {
        this.map = map;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        int x = e.getX();
        int y = e.getY();
        map.selectEdge(x, y);
    }
}
