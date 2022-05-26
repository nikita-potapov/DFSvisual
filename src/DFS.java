import java.awt.*;
import java.util.*;
import java.util.stream.Collectors;

public class DFS {
    Node startNode;


    public DFS() {
        this.startNode = null;
    }

    public static void clearNodesBackground(ArrayList<Node> nodes) {
        for (Node node : nodes) {
            node.color = Color.WHITE;
        }
    }

    Deque<Node> play() {
        Deque<Node> stack = new LinkedList<>();
        DFS.dfs(startNode, stack);
        //todo
        System.out.println(stack);
        Deque<Node> reversed = new LinkedList<>();
        while (!stack.isEmpty()) {
            reversed.addLast(stack.pop());
        }
        return reversed;
    }

    public static void dfs(Node node, Deque<Node> stack) {
        node.checked = true;
        stack.push(node);
        ArrayList<Node> neighbors = (ArrayList<Node>) node.neighbors.stream().sorted(((o1, o2) -> o1.value.compareTo(o2.value))).collect(Collectors.toList());
        for (Node neighbor : neighbors) {
            if (!neighbor.checked) {
                DFS.dfs(neighbor, stack);
                stack.push(node);
            }
        }
    }

    public static void setAllNodesVisitedFalse(ArrayList<Node> nodes) {
        for (Node node : nodes) {
            node.checked = false;
        }
    }

}
