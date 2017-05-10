package hex.genmodel.algos.tree;

import java.io.PrintStream;
import java.util.ArrayList;

/**
 * Graph for representing a GBM or DRF forest.
 * A graph contains subgraphs (trees).
 */
public class SharedTreeGraph {
  private ArrayList<SharedTreeSubgraph> subgraphArray = new ArrayList<>();

  /**
   * Make a new forest.
   */
  SharedTreeGraph() {
    subgraphArray = new ArrayList<>();
  }

  /**
   * Make a new tree.
   * @param name Tree name.
   * @return The new tree.
   */
  SharedTreeSubgraph makeSubgraph(String name) {
    SharedTreeSubgraph sg = new SharedTreeSubgraph(subgraphArray.size(), name);
    subgraphArray.add(sg);
    return sg;
  }

  /**
   * Debug printout of graph structure.
   * For developer use only.
   */
  public void print() {
    System.out.println("------------------------------------------------------------");
    System.out.println("Graph");
    for (SharedTreeSubgraph sg : subgraphArray) {
      sg.print();
    }
  }

  /**
   * Print graph output in a format readable by dot (graphviz).
   * @param os Stream to write the output to
   * @param maxLevelsToPrintPerEdge Limit the number of individual categorical level names printed per edge
   * @param detail include addtional node detail information
   * @param optionalTitle Optional title to override the default
   */
  public void printDot(PrintStream os, int maxLevelsToPrintPerEdge, boolean detail, String optionalTitle) {
    os.println("/*");
    os.println("Generated by:");
    os.println("    http://https://github.com/h2oai/h2o-3/tree/master/h2o-genmodel/src/main/java/hex/genmodel/tools/PrintMojo.java");
    os.println("*/");
    os.println("");
    os.println("/*");
    os.println("On a mac:");
    os.println("");
    os.println("$ brew install graphviz");
    os.println("$ dot -Tpng file.gv -o file.png");
    os.println("$ open file.png");
    os.println("*/");
    os.println("");
    os.println("digraph G {");
    for (SharedTreeSubgraph sg : subgraphArray) {
      sg.printDot(os, maxLevelsToPrintPerEdge, detail, optionalTitle);
    }
    os.println("");
    os.println("}");
    os.println("");
  }
}
