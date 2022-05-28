import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;

public class Visualiser implements ActionListener, KeyListener {
    ArrayList<Node> nodes;
    ArrayList<Connection> connections;
    Node clickedNodeA, clickedNodeB, draggedNode;
    ArrayList<Node> historyBlocks;
    int letterIndex = 0;
    Graphics2D currg2d;
    DFS dfs;
    Deque<Node> dfsHistory;
    Node currentNode;
    ArrayList<Point> points;
    int startDragX, startDragY;
    ProgramState state;


    public Visualiser() {
        nodes = new ArrayList<>();
        connections = new ArrayList<>();
        this.dfs = new DFS();
        this.points = new ArrayList<>();
        this.state = ProgramState.EDIT;
    }

    public void drawFrame(Graphics2D g2d) {
        this.currg2d = g2d;
        drawObjects(g2d);
    }

    private void drawObjects(Graphics2D g2d) {
        drawConnections(g2d);
        drawNodes(g2d);
        drawLabel(g2d);
        drawHistory(g2d);
    }

    private void drawLabel(Graphics2D g2d) {
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
        } else {
            this.state = ProgramState.EDIT;
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
        int clickX, clickY;
        clickX = e.getX() - 8;
        clickY = e.getY() - Settings.NODE_RADIUS;

        Node node = this.getClickedNode(e);

        if (node != null) {
            //todo
            System.out.println("Node: " + node.toString() + "[connect: " + node.connections.size() + "]");
        }

        if (clickX < 220 || clickX > Settings.WINDOW_WIDTH - Settings.NODE_RADIUS ||
                clickY > Settings.WINDOW_HEIGHT - 100 - Settings.NODE_RADIUS / 2 || clickY < Settings.NODE_RADIUS / 2) {
            return;
        }

        if (e.getButton() == MouseEvent.BUTTON3 && node != null) {
            this.dfs.startNode = node;
            DFS.clearNodesBackground(this.nodes);
            this.dfsHistory = this.dfs.play();
            ArrayList<Node> blocks = new ArrayList<>();
            for (int i = 0; i < this.dfsHistory.size(); i++) {
                Node tmp = dfsHistory.poll();
                blocks.add(tmp);
                dfsHistory.push(tmp);
            }
            this.historyBlocks = blocks;
            DFS.setAllNodesVisitedFalse(this.nodes);
            this.state = ProgramState.PLAY;
            return;
        }

        if (this.state == ProgramState.PLAY)
            return;

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
                    for (int i = 0; i < this.clickedNodeA.connections.size(); i++) {
                        Connection connA = this.clickedNodeA.connections.get(i);
                        if (connA.equal(conn)) {
                            this.clickedNodeA.connections.remove(i);
                        }
                    }

                    for (int i = 0; i < this.clickedNodeB.connections.size(); i++) {
                        Connection connA = this.clickedNodeB.connections.get(i);
                        if (connA.equal(conn)) {
                            this.clickedNodeB.connections.remove(i);
                        }
                    }

                    for (int j = 0; j < this.connections.size(); j++) {
                        Connection tuv = this.connections.get(j);
                        if (tuv.equal(conn)) {
                            this.connections.remove(j);
                        }
                    }

                    this.clickedNodeA.removeNeighbor(conn);
                    this.clickedNodeB.removeNeighbor(conn);

                } else {
                    this.connections.add(conn);

                    this.clickedNodeA.neighbors.add(this.clickedNodeB);
                    this.clickedNodeB.neighbors.add(this.clickedNodeA);

                    this.clickedNodeA.connections.add(conn);
                    this.clickedNodeB.connections.add(conn);
                }
                this.clickedNodeA.isHighlightedA = false;
                this.clickedNodeB.isHighlightedB = false;
                this.clickedNodeA = null;
                this.clickedNodeB = null;
            }

        } else {
            Node newNode = new Node();
            newNode.isLighted = false;
            newNode.cx = clickX;
            newNode.cy = clickY;
            newNode.radius = Settings.NODE_RADIUS;
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
        return this.getDistance(x - 8, y - Settings.NODE_RADIUS, node.cx, node.cy) < node.radius;
    }

    public Node getClickedNode(MouseEvent e) {
        for (Node node : this.nodes) {
            if (this.clickInside(e.getX(), e.getY(), node)) {
                this.startDragX = e.getX();
                this.startDragY = e.getY() - 30;
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

    public void drawHistory(Graphics2D g2d) {
        if (this.dfsHistory != null) {
            g2d.drawString(this.dfsHistory.toString(), 200, Settings.WINDOW_HEIGHT - 50);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_DELETE || e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (this.clickedNodeA != null && this.clickedNodeB == null) {
                if (this.clickedNodeA.connections.isEmpty()) {
                    this.nodes.remove(this.clickedNodeA);
                    this.clickedNodeA = null;
                    this.clickedNodeB = null;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
        //todo
        System.out.println("Released: ");
        this.draggedNode = null;
    }

    public void mouseDragged(MouseEvent e) {
        if (this.draggedNode == null) {
            Node node = this.getClickedNode(e);
            if (node != null) {
                this.draggedNode = node;
            }
        }


        int mouseX = e.getX();
        int mouseY = e.getY() - 30;

//        if (this.draggedNode != null &&
//                mouseX > 215 && mouseX < Settings.WINDOW_WIDTH - Settings.NODE_RADIUS / 2 &&
//                mouseY < Settings.WINDOW_HEIGHT - 115 && mouseY > Settings.NODE_RADIUS / 2
//
//        ) {
//            this.draggedNode.cy = mouseY;
//            this.draggedNode.cx = mouseX;
//        }

        if (this.draggedNode != null)
        {
            if (mouseX > 215 && mouseX < Settings.WINDOW_WIDTH - 10 - Settings.NODE_RADIUS / 2){
                this.draggedNode.cx = mouseX;
            }
            if (mouseY < Settings.WINDOW_HEIGHT - 115 && mouseY > Settings.NODE_RADIUS / 2) {
                this.draggedNode.cy = mouseY;
            }
        }

        //todo
        System.out.println("Dragged:");
    }
}
