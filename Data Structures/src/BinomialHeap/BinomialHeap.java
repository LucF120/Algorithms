package BinomialHeap;


class BinomialHeap {

   HeapNode nodes;
   int size;

  public BinomialHeap()
  {
    this.nodes = null;
    this.size = 0;
  }

  public boolean isEmpty() { return nodes == null; }


  public int getSize() { return size; }


  public void makeEmpty()
  {
    nodes = null;
    size = 0;
  }


  public void insert(int value)
  {

    if (value > 0) {
      HeapNode temp
              = new HeapNode(value);
      if (nodes == null) {
        nodes = temp;
        size = 1;
      }
      else {
        union(temp);
        size++;
      }
    }
  }

  private void merge(HeapNode binHeap)
  {
    HeapNode temp1 = nodes, temp2 = binHeap;

    while ((temp1 != null) && (temp2 != null)) {

      if (temp1.degree == temp2.degree) {

        HeapNode tmp = temp2;
        temp2 = temp2.sibling;
        tmp.sibling = temp1.sibling;
        temp1.sibling = tmp;
        temp1 = tmp.sibling;
      }

      else {

        if (temp1.degree < temp2.degree) {

          if ((temp1.sibling == null)
                  || (temp1.sibling.degree
                  > temp2.degree)) {
            HeapNode tmp = temp2;
            temp2 = temp2.sibling;
            tmp.sibling = temp1.sibling;
            temp1.sibling = tmp;
            temp1 = tmp.sibling;
          }

          else {
            temp1 = temp1.sibling;
          }
        }

        else {
          HeapNode tmp = temp1;
          temp1 = temp2;
          temp2 = temp2.sibling;
          temp1.sibling = tmp;

          if (tmp == nodes) {
            nodes = temp1;
          }

          else {
          }
        }
      }
    }

    if (temp1 == null) {
      temp1 = nodes;

      while (temp1.sibling != null) {
        temp1 = temp1.sibling;
      }
      temp1.sibling = temp2;
    }

    else {
    }
  }

  public void union(HeapNode binHeap) {
    merge(binHeap);

    HeapNode prevTemp = null, temp = nodes,
            nextTemp = nodes.sibling;

    while (nextTemp != null) {

      if ((temp.degree != nextTemp.degree)
              || ((nextTemp.sibling != null)
              && (nextTemp.sibling.degree
              == temp.degree))) {
        prevTemp = temp;
        temp = nextTemp;
      }

      else {

        if (temp.key <= nextTemp.key) {
          temp.sibling = nextTemp.sibling;
          nextTemp.parent = temp;
          nextTemp.sibling = temp.child;
          temp.child = nextTemp;
          temp.degree++;
        }

        else {

          if (prevTemp == null) {
            nodes = nextTemp;
          }

          else {
            prevTemp.sibling = nextTemp;
          }

          temp.parent = nextTemp;
          temp.sibling = nextTemp.child;
          nextTemp.child = temp;
          nextTemp.degree++;
          temp = nextTemp;
        }
      }
      nextTemp = temp.sibling;
    }
  }

  public int findMinimum()
  {
    return nodes.findMinNode().key;
  }

  public void delete(int value) {

    if ((nodes != null)
            && (nodes.findANodeWithKey(value) != null)) {
      decreaseKeyValue(value, findMinimum() - 1);
      extractMin();
    }
  }

  public void decreaseKeyValue(int oldVal,
                               int newVal)
  {
    HeapNode temp
            = nodes.findANodeWithKey(oldVal);
    if (temp == null)
      return;
    temp.key = newVal;
    HeapNode tempParent = temp.parent;

    while ((tempParent != null)
            && (temp.key < tempParent.key)) {
      int z = temp.key;
      temp.key = tempParent.key;
      tempParent.key = z;

      temp = tempParent;
      tempParent = tempParent.parent;
    }
  }

  public int extractMin()
  {
    if (nodes == null)
      return -1;

    HeapNode temp = nodes, prevTemp = null;
    HeapNode minNode = nodes.findMinNode();

    while (temp.key != minNode.key) {
      prevTemp = temp;
      temp = temp.sibling;
    }

    if (prevTemp == null) {
      nodes = temp.sibling;
    }
    else {
      prevTemp.sibling = temp.sibling;
    }

    temp = temp.child;
    HeapNode fakeNode = temp;

    while (temp != null) {
      temp.parent = null;
      temp = temp.sibling;
    }

    if ((nodes == null) && (fakeNode == null)) {
      size = 0;
    }
    else {
      if ((nodes == null) && (fakeNode != null)) {
        nodes = fakeNode.reverse(null);
        size = nodes.getSize();
      }
      else {
        if ((nodes != null) && (fakeNode == null)) {
          size = nodes.getSize();
        }
        else {
          union(fakeNode.reverse(null));
          size = nodes.getSize();
        }
      }
    }

    return minNode.key;
  }

  public void displayHeap() {
    displayHeap(nodes);
    System.out.println("\n");
  }

  private void displayHeap(HeapNode r)
  {
    if (r != null) {
      displayHeap(r.child);
      System.out.print(r.key + " ");
      displayHeap(r.sibling);
    }
  }
}