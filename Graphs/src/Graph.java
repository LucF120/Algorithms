import java.util.List;
import java.util.ArrayList;

public class Graph {

  List<Node> nodes;

  public Graph(int numNodes) {
    nodes = new ArrayList<>();

    for (int i = 0; i < numNodes; i++) {
      nodes.add(new Node(i, 0, 0));
    }
  }

  public void addEdge(int source, int dest, int capacity) {
    nodes.get(source).addEdge(nodes.get(dest), 0, capacity);
  }

  public int getMaxFlow() {
    initializePreflow(nodes.get(0));
    // While there are active nodes, push or relabel
    Node activeNode = getActiveNode();
    while (activeNode != null) {
      if (!push(activeNode)) {
        relabel(activeNode);
      }
      activeNode = getActiveNode();
    }

    return nodes.get(nodes.size() - 1).excessFlow;
  }

  private void initializePreflow(Node s) {
    s.height = nodes.size();

    for (Edge e : s.edges) {
      e.flow = e.capacity;
      e.dest.excessFlow += e.flow;
      e.dest.addEdge(s, -e.flow, 0); // Residual backwards edge
    }
  }

  private boolean push(Node n) {
    for (Edge e : n.edges) {
      if ((n.height > e.dest.height) && (e.flow != e.capacity)) {
        int flow = Math.min(e.capacity - e.flow, n.excessFlow);
        n.excessFlow -= flow;
        e.dest.excessFlow += flow;
        e.flow += flow;
        updateReverseEdge(e, flow);
        return true;
      }
    }
    return false;
  }

  private void relabel(Node n) {
    int minAdjHeight = Integer.MAX_VALUE;
    for (Edge e : n.edges) {
      if ((e.flow != e.capacity) && (e.dest.height < minAdjHeight)) {
        minAdjHeight = e.dest.height;
        n.height = minAdjHeight + 1;
      }
    }
  }

  private Node getActiveNode() {
    for (int i = 1; i < nodes.size() - 1; i++) {
      if (nodes.get(i).excessFlow > 0) {
        return nodes.get(i);
      }
    }
    return null;
  }

  private void updateReverseEdge(Edge edge, int flow) {
    for (Edge e : edge.dest.edges) {
      if (e.dest.equals(edge.source)) {
        e.flow -= flow;
        return;
      }
    }

    edge.dest.addEdge(edge.source, -flow, 0);
  }

  public void printGraph() {
    for (int i=0 ; i<nodes.size() ; i++) {
      for (Edge e : nodes.get(i).edges) {
        System.out.println(e.source.num + " " + e.dest.num + " " + e.capacity);
      }
    }
  }
}