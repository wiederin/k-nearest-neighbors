package src.main.java.kdtree;
import java.io. * ;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.main.java.csv.CsvFileLoader;

public class KdTreeTest {
    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<Node>();
        CsvFileLoader csvFileLoader = new CsvFileLoader("csv/2DSample.csv");
        KdTree tree = new KdTree(csvFileLoader.nodes);
        tree.printKdTree();
        System.out.println("insert 5,90");
        tree.insertNode("5,90");
        tree.printKdTree();
        Node testFindSuccess = tree.findNode("10.0,19.0");
        testFindSuccess.printNode();
        System.out.print("\n");
        Node testFindFailure = tree.findNode("4,5");
        Node testFindNearest = tree.findNearest(testFindSuccess);
        testFindNearest.printNode();
        //testFindNearest.parent.printNode();
    }
}
