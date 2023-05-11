class Edge {
  Node source;
  Node dest;
  int flow;
  int capacity;

  Edge(Node source, Node dest, int flow, int capacity) {
    this.source = source;
    this.dest = dest;
    this.flow = flow;
    this.capacity = capacity;
  }

}