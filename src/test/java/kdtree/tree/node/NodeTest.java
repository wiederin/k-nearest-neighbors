package src.test.java.kdtree.tree.node;
import src.main.java.kdtree.tree.node.Node;
import static org.junit.Assert.*;
import org.junit.Test;

/*
 * test class for Node
 */
public class NodeTest {
    @Test
    public void runTests() {
        // counter for passed tests
        int passed = 0;
        int failed = 0;
        // test constuctor #1
        assertTrue("constructor #1 does not work", leftRightCoordsConstructorTest());
        // test constructor #2
        assertTrue("constructor #2 does not work", coordStringConstructorTest());
        // test distanceTo
        assertTrue("distanceTo method does not work", distanceToTest(new Node("5.0,5.0"), new Node("10.0,10.0")));
        // test isLeaf
        assertTrue("isLeaf method does not work", isLeafTest());
        // test hasLeft
        assertTrue("hasLeft method does not work", hasLeftTest());
        // test hasRight
        assertTrue("hasRight method does not work", hasRightTest());
        // test getCoord
        assertTrue("getCoord method does not work", getCoordTest());
    }

    // test function for constructor with left, right, and coords
    public static boolean leftRightCoordsConstructorTest() {
        // create coords double[]
        double[] coords = new double[2];
        coords[0] = 5.0;
        coords[1] = 6.0;
        // create node
        Node node = new Node(null, null, coords);
        // check if coords is set
        if(node.left == null && node.right == null && node.coords == coords){
            // print success message and return true
            System.out.println("node constructor with left, right and double[] works");
            return true;
        }
        // print failure message and return false
        System.out.println("node constructor with left, right, and double[] does not work");
        return false;
    }

    // test function for constructor with coord string
    public static boolean coordStringConstructorTest() {
        // use constructor
        Node node = new Node("7.0,8.0");
        // check if coords correctly set
        if(node.coords[0] == 7.0 && node.coords[1] == 8.0) {
            // print success message and return true
            System.out.println("node constructor with string works");
            return true;
        }
        // print failure message and return false
        System.out.println("node constructor with string does not work");
        return false;
    }

    // test function for distanceTo
    public static boolean distanceToTest(Node n1, Node n2) {
        // if either node is null
        if(n1 == null || n2 == null) {
            // can't compute messages
            if(n1 == null){
                System.out.println("n1 node is null cannot compute distance");
            }
            if(n2 == null){
                System.out.println("n2 node is null cannot compute distance");
            }
            // return false
            return false;
        }
        // check correct distance
        if(n1.distanceTo(n2) == 7.0710678118654755){
            // print success message and return true
            System.out.println("distanceTo method works");
            return true;
        }
        // print failure message and return false
        System.out.println("distanceTo calculation is off. It gives: " + n1.distanceTo(n2) + " instead of 7.0710678118654755");
        return false;
    }

    // test function for isLeaf
    public static boolean isLeafTest() {
        // create a leaf node
        Node leaf = new Node("6.0,6.0");
        // check isLeaf 
        if(!leaf.isLeaf()){
            // print failure message and return false
            System.out.println("isLeaf returns false on leaf node");
            return false;
        }
        // create non-leaf nodes and test
        // non-leaf with left
        Node notLeafLeft = new Node("5.0,4.0");
        notLeafLeft.left = leaf;
        if(notLeafLeft.isLeaf()){
            // print failure message and return false
            System.out.println("isLeaf returns true on non-leaf node - with left");
            return false;
        }
        // non-leaf with right
        Node notLeafRight = new Node("4.0,4.0");
        notLeafRight.right = leaf;
        if(notLeafRight.isLeaf()){
            // print failure message and return false
            System.out.println("isLeaf returns true on non-leaf node - with right");
            return false;
        }
        // non-leaf with left and right
        double[] coords = new double[2];
        coords[0] = 5.0;
        coords[1] = 5.0;
        Node notLeafBoth = new Node(new Node("4.0,4.0"), new Node("6.0,6.0"), coords);
        if(notLeafBoth.isLeaf()){
            // print failure message and return false
            System.out.println("isLeaf returns true on non-leaf node - both left and right");
            return false;
        }
        // print success message and return true
        System.out.println("isLeaf method works");
        return true;
    }

    // test for hasLeft
    public static boolean hasLeftTest() {
        boolean passed = true;
        // create node with no left
        Node noLeft = new Node("4.0,5.0");
        // test hasLeft method
        if(noLeft.hasLeft()) {
            // failure
            System.out.println("hasLeft returns true on noLeft");
            passed = false;
        } else {
            // success
            System.out.println("hasLeft return false on noLeft");
        }
        // create node with left
        Node left = new Node("5.0,5.0");
        left.left = noLeft;
        // test hasLeft
        if(left.hasLeft()) {
            // success
            System.out.println("hasLeft retruns true on left");
        } else {
            // failure
            System.out.println("hasLeft retruns false on left");
            passed = false;
        }
        return passed;
    }

    // test for hasRight
    public static boolean hasRightTest() {
        boolean passed = true;
        // create node with no right
        Node noRight = new Node("4.0,5.0");
        // test hasRight method
        if(noRight.hasRight()) {
            // failure
            System.out.println("hasRight returns true on noRight");
            passed = false;
        } else {
            // success
            System.out.println("hasRight return false on noRight");
        }
        // create node with right
        Node right = new Node("5.0,5.0");
        right.right = noRight;
        // test hasRight
        if(right.hasRight()) {
            // success
            System.out.println("hasRight retruns true on right");
        } else {
            // failure
            System.out.println("hasRight retruns false on right");
            passed = false;
        }
        return passed;
    }

    // test for getDepth

    // test for getCoord
    public static boolean getCoordTest() {
        // create node
        Node node = new Node("5.0,5.0");
        // test getCoord return
        if(node.getCoord(0) == 5.0 && node.getCoord(1) == 5.0){
            System.out.println("getCoord method works");
            return true;
        } else {
            System.out.println("getCoord method does not work");
            return false;
        }
    }
}
