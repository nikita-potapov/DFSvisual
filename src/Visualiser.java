import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;
import java.util.Stack;

public class Visualiser implements ActionListener, KeyListener {
    Random random = new Random();
    ArrayList<Node> nodes;
    ArrayList<Connection> connections;
    Node clickedNodeA, clickedNodeB;
    ArrayList<String> currentText;
    int letterIndex = 0;
    Graphics2D currg2d;
    DFS dfs;
    Deque<Node> dfsHistory;
    Node currentNode;


    public Visualiser() {
        nodes = new ArrayList<>();
        connections = new ArrayList<>();
        this.dfs = new DFS();
    }

    public void drawFrame(Graphics2D g2d) {
        this.currg2d = g2d;
        drawObjects(g2d);
    }

    private void drawObjects(Graphics2D g2d) {
        drawConnections(g2d);
        drawNodes(g2d);
    }

    public void update() {
        if (this.dfsHistory != null && !this.dfsHistory.isEmpty()) {
            if (this.currentNode == null) {
                this.currentNode = this.dfsHistory.poll();
                currentNode.color = Color.RED;
            } else {
                this.currentNode.color = Color.GRAY;
                this.currentNode = null;
            }
        }
    }

    public void drawNodes(Graphics2D g2d) {
        for (Node node : this.nodes) {
            node.draw(g2d);
        }

    }

    public void drawConnections(Graphics2D g2d) {
        for (Connection connection : this.connections) {
            connection.draw(g2d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void mouseClicked(MouseEvent e) {
        Node node = this.getClickedNode(e);

        if (e.getButton() == MouseEvent.BUTTON3 && node != null) {

            //todo
            System.out.println("\n--------------------------------\nClicked");

            this.dfs.startNode = node;
            this.dfsHistory = this.dfs.play();
            DFS.setAllNodesVisitedFalse(this.nodes);
            DFS.clearNodesBackground(this.nodes);
            return;
        }

        if (node != null) {

            if (this.clickedNodeA == null) {
                this.clickedNodeA = node;
                node.isHighlightedA = true;
            } else if (this.clickedNodeA == node) {
                this.clickedNodeA = null;
                node.isHighlightedA = false;
            } else if (this.clickedNodeB == null) {
                this.clickedNodeB = node;
                node.isHighlightedB = true;
            } else if (this.clickedNodeB == node) {
                this.clickedNodeB = null;
                node.isHighlightedB = false;
            }
            if (this.clickedNodeA != null && this.clickedNodeB != null) {
                Connection conn = new Connection(this.clickedNodeA, this.clickedNodeB, false);
                int tmp = this.containConnection(this.connections, conn);
                if (tmp != -1) {
                    this.connections.remove(tmp);
                    this.clickedNodeA.neighbors.remove(this.clickedNodeB);
                    this.clickedNodeB.neighbors.remove(this.clickedNodeA);
                } else {
                    this.connections.add(conn);
                    this.clickedNodeA.neighbors.add(this.clickedNodeB);
                    this.clickedNodeB.neighbors.add(this.clickedNodeA);
                }
                this.clickedNodeA.isHighlightedA = false;
                this.clickedNodeB.isHighlightedB = false;
                this.clickedNodeA = null;
                this.clickedNodeB = null;
            }

        } else {
            Node newNode = new Node();
            newNode.isLighted = false;
            newNode.cx = e.getX();
            newNode.cy = e.getY();
            newNode.radius = 30;
            newNode.value = Settings.ALPHABET.charAt(this.letterIndex % 26) + (this.letterIndex / 26 == 0 ? "" : Integer.toString(this.letterIndex / 26)) + "";
            this.nodes.add(newNode);
            this.letterIndex++;
            if (this.clickedNodeA != null) {
                this.clickedNodeA.isHighlightedA = false;
                this.clickedNodeA.isHighlightedB = false;
                this.clickedNodeA = null;
            }

            if (this.clickedNodeB != null) {
                this.clickedNodeB.isHighlightedA = false;
                this.clickedNodeB.isHighlightedB = false;
                this.clickedNodeB = null;
            }
        }

    }

    public double getDistance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public boolean clickInside(int x, int y, Node node) {
        return this.getDistance(x, y, node.cx, node.cy) <= node.radius;
    }

    public Node getClickedNode(MouseEvent e) {
        for (Node node : this.nodes) {
            if (this.clickInside(e.getX(), e.getY(), node)) {
                return node;
            }
        }
        return null;
    }

    public int containConnection(ArrayList<Connection> connections, Connection conn) {
        for (int i = 0; i < connections.size(); i++) {
            if ((conn.nodeA == connections.get(i).nodeA && conn.nodeB == connections.get(i).nodeB) ||
                    (conn.nodeA == connections.get(i).nodeB && conn.nodeB == connections.get(i).nodeA))
                return i;
        }
        return -1;
    }

    public void deleteConnectionsWithNode(Node node) {
        for (int i = 0; i < this.connections.size(); i++) {
            Connection conn = this.connections.get(i);
            if (conn.nodeA == node || conn.nodeB == node) {
                this.connections.remove(conn);
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (this.clickedNodeA != null && this.clickedNodeB == null) {
                this.deleteConnectionsWithNode(this.clickedNodeA);
                this.nodes.remove(this.clickedNodeA);
                this.clickedNodeA = null;
                this.clickedNodeB = null;

            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
