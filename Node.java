public class Node {

    // member variables
    public Node left;
    public Node right;
    public double[] coords;
    //public double dimensions = coords.length;

    // constructor
    public Node(Node _left, Node _right, double[] _coords){
        left = _left;
        right = _right;
        coords = _coords;
    }

    public Node(String coordsString) {
        String[] coordStrings = coordsString.split(",");
        coords = new double[coordStrings.length];
        for (int i = 0; i < coordStrings.length; i++) {
            coords[i] = Double.parseDouble(coordStrings[i]);
        }
    }

    public double distance(Node node) {
        double dist = 0;
        for (int i = 0; i < coords.length; i++) {
            double distPart = coords[i] - node.coords[i];
            dist += distPart * distPart;
        }
        return dist;
    }

    public boolean isLeaf() {
        if(left == null && right == null) {
            return true;
        }
        return false;
    }

    public boolean hasLeft() {
        if(left == null) {
            return false;
        }
        return true;
    }

    public boolean hasRight() {
        if (right == null) {
            return false;
        }
        return true;
    }

    public int getHeight() {
        int height_left = 0;
        int height_right = 0;
        Node loc = this;
        while(loc.hasLeft()){
            loc = loc.getLeft();
            ++height_left;
        }
        loc = this;
        while(loc.hasRight()) {
            loc = loc.getRight();
            ++height_right;
        }
        if(height_left > height_right) {
            return height_left;
        } else {
            return height_right;
        }
    }    
    // print function

    public void printNode() {
        boolean comma = false;
        for(double coord : coords) {
            if(comma){System.out.print(",");}
            else {comma = true;}
            System.out.print(coord);
        }
        //System.out.println();
    }

    // getters & setters
    public Node getLeft() {
        return left;
    }

    public void setLeft(Node node) {
        left = node;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node node) {
        right = node;
    }


    public double[] getCoords() {
        return coords;
    }

    public double getCoord(int index) {
        return coords[index];
    }

/*
    public void setDimensions() {
        dimensions = coords.length;
    }
*/

}
