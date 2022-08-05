package src.main.java.kdtree.node;

public class Node {

    // member variables
    public Node left;
    public Node right;
    public Node parent = null;
    public double[] coords;

    // constructor with left, right and coords
    public Node(Node _left, Node _right, double[] _coords){
        left = _left;
        right = _right;
        coords = _coords;
    }

    // contructor with coordinate string
    public Node(String coordsString) {
        // convert string to double[]
        String[] coordStrings = coordsString.split(",");
        coords = new double[coordStrings.length];
        for (int i = 0; i < coordStrings.length; i++) {
            coords[i] = Double.parseDouble(coordStrings[i]);
        }
    }

    // function to get distance to input node
    public double distanceTo(Node node) {
        // init
        double distSquared = 0;
        // loop through coordinates
        for (int i = 0; i < coords.length; i++) {
            // compute and add (x2 - x1)^2 to dist
            distSquared += Math.pow(coords[i] - node.coords[i], 2);
        }
        // return sqrt of distSquared
        return Math.sqrt(distSquared);
    }

    // function to check if node is a leaf
    public boolean isLeaf() {
        // if both left and right are null return true else false
        if(left == null && right == null) {
            return true;
        }
        return false;
    }

    // function that returns true if node has left
    public boolean hasLeft() {
        if(left == null) {
            return false;
        }
        return true;
    }


    // function that returns true if node has right
    public boolean hasRight() {
        if (right == null) {
            return false;
        }
        return true;
    }

    // function that returns depth of node in tree
    public int getDepth() {
        // init
        int depth_left = 0;
        int depth_right = 0;
        Node loc = this;
        // traverse left
        while(loc.hasLeft()){
            loc = loc.left;
            ++depth_left;
        }
        // reset loc
        loc = this;
        // traverse right
        while(loc.hasRight()) {
            loc = loc.right;
            ++depth_right;
        }
        // return based on comparison of left and right
        return depth_left > depth_right ? depth_left : depth_right;
    }    

    // print node coordinates
    public void printNode() {
        // flag for comma
        boolean comma = false;
        // loop through coordinates
        for(double coord : coords) {
            // if first coord then no preceding comma
            if(comma){System.out.print(",");}
            else {comma = true;}
            // print coord with no newline
            System.out.print(coord);
        }
    }

    // function to get individual coordinate
    public double getCoord(int index) {
        return coords[index];
    }

}
