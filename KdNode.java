public class KdNode {

    // member variables
    public KdNode left;
    public KdNode right;
    public double[] coords;

    // constructor
    public KdNode(KdNode left, KdNode right, double[] coords){
        left = left;
        right = right;
        coords = coords;
    }

    // getters
    public KdNode getLeft() {
        return left;
    }

    public KdNode getRight() {
        return right;
    }

    public double[] getCoords() {
        return coords;
    }


}
