package src.main.java.csv;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import src.main.java.kdtree.node.Node;

public class CsvFileLoader{
    // member variables
    public List<Node> nodes = new ArrayList<Node>();
    // constructor
    public CsvFileLoader(String filename) {
        try {
            // create scanner
            Scanner scan = new Scanner(new File("csv/"+filename));
            // set new line as delimiter
            scan.useDelimiter("\n");
            // parse file
            int count = 0;
            while (scan.hasNext()) {
                // each line is coordinate string e.g. 0,0
                String coordsString = scan.next();
                // create node from coordinate
                Node node = new Node(coordsString);
                nodes.add(node);
                    
            }
            // close scan
            scan.close();

        } catch (FileNotFoundException ex) {
            // message in case file is not found
            System.out.println("CSV file not found");
        }
    }
}