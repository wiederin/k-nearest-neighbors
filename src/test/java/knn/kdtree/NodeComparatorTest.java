package knn.kdtree;
import static org.junit.Assert.*;
import org.junit.Test;
/*
 * test class for NodeComparator
 */
public class NodeComparatorTest {
    @Test
    public void nodeComparatorTest() {
        // init NodeComparators
        NodeComparator nc0 = new NodeComparator(0);
        NodeComparator nc1 = new NodeComparator(1);
        // init nodes
        Node n1 = new Node("10,100,100");
        Node n2 = new Node("15,15,15");
        // assert when n1 of selected dimension is smaller function returns -1
        assertTrue("node comparison does not work when n1 is smaller",nc0.compare(n1, n2) == -1);
        // assert when n1 of selected dimension is larger function returns 1
        assertTrue("node comparison does not work when n1 is bigger", nc1.compare(n1, n2) == 1);
    }
}
