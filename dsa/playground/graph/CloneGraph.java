package graph;

import java.util.*;

class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int val) {
        this.val = val;
        neighbors = new ArrayList<>();
    }

    public Node(int val, ArrayList<Node> neighbors) {
        this.val = val;
        this.neighbors = neighbors;
    }
}

public class CloneGraph {

    public static Node cloneGraph(Node node) {
        // Your solution here
        return null;
    }

    public static void main(String[] args) {
        // Test case 1: General case
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        node1.neighbors.add(node2);
        node1.neighbors.add(node4);
        node2.neighbors.add(node1);
        node2.neighbors.add(node3);
        node3.neighbors.add(node2);
        node3.neighbors.add(node4);
        node4.neighbors.add(node1);
        node4.neighbors.add(node3);
        System.out.println("Test case 1: " + cloneGraph(node1)); // Expected: Cloned graph of 1-2-3-4

        // Test case 2: Single node
        Node singleNode = new Node(1);
        System.out.println("Test case 2: " + cloneGraph(singleNode)); // Expected: Cloned single node

        // Test case 3: Null graph
        System.out.println("Test case 3: " + cloneGraph(null)); // Expected: null
    }
}