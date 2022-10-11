package knn.kdtree;

import org.junit.Test;

import knn.csv.CsvFileLoader;

public class KdTreePrinterTest {        
    @Test
    public void kdTreePrinterTest() {
        // init csvFileLoader
        CsvFileLoader csvFileLoader = new CsvFileLoader();
        // load 2Dsample.csv
        csvFileLoader.load("csv/2Dsample.csv");
        // create tree from csv file
        KdTree tree = new KdTree(csvFileLoader.nodes);
        KdTreePrinter printer = new KdTreePrinter();
        printer.printKdTree(tree);
    }
}
