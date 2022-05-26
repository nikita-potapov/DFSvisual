import java.awt.*;

public class Connection {
    public Node nodeA, nodeB;
    public boolean directed;
    Stroke stroke = new BasicStroke(4);

    public Connection(Node nodeA, Node nodeB, boolean directed) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.directed = directed;
    }

    public void draw(Graphics2D g2d) {
        g2d.setStroke(stroke);
        g2d.drawLine(this.nodeA.cx, this.nodeA.cy - 30, this.nodeB.cx, this.nodeB.cy - 30);
    }
}
