import java.io. * ;
import java.util.Scanner;

public class KdTreeBuilder {
    public static void main(String[] args) {

        // options output
        System.out.println("---------------------------- KDTree Builder --------------------------");
        try {

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
            //System.out.print("Enter the number of option to select: ");

            // option input
            //int fileIndex = Integer.parseInt(System.console().readLine());
            int fileIndex = 0;
            String filename = files[fileIndex];
            // create scanner
            Scanner scan = new Scanner(new File("csv/"+filename));
            // set new line as delimiter
            scan.useDelimiter("\n");
            // parse file and build kdtree
            KdTree tree = new KdTree();
            int count = 0;
            while (scan.hasNext()) {
                // each line is coordinate string e.g. 0,0
                String coordsString = scan.next();
                // create node from coordinate
                KdNode node = new KdNode(coordsString);
                System.out.println("------------------- insert node -------------------");

                // build tree
                tree.insertNode(node, 0);
                
                System.out.println("-------------------- after add --------------------");
                System.out.println("tree after adding node " + count);
                System.out.println("tree size " + tree.getSize());
                tree.printKdTree();
                count++;
                System.out.println("---------------------------------------------------");
                    
            }
            // close scan
            scan.close();

        } catch (FileNotFoundException ex) {
            // message in case file is not found
            System.out.println("CSV file not found");
        }
    }
}
