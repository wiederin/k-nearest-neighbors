package knn.kdtree;

import java.util.LinkedList;

public class TreePrintFrame {
    /*
     * Class to build frame of tree to print
     */
    public LinkedList<String> header;
    public LinkedList<String> footer;
    public String borderLeft = "│┆";
    public String borderRight = "┆│";

    public TreePrintFrame() {
        String topBar =       "┌──────────┐";
        String dottedTopBar = "│╭┄┄┄┄┄┄┄┄╮│";
        String dottedBotBar = "│╰┄┄┄┄┄┄┄┄╯│";
        String botBar =       "└──────────┘";
    }
}
