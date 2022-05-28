import java.awt.*;

public class Connection {
    public Node nodeA, nodeB;
    public boolean directed;
    Stroke stroke = new BasicStroke(4);
    public boolean isDeleted;

    public Connection(Node nodeA, Node nodeB, boolean directed) {
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.directed = directed;
        this.isDeleted = false;
    }

    public void draw(Graphics2D g2d) {
        if (!this.isDeleted) {
            g2d.setStroke(stroke);
            g2d.drawLine(this.nodeA.cx, this.nodeA.cy, this.nodeB.cx, this.nodeB.cy);
        }
    }

    public boolean equal(Connection other) {
        if (this.nodeA == other.nodeA && this.nodeB == other.nodeB ||
            this.nodeA == other.nodeB && this.nodeB == other.nodeA)
            return true;
        return false;
    }


}
