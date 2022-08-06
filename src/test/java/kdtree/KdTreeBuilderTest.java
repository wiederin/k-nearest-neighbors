package src.test.java.kdtree;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import src.main.java.kdtree.KdTreeBuilder;

public class KdTreeBuilderTest extends KdTreeBuilder {
    @After
    public void tearDown() throws Exception {
        instance = null;
    }

    @Rule
    public final ExpectedSystemExit exit = ExpectedSystemExit.none();

    @Test
    public void mainTest() {
        instance = new CrashAndBurn();
        String [] args = { "0", "-1", "-1" };
        exit.expectSystemExitWithStatus(1);
        main(args);
    }
    
    private static class CrashAndBurn extends KdTreeBuilder {
        @Override
        protected void run() throws Exception { 
            throw new Exception();
        }
    }
}
