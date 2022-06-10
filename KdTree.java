public class KdTree {
    
    // member variables
    public KdNode root;
    public int size;
    public int dimensions;

    // constructor
    public KdTree(KdNode _root, int _size) {
        root = _root;
        size = _size;
        dimensions = _root.getCoords().length;
    }

    public KdTree() {
        
    }

    // print function
    public void printKdTree() {
        System.out.println("-------------------- print tree --------------------");
        KdNode[] nodes = new KdNode[2];
        System.out.print("1      ");
        root.printNode();
        System.out.println();
        if(size == 0) {return;}
        nodes[0] = root.getLeft();
        nodes[1] = root.getRight();
        for (int i = 1; i < size + 1; i++) {
            int index = 0;
            KdNode[] nodesTemp = new KdNode[(i+1)*2];
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
            for(KdNode node: nodesTemp) {
                if(node != null){
                    node.printNode();
                } else {
                    System.out.print("-");
                }
            }
            System.out.println();
            nodes = nodesTemp.clone();
            

        }
    }

    // insert function
    public void insertNode(KdNode node, int depth) {
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
                }*/

                //subTree.getRoot().getLeft().printNode();
                //depth += 2;
                //root.setRight(subTree.getRoot());

            }
        }
    }

    // getters & setters
    public KdNode getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }
}