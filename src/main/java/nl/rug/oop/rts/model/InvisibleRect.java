package nl.rug.oop.rts.model;

import lombok.Getter;
import nl.rug.oop.rts.listeners.ListenerSupport;

/**
 * Class that represents and invisible rectangle drawn under an edge.
 */
@Getter
public class InvisibleRect extends ListenerSupport {
    private int x;
    private int y;
    private int width;

    private int height;
    private Node nodeA;
    private Node nodeB;
    private final ListenerSupport listenerSupport;

    /**
     * Constructor for invisible rectangle.
     * @param nodeA First node that the rectangle spans from.
     * @param nodeB Second node that the rectangle spans from.
     * @param listenerSupport listens to any changes.
     */
    public InvisibleRect(Node nodeA, Node nodeB, ListenerSupport listenerSupport){
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.listenerSupport = listenerSupport;
    }

    public int initX(){
        x = nodeA.invisibleRectangleCoordsX();
        return x;
    }

    public int initY(){
        y = nodeA.invisibleRectangleCoordsY();
        return y;
    }

    /**
     * Initializes the width of the rectangle by the coordinates of the 2nd node.
     * @return returns the width of the rectangle.
     */
    public int initW(){
        int x = initX();
        width = nodeB.getX() - x;
        return width;
    }

    /**
     * Initializes the height of the rectangle by the coordinates of the 2nd node.
     * @return returns the height of the rectangle.
     */
    public int initH(){
        int y = initY();
        height = nodeB.getY() - y;
        return height;
    }

    /**
     * Checks if the user click is in bounds of the invisible rectangle.
     * @param pointX PointX of user's click.
     * @param pointY PointY of user's click
     * @return returns if the click is in bounds or not.
     */
    public boolean isInBounds(int pointX, int pointY) {
        listenerSupport.notifyObservers();
        boolean first = (pointX >= x && pointX <= x + width && pointY >= y && pointY <= y + height);
        boolean second = (pointX >= x && pointX <= x + width && pointY <= y && pointY >= y + height);
        boolean third = (pointX <= x && pointX >= x + width && pointY >= y && pointY <= y + height);
        boolean fourth = (pointX <= x && pointX >= x + width && pointY <= y && pointY >= y + height);
        return first || second || third || fourth;
    }

}
