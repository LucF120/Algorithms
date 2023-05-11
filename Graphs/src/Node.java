import java.util.ArrayList;
import java.util.List;

class Node {
  int num;
  int height;
  int excessFlow;
  List<Edge> edges;

  Node(int num, int height, int excessFlow) {
    this.num = num;
    this.height = height;
    this.excessFlow = excessFlow;
    edges = new ArrayList<>();
  }

  void addEdge(Node dest, int flow, int capacity) {
    Edge edge = new Edge(this, dest, flow, capacity);
    edges.add(edge);
  }

}