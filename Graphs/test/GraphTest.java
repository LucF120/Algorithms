import org.junit.Test;

import static org.junit.Assert.*;

public class GraphTest {


  @Test
  public void getMaxFlow() {
//    Graph f = new Graph(6);
//    f.addEdge(0, 1, 16);
//    f.addEdge(0, 2, 13);
//    f.addEdge(2, 1, 4);
//    f.addEdge(1, 3, 12);
//    f.addEdge(3, 2, 9);
//    f.addEdge(2, 4, 14);
//    f.addEdge(4, 3, 7);
//    f.addEdge(4, 5, 4);
//    f.addEdge(3, 5, 20);
//
//    assertEquals(23, f.getMaxFlow());

    Graph f = new Graph(4);
    f.addEdge(0, 1, 1000000);
    f.addEdge(0, 2, 1000000);
    f.addEdge(1, 2, 1);
    f.addEdge(1, 3, 1000000);
    f.addEdge(2, 3, 1000000);

    assertEquals(0, f.getMaxFlow());











  }
}