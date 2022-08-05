package src.test.java.kdtree.tree;

import src.main.java.kdtree.csv.CsvFileLoader;
import src.main.java.kdtree.tree.node.Node;
import src.main.java.kdtree.tree.KdTree;
import static org.junit.Assert.*;

import org.junit.Test;

public class KdTreeTest {
    @Test
    public void kdTreeTest() {
        // init csvFileLoader
        CsvFileLoader csvFileLoader = new CsvFileLoader();
        // load 2Dsample.csv
        csvFileLoader.load("bin/csv/2Dsample.csv");
        // test constructor with nodes
        // create tree from csv file
        KdTree tree = new KdTree(csvFileLoader.nodes);
        // assert constuctor worked
        assertEquals("constructor given nodes does not set dimension", tree.dimensions, 2);
        assertTrue("constructor given nodes does not set root", tree.root!=null);
        assertTrue("constructor given nodes does not set nodesList", tree.nodesList!=null);
        // test constructor with root
        Node node = new Node("5.0,5.0");
        KdTree tree1 = new KdTree(node);
        assertEquals("constructor given root does not set root", tree1.root, node);
        // test insertNode
        tree.insertNode("6.0,6.0");
        // test findNode
        Node testFindSuccess = tree.findNode("10.0,19.0");
        Node testFindFailure = tree.findNode("4,5");
        // assert find worked
        assertTrue("findNode does not find node in tree", testFindSuccess!=null);
        assertTrue("findNode returns node despite target not in tree", testFindFailure==null);
        // test findNearest
        Node testFindNearest = tree.findNearest(testFindSuccess);
        assertTrue("findNearest returns wrong node or null", testFindNearest.getCoord(0)==6);
        // test printKdTree
        System.out.println("----------------------------- test print -----------------------------");
        tree.printKdTree();
        System.out.println("----------------------------- test print -----------------------------");
    }
}
