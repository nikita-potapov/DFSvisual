package graph;

import java.util.ArrayList;

public class GraphNode<T> {
    private T nodeValue;
    private ArrayList<GraphNode<T>> neighborNodes;

    public T getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(T value) {
        nodeValue = value;
    }

    public void addNeighbor(GraphNode<T> newNeighbor) {
        neighborNodes.add(newNeighbor);
    }

    public void deleteNeighbor(GraphNode<T> neighbor) {
        neighborNodes.remove(neighbor);
    }

    public ArrayList<GraphNode<T>> getNeighborNodes() {
        return neighborNodes;
    }

    public void setNeighborNodes(ArrayList<GraphNode<T>> newNeighborNodes) {
        neighborNodes = newNeighborNodes;
    }

    public boolean isNeighbor(GraphNode<T> neighbor) {
        for (GraphNode<T> node : neighborNodes) {
            if (neighbor == node) {
                return true;
            }
        }
        return false;
    }
}
