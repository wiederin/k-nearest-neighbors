package knn.kdtree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.AbstractMap.SimpleEntry;

/* KdTreePrinter 
 *
 * prints kdtree on command line
 *
 * */
public class KdTreePrinter {
    // empty constructor
    public KdTreePrinter() {}
    
    // main print function taking a KdTree as input
    public void printKdTree(KdTree tree) {
        // create array of output strings
        LinkedList<String> outputStrings = new LinkedList<String>();
        // get root of input tree
        Node root = tree.root;
        // add root to outputStrings
        outputStrings.add(root.stringNode());
        // create stack of left nodes
        Stack<Node> leftNodes = new Stack<Node>();
        // create buffers for clean output
        String buf = new String(new char[root.stringNode().length() - 3]).replace("\0", "─");
        String space = new String(new char[root.stringNode().length() + 2]).replace("\0", " ");
        String buf1 = "│" + space;
        // call recursive print function
        leftNodes = recPrintKdTree(outputStrings, root, buf, buf1, leftNodes);
        
        while(!leftNodes.empty()) {
            Node left = leftNodes.pop();
            if(leftNodes.size() == 0 && left.parent == root) {
                buf1 = ""; 
            } else if (leftNodes.size() == 0) {
                buf1 = " " + space;
            } else {
                buf1 = buf1.substring(0, root.stringNode().length()+3);
            }
            //outputStrings.add("parent = " + left.parent.stringNode());
            outputStrings.add(buf1 + "└─ l " + buf +  left.stringNode());
            Stack<Node> newLeftNodes = recPrintKdTree(outputStrings, left, buf, buf1, leftNodes);
            //for(Node node: newLeftNodes) {
            //    leftNodes.add(node);
            //}
            
        }
        for(String string: outputStrings) {
            System.out.println(string);
        }


    }

    public Stack<Node> recPrintKdTree(LinkedList<String> outputStrings, Node node, String buf, String buf1, Stack<Node> leftNodes) {
        //System.out.println("buf " + buf + " end buf");
        if(node.hasRight()){
            Node right = node.right;
            if(node.hasLeft()){
                if(node.isRoot()){
                    outputStrings.add("parent: " + right.parent.stringNode());
                    outputStrings.add("├─ r " + buf + right.stringNode());
                } else {
                    outputStrings.add("parent: " + right.parent.stringNode());
                    outputStrings.add(createBuf(right) + "├─ r " + buf + right.stringNode());
                }
                buf1 = buf1 + buf1;
                leftNodes.add(node.left);
            } else {
                outputStrings.add(createBuf(right) + "└─ r " + buf + right.stringNode());
            }
            return recPrintKdTree(outputStrings, right, buf, buf1, leftNodes);
        } else if (node.hasLeft()) {
            leftNodes.add(node.left);
        }
        //return new Pair<Stack<Node>, String>(leftNodes, buf1);
        return leftNodes;
    }

    // function that creates a buffer according to depth
    public String createBuf(Node node) {
        // buffer base
        String space = new String(new char[node.getRoot().stringNode().length() + 2]).replace("\0", " ");
        String base = "│" + space;
        return base.repeat(node.depth());
    }
}
