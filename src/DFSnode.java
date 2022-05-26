import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class DFSnode {
    public Node node;
    public ArrayList<DFSnode> neighbors;
    public boolean checked;

    public DFSnode(Node node) {
        this.node = node;
        this.checked = false;
        this.neighbors = new ArrayList<>();
    }


    public void dfs(Stack<DFSnode> stack) {
        while (!stack.isEmpty()) {
            DFSnode current = stack.pop();
            ArrayList<DFSnode> neighbors = current.neighbors;
            for (int i = 0; i < neighbors.size(); i++) {
                //todo
                System.out.println(current.neighbors.get(i).node.value);
                if (!neighbors.get(i).checked) {
                    neighbors.get(i).checked = true;
                    stack.push(neighbors.get(i));
                }
            }

            System.out.print(current.node.value + ", ");
        }
    }

//    public String dfs(Stack<DFSnode> stack, ArrayList<DFSnode> completed) {
//        String str = "";
//        while (!stack.empty()) {
//            //todo
//            System.out.println("Stack: " + stack.toString());
//            System.out.println("Completed:: " + completed.toString());
//
//            DFSnode peeked = stack.pop();
//
//            completed.add(peeked);
//
//            System.out.println("new Stack: " + stack.toString());
//            System.out.println("new Completed: " + completed.toString());
//
//            //todo
//            System.out.println("Neighb: " + peeked.neighbors);
//
//            ArrayList<DFSnode> neighbs = peeked.neighbors;
//            List<DFSnode> sorted = peeked.neighbors.stream().sorted((o1,o2) -> -o1.node.value.compareTo(o2.node.value)).toList();
//
//            //todo
//            System.out.println("Sorted neighb: " + sorted);
//
//
//            for (int i = 0; i < neighbs.size(); i++) {
//                if (!completed.contains(sorted.get(i))) {
//                    stack.push(sorted.get(i));
//                }
//            }
//
//            //todo
//            System.out.println("-------------------------------------------");
//        }
//        return str;
//    }

    public String toString() {
        return this.node.value;
    }
}
