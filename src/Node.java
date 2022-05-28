import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Node {
    public int cx, cy;
    public int radius;
    public String value;
    public boolean isLighted;
    public boolean isHighlightedA, isHighlightedB;
    public ArrayList<Node> neighbors;
    public Color color, back;
    public boolean checked;
    public ArrayList<Connection> connections;
    public Stroke stroke = new BasicStroke(6);

    public Node() {
        this.connections = new ArrayList<>();
        this.neighbors = new ArrayList<>();
        this.color = Color.white;
        this.checked = false;
    }


    public void draw(Graphics2D g2d) {
        if (this.isLighted) {
            g2d.setPaint(Settings.COLOR_LIGHTED);
            g2d.fillOval(this.cx - this.radius / 2, this.cy - this.radius / 2, this.radius, this.radius);
        }
        if (this.isHighlightedA) {
            g2d.setPaint(Color.green);
        } else if (this.isHighlightedB) {
            g2d.setPaint(Color.RED);
        } else {
            g2d.setPaint(Color.black);
        }
        g2d.setStroke(stroke);
        g2d.drawOval(this.cx - this.radius / 2, this.cy - this.radius / 2, this.radius, this.radius);
        g2d.setPaint(this.color);
        g2d.fillOval(this.cx - this.radius / 2, this.cy - this.radius / 2, this.radius, this.radius);
        g2d.setPaint(Color.black);
        g2d.drawString((this.value != null ? this.value : ""), this.cx - 6 - (this.value.length() > 1 ? 3 : 0), this.cy + 5);
    }

    public String toString() {
        return this.value;
    }

    public void removeNeighbor(Connection conn) {
        if (conn.nodeA == this) {
            this.neighbors.remove(conn.nodeB);
        } else {
            this.neighbors.remove(conn.nodeA);
        }
    }
}
