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
    /*old
    public KdTree(Node _root, int _size) {
        root = _root;
        size = _size;
        dimensions = _root.getCoords().length;
    }

    public KdTree() {
        
    }*/

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
        findNearestRec(root, target, 0);
        return nearest;
    }

    private void findNearestRec(Node root, Node target, int index) {
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
        findNearestRec(depth > 0 ? root.left : root.right, target, index);
        if (depth * depth >= nearestDistance) {
            return;
        }
        findNearestRec(depth > 0 ? root.right : root.left, target, index);
    }
    
    // print function
    public void printKdTree() {
        System.out.println("----------------------------- print tree -----------------------------");
        List<Node> nodes = new ArrayList();
        System.out.print("0      ");
        root.printNode();
        System.out.println();
        if(root.getLeft() == null && root.getRight() == null) {return;}
        nodes.add(root.getLeft());
        nodes.add(root.getRight());
        for(int i = 0; i < root.getHeight(); i++) {
            System.out.print(i+1 + " ");
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
        /*
        for (int i = 1; i < size + 1; i++) {
            int index = 0;
            Node[] nodesTemp = new Node[(i+1)*2];
            System.out.print((i)*2 + " ");
            for (int j = 0; j < nodes.length; j++) {

                //System.out.print(nodes.length);
                if(nodes[j] != null){
                    nodes[j].printNode();
                    System.out.println();
                    System.out.print("adding :");
                    if(nodes[j].getLeft()!=null){nodes[j].getLeft().printNode();}
                    if(nodes[j].getRight()!=null){nodes[j].getRight().printNode();}
                    nodesTemp[index] = nodes[j].getLeft();
                    if(nodesTemp[index]!=null){nodesTemp[index].printNode();}
                    nodesTemp[index++] = nodes[j].getRight();
                    if(nodesTemp[index+1]!=null){nodesTemp[index].printNode();}
                } else {
                    System.out.print(" - ");
                    nodesTemp[index] = null;
                    nodesTemp[index++] = null;
                }
            }
            System.out.println();
            System.out.print("nodes ");
            for(Node node: nodesTemp) {
                if(node != null){
                    node.printNode();
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
            nodes = nodesTemp.clone();
        }*/
    }
    /*
    // insert function
    public void insertNode(Node node, int depth) {
        // compare with root
        //KdNode rootTemp = root;
        if(root == null) {
            root = node;
            size = 0;
            dimensions = root.getCoords().length;
            return;
        }
        System.out.println("depth " + depth + " comparing " + root.getCoords()[depth%dimensions] + " to " + node.getCoords()[depth%dimensions]);
        if(root.getCoords()[depth%dimensions] > node.getCoords()[depth%dimensions]){
            // go left or set left to node if left is null
            if(root.getLeft()==null) {
                System.out.println("Set left of root");
                root.setLeft(node);
                size += 1;
                //printKdTree();
                //root.getLeft().printNode();
            } else {
                System.out.println("Go Left");
                KdTree subTree = new KdTree(root.getLeft(), size-1);
                
                subTree.insertNode(node, depth++);
                root.setLeft(subTree.getRoot());
            }
        } else {
            // go right or set right to node if right is null
            if(root.getRight()==null) {
                System.out.println("Set right of root");
                root.setRight(node);
                size += 1;
            } else {
                System.out.println("Go Right");
                KdTree subTree = new KdTree(root.getRight(), size-1);
                System.out.println("comparing " + root.getRight().getCoords()[depth+1%dimensions] + " to " + node.getCoords()[depth+1%dimensions]);
                System.out.print("insert ");
                node.printNode();
                subTree.insertNode(node, ++depth);
                //subTree.printKdTree();
                root.setRight(subTree.getRoot());
                size += depth;
                //printKdTree();
                /*root.getRight().printNode();
                if(root.getRight().getLeft() != null) {
                    root.getRight().getLeft().printNode();
                    printKdTree();
                }*/ /*

                //subTree.getRoot().getLeft().printNode();
                //depth += 2;
                //root.setRight(subTree.getRoot());

            }
        }
    } */

    // getters & setters
    public Node getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }
}