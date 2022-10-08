package knn;

import java.util.ArrayList;
import java.util.List;

public interface Tree {

    /**
     *
     */
    public Node root = null;
    public int size = 0;
    public int dimensions = 0;
    public Node nearest = null;
    public double nearestDistance = 0;
    public int visited = 0; 
    public List<Node> nodesList = new ArrayList<Node>();
    public int height = 0;

    public Tree buildTree();

    public Node findNearest();

    public static void printTree() {

    }
    
}
