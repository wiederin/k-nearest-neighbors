package knn.kdtree;
 
import java.io. * ;

import knn.csv.CsvFileLoader;

public class KdTreeBuilder {

    // member variables
    protected String[] files = null;
    protected static KdTreeBuilder instance = null;
    protected KdTree tree;

    
    // empty constructor
    protected KdTreeBuilder() {

    }

    // main method
    public static void main(String[] args) {
        KdTreeBuilder kdTreeBuilder = newKdTreeBuilder();
        try {
            kdTreeBuilder.run();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            System.exit(1);
        }
        System.exit(0);
    }

    // run method 
    protected void run() throws Exception {
        // terminal output
        System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
        System.out.println("┃                             KDTree Builder                             ┃");
        System.out.println("┃╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳   running...   ╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳╳┃");
        System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
        boolean kill = false;
        // infinite while loop
        while(!kill){
            kill = buildFromSelection();
            if(!kill){
                kill = actionFromSelection();
            }
        }
    }

    /*
    * user interface to build tree 
     * returns true if kill is selected
     */
    protected boolean buildFromSelection() {
        System.out.println("┌────────────────────────────────────────────────────────────────────────┐");
        System.out.println("│Options:                                                                │");
        // find csv files in folder
        File csvFolder = new File("csv");
        // set files to found file list
        files = csvFolder.list();
        // display options
        int i = 0;
        for (String file : files) {
            // print index and filename
            System.out.print("│" + i + " - " + file);
            // print buffer for box border
            for (int j = 0; j < 68 - file.length(); j++){
                System.out.print(" ");
            }
            // print box border
            System.out.println("│");
            i++;
        }
        // kill option
        System.out.println("│-1 - kill                                                               │");
        System.out.println("└────────────────────────────────────────────────────────────────────────┘");
        // select
        System.out.print("Enter the number of option to select: ");
        // option input
        int fileIndex = Integer.parseInt(System.console().readLine());
        // if kill
        if(fileIndex==-1){return true;}

        // get filename
        String filename = files[fileIndex];
        // load csv file
        CsvFileLoader csvFileLoader = new CsvFileLoader();
        csvFileLoader.load("csv/"+filename);
        // create tree
        tree = new KdTree(csvFileLoader.nodes);
        return false;
    }

    /*
     * user interface to work with tree 
     * returns true if kill is selected
     */
    protected boolean actionFromSelection() {
        boolean buildNew = false;
        while(!buildNew) {
            // print options
            System.out.println("┌────────────────────────────────────────────────────────────────────────┐");
            System.out.println("│Options:                                                                │");
            System.out.println("│0 - print tree                                                          │");
            System.out.println("│1 - insert node                                                         │");
            System.out.println("│2 - find nearest                                                        │");
            System.out.println("│3 - build new tree                                                      │");
            System.out.println("│-1 - kill                                                               │");
            System.out.println("└────────────────────────────────────────────────────────────────────────┘");
            // select
            System.out.print("Enter the number of option to select: ");
            // read input
            int action = Integer.parseInt(System.console().readLine());
            if(action == 0) {
                // print tree
                KdTreePrinter printer = new KdTreePrinter();
                printer.printKdTree(tree);
                //tree.printKdTree();
            } else if(action == 1) {
                // insert coords
                System.out.print("Enter the coordinates to insert: ");
                // read input
                String coords = System.console().readLine();
                // insert
                tree.insertNode(coords);
            } else if(action == 2) {
                // find nearest
                // get coords
                System.out.print("Enter the coordinates to find nearest for: ");
                // read input
                String coords = System.console().readLine();
                // check input
                if(tree.findNode(coords) != null){
                    // find nearest
                    tree.findNearest(tree.findNode(coords)).printNode();
                    System.out.println();
                }
                
            } else if(action == 3) {
                 // build new tree
                buildNew = true;
                
            } else if(action == -1) {
                // kill program
                return true;
            } else {
                // invalid input
                System.out.println("Invalid option selected. Please select again");
            }
        }
        return false;
    }

    
    
    protected void logException() { }
    
    private static KdTreeBuilder newKdTreeBuilder() {
        return instance == null ? new KdTreeBuilder() : instance;
    }
}
