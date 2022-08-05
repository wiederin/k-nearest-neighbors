package src.main.java;
import java.io. * ;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.main.java.csv.CsvFileLoader;
import src.main.java.kdtree.KdTree;
import src.main.java.kdtree.node.Node;

public class KdTreeBuilder {
    public static void main(String[] args) {
        // options output
        System.out.println("---------------------------- KDTree Builder --------------------------");
        System.out.println("Options: ");
        // find csv files in folder
        File csvFolder = new File("csv");
        String[] files = csvFolder.list();
        // display options
        int i = 0;
            for (String file : files) {
                System.out.println(i + " - " + file);
            i++;
        }
        // select
        System.out.print("Enter the number of option to select: ");
        // option input
        int fileIndex = Integer.parseInt(System.console().readLine());
        //int fileIndex = 0;
        String filename = files[fileIndex];
        CsvFileLoader csvFileLoader = new CsvFileLoader(filename);
        KdTree tree = new KdTree(csvFileLoader.nodes);
        tree.printKdTree();
        System.out.println("insert 5,90");
        tree.insertNode("5,90");
        tree.printKdTree();
        Node testFindSuccess = tree.findNode("10.0,19.0");
        testFindSuccess.printNode();
        Node testFindFailure = tree.findNode("4,5");
    }
}
