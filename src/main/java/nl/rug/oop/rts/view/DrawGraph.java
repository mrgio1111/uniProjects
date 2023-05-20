package nl.rug.oop.rts.view;

import nl.rug.oop.rts.listeners.UpdateListener;
import nl.rug.oop.rts.model.Army;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Map;
import nl.rug.oop.rts.model.Node;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.util.Objects;

/**
 * Class for MyPanel where the actual rts game takes place.
 */
public class DrawGraph extends JPanel implements UpdateListener {
    private Map map;

    /**
     * Constructor for MyPanel class.
     * @param map The model of the graph.
     */
    public DrawGraph(Map map){
        this.setBorder(BorderFactory.createEmptyBorder(300, 300, 300, 300));
        this.setBackground(Color.white);
        this.map = map;
        map.addListener(this);
    }

    public void addMouseMovementListener(MouseAdapter mouseAdapter){
        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
    }

    /**
     * Method that creates the shapes of the nodes.
     * @param g the <code>Graphics</code> object to protect.
     */
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawNodes(g);
    }

    /**
     * Draws nodes of the graph.
     * @param g Graphics g.
     */
    protected void drawNodes(Graphics g){
        for (Node node : map.getNodes()) {
            if (map.getNodeOne() != null && node.getId() == map.getNodeOne().getId()) {
                g.setColor(Color.YELLOW);
                g.fillRect(node.getX(), node.getY(), node.getWidth(), node.getHeight());
                g.setColor(Color.RED);
                g.drawString(node.getName(), node.getCenterX(), node.getCenterY());
            } else {
                g.setColor(Color.BLUE);
                g.fillRect(node.getX(), node.getY(), node.getWidth(), node.getHeight());
                g.setColor(Color.RED);
                g.drawString(node.getName(), node.getCenterX(), node.getCenterY());
            }
            //army
            drawArmies(g, node);
            drawEdges(g);
        }
    }

    /**
     * Draws the edges of the graph.
     * @param g Graphics g component.
     */
    protected void drawEdges(Graphics g){
        for(Edge edge : map.getEdges()) {
            g.setColor(Color.white);
            g.fillRect(edge.getRect().initX(), edge.getRect().initY(), edge.getRect().initW(), edge.getRect().initH());
            if(map.getAnEdge() != null && edge.getId() == map.getAnEdge().getId()) {
                g.setColor(Color.RED);
                g.drawLine(edge.initEdgeX1(edge), edge.initEdgeY1(edge), edge.initEdgeX2(edge), edge.initEdgeY2(edge));
            } else {
                g.setColor(Color.black);
                g.drawLine(edge.initEdgeX1(edge), edge.initEdgeY1(edge), edge.initEdgeX2(edge), edge.initEdgeY2(edge));
            }
        }
    }

    /**
     * Draws the armies of the graph.
     * @param g Graphics g component.
     * @param node Node the armies are drawn on.
     */
    protected void drawArmies(Graphics g, Node node){
        if(node.getArmies() != null) {
            for (Army army : node.getArmies()) {
                if (Objects.equals(army.getFaction(), "Men")) {
                    g.setColor(Color.CYAN);
                    g.fillRect(node.getX(), node.getY(), node.getWidth() - 20, node.getHeight() - 20);
                }
                if (Objects.equals(army.getFaction(), "Elves")) {
                    g.setColor(Color.GREEN);
                    g.fillRect(node.getX() + 20, node.getY(), node.getWidth() - 20, node.getHeight() - 20);
                }
                if (Objects.equals(army.getFaction(), "Dwarves")) {
                    g.setColor(Color.ORANGE);
                    g.fillRect(node.getX(), node.getY() + 20, node.getWidth() - 20, node.getHeight() - 20);
                }
                if (Objects.equals(army.getFaction(), "Mordor")) {
                    g.setColor(Color.BLACK);
                    g.fillRect(node.getX(), node.getY(), node.getWidth() - 20, node.getHeight() - 20);
                }
                if (Objects.equals(army.getFaction(), "Isengard")) {
                    g.setColor(Color.GRAY);
                    g.fillRect(node.getX() + 20, node.getY(), node.getWidth() - 20, node.getHeight() - 20);
                }
            }
        }
    }

    /**
     * implemented method from Updater interface.
     */
    public void update(){
        repaint();
    }

}
