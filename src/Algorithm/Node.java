package src.Algorithm;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private String word;
    private int cost;
    private Node parent;

    public Node(String word, int cost, Node parent) {
        this.word = word;
        this.cost = cost;
        this.parent = parent;
    }
    public String getWord() {
        return this.word;
    }
    public int getCost() {
        return this.cost;
    }
    public Node getParent(){
        return this.parent;
    }

    public List<String> reconstructPath() {
        List<String> path = new ArrayList<>();
    Node currentNode = this; 

        while (currentNode != null) {
            path.add(0, currentNode.getWord()); 
            currentNode = currentNode.getParent();
        }

        return path;
    }
}
