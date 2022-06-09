import java.io. * ;
import java.util.Scanner;

public class KdTreeBuilder {
    public static void main(String[] args) {

        // options output
        System.out.println("---------------------------- KDTree Builder --------------------------");
        //System.out.println("Options: ");
        //System.out.println("0 - Build from CSV");
        //System.out.print("Enter number of option to select: ");

        // option input
        //int option = Integer.parseInt(System.console().readLine());
        int option = 0;
        if(option == 0) {
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
                System.out.print("Enter the number of option to select: ");

                // option input
                int fileIndex = Integer.parseInt(System.console().readLine());
                String filename = files[fileIndex];
                // create scanner
                Scanner scan = new Scanner(new File("csv/"+filename));
                // set new line as delimiter
                scan.useDelimiter("\n");
                // parse file
                while (scan.hasNext()) {
                    String coordsString = scan.next();
                    System.out.println(coordsString);
                    KdNode node = new KdNode(coordsString);
                    //System.out.println(node.getCoords());
                    double[] coords = node.getCoords();
                    for (double coord : coords) {
                        System.out.println(coord);
                    }
                    
                }
                // close scan
                scan.close();

            } catch (FileNotFoundException ex) {
                // message in case file is not found
                System.out.println("CSV file not found");
            }

        }
    }
}
