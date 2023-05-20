package nl.rug.oop.rts.view;

import nl.rug.oop.rts.listeners.UpdateListener;
import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Map;
import nl.rug.oop.rts.model.Node;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * Class for added panel through JSplitFrame.
 */
public class ObjectInfo extends JPanel implements UpdateListener {
    private Map map;

    /**
     * Constructor for ObjectInfo.
     * @param map Model of the Graph.
     */
    public ObjectInfo(Map map) {
        this.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        this.setBackground(Color.white);
        this.map = map;
        map.addListener(this);
    }

    public void addMouseMovementListener(MouseAdapter mouseAdapter){
        this.addMouseListener(mouseAdapter);
        this.addMouseMotionListener(mouseAdapter);
    }

    /**
     * Method that paints the panel.
     * @param g the <code>Graphics</code> object to protect
     */
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        drawInfo(g);
        for(Edge edge : map.getEdges()) {
            if(map.getAnEdge() != null && edge.getId() == map.getAnEdge().getId()) {
                g.setColor(Color.BLACK);
                g.drawString("Selected Edge id: " + edge.getId(), 40, 40);
                g.drawString("Selected Edge Name: " + edge.getName(), 40, 60);
                g.drawString("Connecting: " + edge.getNodeA().getName() + " with " + edge.getNodeB().getName(), 40, 80);
            } else{
                this.setBackground(Color.WHITE);
            }
        }
        for (Node node : map.getNodes()) {
            g.setColor(Color.BLACK);
            if (map.getNodeOne() != null && node.getId() == map.getNodeOne().getId()) {
                g.setColor(Color.BLACK);
                g.drawString("Selected Node name: " + node.getName(), 40, 20);
                g.drawString("Present events in node: ", 40, 200);
                for(int i = 0, j = 220; i < node.getEvents().size(); i++, j+=20){
                    g.drawString(node.printEvents(i), 40, j);
                }
            } else {
                this.setBackground(Color.WHITE);
            }
        }
    }

    /**
     * Draws the relevant game info on the right side of the panel.
     * @param g Graphics g component.
     */
    protected void drawInfo(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        float thicc = 5;
        g2.setStroke(new BasicStroke(thicc));
        g2.setColor(Color.black);
        g2.drawString("Armie colors:", 20, 480);
        g2.drawString("MORDOR", 20, 500);
        g2.setColor(Color.gray);
        g2.drawString("ISENGARD", 20, 520);
        g2.setColor(Color.CYAN);
        g2.drawString("MEN", 20, 540);
        g2.setColor(Color.ORANGE);
        g2.drawString("DWARVES", 20, 560);
        g2.setColor(Color.GREEN);
        g2.drawString("ELVES", 20, 580);

        g2.setColor(Color.black);
        g2.drawString("Events description: ", 20, 620);
        g2.setColor(Color.black);
        g2.drawString("Nazgul: Armies health is halved. Factions affected are Men, Dwarves, Elves.", 20, 640);
        g2.setColor(Color.red);
        g2.drawString("Gandalf: Armies damage increased. Factions affected are Men, Dwarves, Elves.", 20, 660);
        g2.setColor(Color.magenta);
        g2.drawString("Ring: Frodo destroyed the ring. Sauron and his forces are destroyed ", 20, 680);
    }

    public void update(){
        repaint();
    }

}
