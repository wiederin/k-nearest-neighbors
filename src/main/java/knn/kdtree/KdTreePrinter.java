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

    // member variables
    public String base;

    // empty constructor
    public KdTreePrinter() {
    }
    
    // main print function taking a KdTree as input
    public void printKdTree(KdTree tree) {
        // create array of output strings
        LinkedList<String> outputStrings = new LinkedList<String>();
        // get root of input tree
        Node root = tree.root;
        // add root to outputStrings
        outputStrings.add(root.stringNode());
        // create buffer base from root
        String space = new String(new char[root.stringNode().length() + 2]).replace("\0", " ");
        this.base = "│" + space;
        // create stack of left nodes
        Stack<Node> leftNodes = new Stack<Node>();
        // create buffers for clean output
        String buf = new String(new char[root.stringNode().length() - 3]).replace("\0", "─");
        // call recursive print function
        leftNodes = recPrintKdTree(outputStrings, root, buf, leftNodes);
        
        while(!leftNodes.empty()) {
            Node left = leftNodes.pop();
            //outputStrings.add("parent = " + left.parent.stringNode());
            outputStrings.add(createBuf(left, leftNodes) + "└─ l " + buf +  left.stringNode());
            if(left.parent.isRoot()) {
                this.base =  new String(new char[root.stringNode().length() + 3]).replace("\0", " ");
            }
            recPrintKdTree(outputStrings, left, buf, leftNodes);
            
        }
        for(String string: outputStrings) {
            System.out.println(string);
        }


    }

    public Stack<Node> recPrintKdTree(LinkedList<String> outputStrings, Node node, String buf, Stack<Node> leftNodes) {
        //System.out.println("buf " + buf + " end buf");
        if(node.hasRight()){
            Node right = node.right;
            if(node.hasLeft()){
                if(node.isRoot()){
                    outputStrings.add("├─ r " + buf + right.stringNode());
                } else {
                    outputStrings.add(createBuf(right, leftNodes) + "├─ r " + buf + right.stringNode());
                }
                leftNodes.add(node.left);
            } else {
                outputStrings.add(createBuf(right, leftNodes) + "└─ r " + buf + right.stringNode());
            }
            return recPrintKdTree(outputStrings, right, buf, leftNodes);
        } else if (node.hasLeft()) {
            leftNodes.add(node.left);
        }
        //return new Pair<Stack<Node>, String>(leftNodes, buf1);
        return leftNodes;
    }

    // function that creates a buffer according to depth
    public String createBuf(Node node, Stack<Node> leftNodes) {
        return this.base.repeat(node.depth() - 1);
    }
}
