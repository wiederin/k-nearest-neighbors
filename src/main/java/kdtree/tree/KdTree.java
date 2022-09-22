package src.main.java.kdtree.tree;
import java.util.ArrayList;
import java.util.List;

import knn.kdtree.select.QuickSelect;
import knn.kdtree.tree.node.*;

public class KdTree {
    
    // member variables
    public Node root = null;
    public int size;
    public int dimensions;
    public Node nearest = null;
    public double nearestDistance = 0;
    public int visited = 0; 
    public List<Node> nodesList = new ArrayList<Node>();
    public int height;

    // constructor from list of nodes
    public KdTree(List<Node> nodes) {
        dimensions = nodes.get(0).coords.length;
        // recursive makeTree function from nodes
        root = makeTree(nodes, 0, nodes.size(), 0, null);
        nodesList = nodes;
    }

    // constructor from node
    public KdTree(Node _root) {
        root = _root;
        nodesList.add(_root);
    }

    // recursive function to make tree from nodes and return root
    public Node makeTree(List<Node> nodes, int begin, int end, int index, Node parent) {
        // if at the end of nodes return null
        if (end <= begin) {
            return null;
        }
        // QuickSelect node
        int n = begin + (end - begin) / 2;
        Node node = QuickSelect.select(nodes, begin, end - 1, n, new NodeComparator(index));
        // set parent of node
        node.parent = parent;
        // update index
        index = (index + 1) % dimensions;
        // make left and right subtrees
        node.left = makeTree(nodes, begin, n, index, node);
        node.right = makeTree(nodes, n + 1, end, index, node);
        //printKdTree();
        return node;
    }
    
    public void insertNode(String coords) {
        if(findNode(coords) != null){
            System.out.println("The coordinates are already in the tree");
        } else {
            nodesList.add(new Node(coords));
            root = makeTree(nodesList, 0, nodesList.size(), 0, null);
        }
    }

    // findNode from coordString returns node or null if not in tree
    public Node findNode(String coordString) {
        // make node from string for comparison
        Node node = new Node(coordString);
        // recursive find node 
        return findNode(node, root, 0);
    }

    // recursive findNode function 
    public Node findNode(Node node, Node _root, int index) {
        // check if _root is node
        boolean found = true;
        for(int i = 0; i < _root.coords.length; i++) {
            if(_root.getCoord(i) != node.getCoord(i)){
                found = false;
            }
        }
        // if _root is node return _root
        if(found) {
            return _root;
        }
        //System.out.println(node.getCoord(index) + " " +  _root.getCoord(index));
        // if node index coord is bigger than root
        if(node.getCoord(index) > _root.getCoord(index)) {
            // if _root right is null return error message else go right
            if(_root.right == null) {
                System.out.println("the entered coordinate is not in the tree. insert and try again");
                return null;
            } else {
                index = (index + 1) % dimensions;
                return findNode(node, _root.right, index);
            }
        } else {
            // if _root left is null return error message else go left
            if(_root.left == null) {
                System.out.println("the entered coordinate is not in the tree. insert and try again");
                return null;
            } else {
                index = (index + 1) % dimensions;
                return findNode(node, _root.left, index);
            }
        }
    }


    public Node findNearest(Node target) {
        if (root == null ) {
            throw new IllegalStateException("tree is empty!");
        }
        nearest = null;
        //target.printNode();
        // init nearest distance with dist to parent
        nearestDistance = target.distanceTo(target.parent);
        nearest = target.parent;
        // check distance to parent, left, right
        if(target.left != null) {
           nearest = target.left;
        } 
        if(target.right != null) {
            target.right.printNode();
            nearest = target.right;
        }
        return nearest;
    }
    
    // print function
    public void printKdTree() {
        System.out.println("----------------------------- print tree -----------------------------");
        List<Node> nodes = new ArrayList<Node>();
        System.out.print("0 ");
        for(int k = 0; k < root.getDepth(); k++) {
            System.out.print("      ");
        }
        root.printNode();
        System.out.println();
        if(root.left == null && root.right == null) {return;}
        nodes.add(root.left);
        nodes.add(root.right);
        for(int i = 0; i < root.getDepth(); i++) {
            for(int l = 0; l < 20/(i+1); l++) {
                System.out.print(" ");
            }
            for(int j = 0; j <= i; j++) {
                System.out.print(" /   \\   ");
            }
            System.out.println();
            System.out.print(i+1 + "   ");
            List<Node> nodesNext = new ArrayList<Node>();
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
}
