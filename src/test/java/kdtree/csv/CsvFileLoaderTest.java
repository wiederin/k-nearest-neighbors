package src.test.java.kdtree.csv;
import org.junit.Test;
import static org.junit.Assert.*;

import src.main.java.kdtree.csv.CsvFileLoader;

public class CsvFileLoaderTest {
    @Test
    public void csvFileLoaderTest() {
        // init csvFileLoader
        CsvFileLoader csvFileLoader = new CsvFileLoader();
        // load existant file
        boolean loaded = csvFileLoader.load("bin/csv/2Dsample.csv");
        // assert loaded is true
        assertTrue("file is not found", loaded);
        // assert loaded data is in nodes
        assertTrue("load works", csvFileLoader.nodes.get(0).getCoord(0) == 300);
        // load non-existant file
        boolean nonExist = csvFileLoader.load("bin/csv/notfound.csv");
        // assert nonExist is false
        assertTrue("response if file is not found is wrong", !nonExist);
    }
}
