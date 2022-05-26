import java.util.ArrayList;
import java.util.Stack;

public class DFS {
    Node startNode;


    public DFS() {
        this.startNode = null;
    }

    public void play() {
        Stack<Node> stack = new Stack<>();
        DFS.dfs(startNode, stack);
        //todo
        System.out.println(stack);

    }

    public static void dfs(Node node, Stack<Node> stack) {
        node.checked = true;
        stack.push(node);
        ArrayList<Node> neighbors = node.neighbors;
        for (Node neighbor : neighbors) {
            if (!neighbor.checked) {
                DFS.dfs(neighbor, stack);
            }
        }
    }

    public static void setAllNodesVisitedFalse(ArrayList<Node> nodes) {
        for (Node node : nodes) {
            node.checked = false;
        }
    }

}
