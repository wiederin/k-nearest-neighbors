public class KdNode {

    // member variables
    public KdNode left;
    public KdNode right;
    public double[] coords;
    //public double dimensions = coords.length;

    // constructor
    public KdNode(KdNode _left, KdNode _right, double[] _coords){
        left = _left;
        right = _right;
        coords = _coords;
    }

    public KdNode(String coordsString) {
        String[] coordStrings = coordsString.split(",");
        coords = new double[coordStrings.length];
        for (int i = 0; i < coordStrings.length; i++) {
            coords[i] = Double.parseDouble(coordStrings[i]);
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
    public KdNode getLeft() {
        return left;
    }

    public void setLeft(KdNode node) {
        left = node;
    }

    public KdNode getRight() {
        return right;
    }

    public void setRight(KdNode node) {
        right = node;
    }


    public double[] getCoords() {
        return coords;
    }

/*
    public void setDimensions() {
        dimensions = coords.length;
    }
*/

}
