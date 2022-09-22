package knn.kdtree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class KdTreePrinter {

    public KdTreePrinter() {

    }

    public void printKdTree(KdTree tree) {
        /* 
            ┌──────────┐ = (┌), (─)*10, (┐)
            │╭┄┄┄┄┄┄┄┄╮│ = (│), (╭), (┄)*8, (╮), (|)
            │┆x ┄ 1   ┆│ = (│), (┆), (x ), (┄), (1), ( ), (┆), (|)   -> 1 max size 
            │╰┄┄┄┄┄┄┄┄╯│ = (│), (╰), (┄)*8, (╯), (|)
            └──────────┘ = (└), (─)*10, (┘)
        */
        Node root = tree.root;
        String one = root.stringNode();
        int add = 0;
        if(one.length() > 3) {
            add = one.length() - 4;
        }
        int bufSize = 10 + add;
        int buf1Size = 8 + add;
        int buf2Size = 1;
        String borderLeft = "│┆";
        String borderRight = "┆│";

        List<String> outputStrings = new LinkedList<String>();
        Collection<String> leafStrings = new ArrayList<String>();
        // if root is not leaf
        // print leafs
        List<Node> leafs = new LinkedList<Node>();
        if(!root.isLeaf()) {
            if(root.hasLeft()){leafs.add(root.left);}
            if(root.hasRight()){leafs.add(root.right);}
            for(int i = 0; i < root.getDepth(); i++) {
                List<Node> leafsNext = new LinkedList<Node>();
                String buf3 = new String(new char[root.getDepth()]).replace("\0", "┄");
                String leafLine = borderLeft + iToDimension(i, tree.dimensions) + " " + buf3 + " ";
                buf2Size++;
                for(Node leaf: leafs) {

                    leafStrings.add(borderLeft + "     ┌─┴─┐ " + borderRight);
                    if(leaf.hasLeft()){leafsNext.add(leaf.left);}
                    if(leaf.hasRight()){leafsNext.add(leaf.right);}
                    leafLine = leafLine + leaf.stringNode() + " ";
                }
                leafLine = leafLine + borderRight;
                leafStrings.add(leafLine);
                leafs = leafsNext;
            }

        }

        // open box
        String buf = new String(new char[bufSize]).replace("\0", "─");
        String topbar = ("┌" + buf + "┐");
        outputStrings.add(topbar);
        // open dotted box
        String buf1 = new String(new char[buf1Size]).replace("\0", "┄");
        String dottedTopBar = "│╭" + buf1 + "╮│";
        outputStrings.add(dottedTopBar);
        // root
        String buf2 = new String(new char[buf2Size]).replace("\0", "┄");
        String rootLine = borderLeft + "0" + " " + buf2 + " " + one + borderRight;
        outputStrings.add(rootLine);
        // leafs
        outputStrings.addAll(leafStrings);
        
        // close dotted box
        String dottedBottomBar = "│╰" + buf1 + "╯│";
        outputStrings.add(dottedBottomBar);
        // close box
        String bottombar = ("└" + buf + "┘");
        outputStrings.add(bottombar);

        outputStrings = reformat(outputStrings);
        // print tree
        for(String outputString: outputStrings) {
            System.out.println(outputString);
        }
    }

    // function that returns dimension from an int
    public String iToDimension(int i, int dimensions) {
        String dimension = "";
        if(i%dimensions==0){
            dimension = "1";
        }
        return dimension;
    }

    public List<String> reformat(List<String> outputStrings) {
        List<String> reformatted = new LinkedList<String> ();
        // find longest
        int longest = 0;
        for(String outputString: outputStrings){
            if(outputString.length() > longest) {
                longest = outputString.length();
            }
        }
        // reformat all smaller lines
        for(int i = 0; i < outputStrings.size(); i++){
            String line = outputStrings.get(i);
            if(line.length() < longest) {
                line = reformatLine(i, line, longest);
                reformatted.add(line);
            } else {
                reformatted.add(line);
            }
        }
        return reformatted;
    }

    public String reformatLine(int i, String line, int longest){
        int len = longest - line.length();
        int offset = 0;
        String buf = "";
        String end = "";
        if(i == 0) {
            offset = 1;
            buf = "─";
            end = "┐";
        } else if(i == 1) {
            offset = 2;
            buf = "┄";
            end = "╮│";
        }
        String add = new String(new char[len]).replace("\0", buf);
        return line.substring(0, line.length()-offset) + add + end; 
    }
    
}
