package knn.kdtree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import javafx.util.Pair;

public class KdTreePrinter {

    public KdTreePrinter() {}

    public void printKdTree(KdTree tree) {
        // get root
        Node root = tree.root;
        System.out.println(root.stringNode());
        Stack<Node> leftNodes = new Stack<Node>();
        String buf = new String(new char[root.stringNode().length()-3]).replace("\0", "─");
        String buf1 = "";
        leftNodes = recPrintKdTree(root, buf, buf1, leftNodes);

        while(!leftNodes.empty()) {
            Node left = leftNodes.pop();
            System.out.println(buf1 + "└─ l " + buf +  left.stringNode());
            leftNodes.add(0, recPrintKdTree(left, buf, buf1+"     ", leftNodes));
        }

    }

    public Stack<Node> recPrintKdTree(Node node, String buf, String buf1, Stack<Node> leftNodes) {
        //System.out.println("buf " + buf + " end buf");
        if(node.hasRight()){
            Node right = node.right;
            if(node.hasLeft()){
                leftNodes.add(node.left);
                System.out.println(buf1 + "├─ r " + buf + right.stringNode());
            } else {
                System.out.println(buf1 + "└─ r " + buf + right.stringNode());
            }
            return recPrintKdTree(right, buf, "│          ", leftNodes);
        } else if (node.hasLeft()) {
            leftNodes.add(node.left);
        }
        //return new Pair<Stack<Node>, String>(leftNodes, buf1);
        return leftNodes;
    }
    
}
