public class NodeComparatorTest {
    public static void main(String[] args) {
        int passed = 0;
        NodeComparator nc0 = new NodeComparator(0);
        NodeComparator nc1 = new NodeComparator(1);
        Node n1 = new Node("10,100,100");
        Node n2 = new Node("15,15,15");
        if(nc0.compare(n1, n2) == -1){
            System.out.println("compare function returns expected value when n1 value is smaller");
            ++passed;
        } 
        if(nc1.compare(n1, n2) == 1){
            System.out.println("compare function returns expected value when n1 value is greater");
            ++passed;
        } 
        if(nc1.compare(n1, n2) != 1 && nc1.compare(n1, n2) != -1) {
            System.out.println("compare function returns unexpected value " + nc1.compare(n1, n2));
        } else {
            ++passed;
        }
        System.out.println("passed tests: " + passed + " out of 3");
    }
}
