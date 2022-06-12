import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class KdTree {
    
    // member variables
    public Node root = null;
    public int size;
    public int dimensions;
    public Node nearest = null;
    public double nearestDistance = 0;
    public int visited = 0; 

    // constructor
    public KdTree(List<Node> nodes) {
        dimensions = nodes.get(0).getCoords().length;
        root = makeTree(nodes, 0, nodes.size(), 0);
    }

    public Node makeTree(List<Node> nodes, int begin, int end, int index) {
        if (end <= begin) {
            return null;
        }
        int n = begin + (end - begin) / 2;
        Node node = QuickSelect.select(nodes, begin, end - 1, n, new NodeComparator(index));
        index = (index + 1) % dimensions;
        node.left = makeTree(nodes, begin, n, index);
        node.right = makeTree(nodes, n + 1, end, index);
        //printKdTree();
        return node;
    }

    private class NodeComparator implements Comparator<Node> {
        private int index;

        private NodeComparator(int index_) {
            index = index_;
        }
        public int compare(Node n1, Node n2) {
            return Double.compare(n1.getCoord(index), n2.getCoord(index));
        }

    }

    public double distance() {
        return Math.sqrt(nearestDistance);
    }


    public Node findNearest(Node target) {
        if (root == null ) {
            throw new IllegalStateException("tree is empty!");
        }
        nearest = null;
        nearestDistance = 0;
        visited = 0;
        findNearest(root, target, 0);
        return nearest;
    }

    private void findNearest(Node root, Node target, int index) {
        if (root == null) {
            return;
        }
        ++visited;
        double dist = root.distance(target);
        if (nearest == null || dist < nearestDistance) {
            nearestDistance = dist;
            nearest = root;
        }
        if (nearestDistance == 0) {
            return;
        }
        double depth = root.getCoord(index) - target.getCoord(index);
        index = (index + 1) % dimensions;
        findNearest(depth > 0 ? root.left : root.right, target, index);
        if (depth * depth >= nearestDistance) {
            return;
        }
        findNearest(depth > 0 ? root.right : root.left, target, index);
    }
    
    // print function
    public void printKdTree() {
        System.out.println("----------------------------- print tree -----------------------------");
        List<Node> nodes = new ArrayList();
        System.out.print("0 ");
        for(int k = 0; k < root.getHeight(); k++) {
            System.out.print("      ");
        }
        root.printNode();
        System.out.println();
        if(root.getLeft() == null && root.getRight() == null) {return;}
        nodes.add(root.getLeft());
        nodes.add(root.getRight());
        for(int i = 0; i < root.getHeight(); i++) {
            for(int l = 0; l < 20/(i+1); l++) {
                System.out.print(" ");
            }
            for(int j = 0; j <= i; j++) {
                System.out.print(" /   \\   ");
            }
            System.out.println();
            System.out.print(i+1 + "   ");
            List<Node> nodesNext = new ArrayList();
            int index = 0;
            for(Node node: nodes) {
                if(index != 0) {
                    System.out.print(" ");
                }
                ++index;
                if(node == null) {
                    System.out.print(" - ");
                    nodesNext.add(null);
                    nodesNext.add(null);
                } else {
                    node.printNode();
                    nodesNext.add(node.left);
                    nodesNext.add(node.right);
                }
            }
            System.out.println();
            nodes = nodesNext;
        }
        System.out.println("----------------------------------------------------------------------");
    
    }

    // getters & setters
    public Node getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }
}