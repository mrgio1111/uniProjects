package nl.rug.oop.rts.controller;

import lombok.Getter;
import nl.rug.oop.rts.model.Map;
import nl.rug.oop.rts.model.Node;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Class that helps the user select the preferred nodes.
 */
@Getter
public class SelectNodes extends MouseAdapter {
    private Map map;
    private Node selectedNode = null;

    /**
     * Constructor for SelectedNodes class.
     * @param map instantiated map that contains nodes/edges etc.
     */
    public SelectNodes(Map map) {
        this.map = map;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        int x = e.getX();
        int y = e.getY();
        selectedNode = map.selectNode(x, y);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        super.mouseDragged(e);
        if(selectedNode != null){
            selectedNode.updatePosition(e.getX(), e.getY());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        if(selectedNode != null){
            selectedNode = null;
        }
    }
}
