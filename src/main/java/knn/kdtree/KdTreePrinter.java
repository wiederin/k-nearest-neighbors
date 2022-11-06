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
    public String bufLeft; // only spaces
    public String bufRight;
    public String bufSum;
    public int rootStringLen;

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
        rootStringLen = root.stringNode().length();
        String space = new String(new char[rootStringLen + 2]).replace("\0", " ");
        bufRight = "│" + space;
        bufLeft = new String(new char[rootStringLen + 3]).replace("\0", " ");
        bufSum = "";
        // create stack of left nodes
        Stack<Node> leftNodes = new Stack<Node>();
        // create buffers for clean output
        String buf = new String(new char[root.stringNode().length() - 3]).replace("\0", "─");
        // call recursive print function
        leftNodes = recPrintKdTree(outputStrings, root, buf, leftNodes);
        
        while(!leftNodes.empty()) {
            Node left = leftNodes.pop();
            if(left.parent.isRoot()) {
                //bufSum = "";
            } else {
                //bufSum = bufSum + bufLeft;
            }
            outputStrings.add(createBuf(left, bufLeft) + "└─ l " + buf +  left.stringNode());
            recPrintKdTree(outputStrings, left, buf, leftNodes);
            
        }
        for(String string: outputStrings) {
            System.out.println(string);
        }


    }

    public Stack<Node> recPrintKdTree(LinkedList<String> outputStrings, Node node, String buf, Stack<Node> leftNodes) {
        if(node.hasRight()){
            Node right = node.right;
            if(node.hasLeft()) {
                outputStrings.add(createBuf(right, bufRight) + "├─ r " + buf + right.stringNode());
                leftNodes.add(node.left);
            } else {
                outputStrings.add(createBuf(right, bufRight) + "└─ r " + buf + right.stringNode());
            }
            return recPrintKdTree(outputStrings, right, buf, leftNodes);
        }
        if (node.hasLeft()) {
            leftNodes.add(node.left);
        }
        return leftNodes;
    }

    // function that creates a buffer according to depth
    public String createBuf(Node node, String buf) {
        // return current buf
        if(node.parent.isRoot() && !buf.startsWith("│")) {
            bufSum = ""; 
        }
        String curBuf = bufSum;
        // update buf for next
        if(node.isLeaf()) {
            // if node is left
            if(!buf.startsWith("│") && !node.parent.isRoot()) {
                Node cur = node.parent.parent;
                Node mid = node.parent;
                int cutCount = 1;
                boolean goUp = true;
                while(goUp){
                    //cur = cur.parent;
                    if(cur.hasLeft()){
                        if(cur.left.stringNode().contentEquals(mid.stringNode()) && !cur.isRoot()) {
                            mid = cur;
                            cur = cur.parent;
                            cutCount++;
                        } else {
                            goUp = false;
                        }
                    } else if(!cur.isRoot()) {
                        cur = cur.parent;
                        cutCount++;
                    }
                }
                bufSum = bufSum.substring(0, bufSum.length() - (cutCount * (rootStringLen + 3)));
            } else if(!node.parent.hasLeft() && !node.parent.isRoot()) {
                int cutCount = 1;
                Node cur = node.parent.parent;
                Node mid = node.parent;
                boolean goUp = true;
                while(goUp){
                    //cur = cur.parent;
                    if(cur.hasLeft()){
                        if(cur.left.stringNode().contentEquals(mid.stringNode()) && !cur.isRoot()) {
                            mid = cur;
                            cur = cur.parent;
                            cutCount++;
                        } else {
                            goUp = false;
                        }
                    } else if(!cur.isRoot()){
                        cur = cur.parent;
                        cutCount++;
                    }
                }
                bufSum = bufSum.substring(0, bufSum.length() - (cutCount * (rootStringLen + 3)));
            }
        } else {
            bufSum = bufSum + buf;
        }
        return curBuf;
    }
    
}
