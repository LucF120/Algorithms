package BinomialHeap;
// Class 1
// BinomialHeapNode
public class HeapNode {

  int key, degree;
  HeapNode parent;
  HeapNode sibling;
  HeapNode child;

  // Constructor of this class
  public HeapNode(int k)
  {

    key = k;
    degree = 0;
    parent = null;
    sibling = null;
    child = null;
  }

  // Method 1
  // To reverse
  public HeapNode reverse(HeapNode sibl)
  {
    HeapNode ret;
    if (sibling != null)
      ret = sibling.reverse(this);
    else
      ret = this;
    sibling = sibl;
    return ret;
  }

  // Method 2
  // To find minimum node
  public HeapNode findMinNode()
  {

    // this keyword refers to current instance itself
    HeapNode x = this, y = this;
    int min = x.key;

    while (x != null) {
      if (x.key < min) {
        y = x;
        min = x.key;
      }

      x = x.sibling;
    }

    return y;
  }

  // Method 3
  // To find node with key value
  public HeapNode findANodeWithKey(int value)
  {

    HeapNode temp = this, node = null;

    while (temp != null) {
      if (temp.key == value) {
        node = temp;
        break;
      }

      if (temp.child == null)
        temp = temp.sibling;

      else {
        node = temp.child.findANodeWithKey(value);
        if (node == null)
          temp = temp.sibling;
        else
          break;
      }
    }

    return node;
  }

  // Method 4
  // To get the size
  public int getSize()
  {
    return (
            1 + ((child == null) ? 0 : child.getSize())
                    + ((sibling == null) ? 0 : sibling.getSize()));
  }
}