import java.util.ArrayList;
import java.util.Stack;
import java.util.UUID;

public class DFS {
    ArrayList<String> dfsHistory;
    ArrayList<DFSnode> graph;
    ArrayList<Node> currNeighbors;
    Node startNode, currentNode;
    DFSnode startDFSnode;


    public DFS() {
        this.dfsHistory = new ArrayList<>();
        this.graph = new ArrayList<>();
        this.startNode = null;

    }

    public void play() {
        Stack<DFSnode> stack = new Stack<>();
        stack.push(this.startDFSnode);
        this.startDFSnode.dfs(stack);
    }

    public void setGraph(ArrayList<Node> nodes) {
        for (Node node : nodes) {

            DFSnode tmp = new DFSnode(node);

            for (int j = 0; j < node.neighbors.size(); j++) {
                tmp.neighbors.add(new DFSnode(node.neighbors.get(j)));
            }

            if (startNode == node) {
                startDFSnode = tmp;
            }

            graph.add(tmp);
        }
    }
}
