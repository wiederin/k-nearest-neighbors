import java.util.Comparator;

// NodeComparator compares node coord based on index
public class NodeComparator implements Comparator<Node> {
    // member value
    private int index;

    // constructor
    public NodeComparator(int index_) {
        index = index_;
    }
    
    // compares coordinates based on index. 
    // returns -1 if n1 value is smaller 1 if larger
    public int compare(Node n1, Node n2) {
        return Double.compare(n1.getCoord(index), n2.getCoord(index));
    }

}