package src.test.java.kdtree.tree.node;
import src.main.java.kdtree.tree.node.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class NodeComparatorTest {
    @Test
    public void tests() {
        NodeComparator nc0 = new NodeComparator(0);
        NodeComparator nc1 = new NodeComparator(1);
        Node n1 = new Node("10,100,100");
        Node n2 = new Node("15,15,15");
        assertTrue("node comparison works when n1 is smaller",nc0.compare(n1, n2) == -1);
        assertTrue("node comparison works when n1 is bigger", nc1.compare(n1, n2) == 1);
    }
}
