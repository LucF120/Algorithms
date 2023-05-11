package RBTree;

import java.util.ArrayList;

public class RBTree {
  NodeInterface rootNode;


  public RBTree(ArrayList<Integer> values) {
    this.rootNode = new NilNode();
    for(int i=0 ; i<values.size() ; i++) {
        this.insert(new Node(values.get(i), null, null));
      }
  }

  public void delete(NodeInterface z) {
    NodeInterface y = z;
    String yOriginalColor = y.getColor();

    NodeInterface x;
      if (z.getLeftChild().isNil()) {
        x = z.getRightChild();
        transplant(z, z.getRightChild());
      } else if (z.getRightChild().isNil()) {
        x = z.getLeftChild();
        transplant(z, z.getLeftChild());
      } else {
        y = minimum(z.getRightChild());
        yOriginalColor = y.getColor();
        x = y.getRightChild();

          if (y.getParent().getValue() == z.getValue()) {
            x.setParent(y);
          }
          else {
          transplant(y, y.getRightChild());
          y.setRightChild(z.getRightChild());
          y.getRightChild().setParent(y);
        }
        transplant(z, y);
        y.setLeftChild(z.getLeftChild());
        y.getLeftChild().setParent(y);
        y.setColor(z.getColor());
      }

      if (yOriginalColor.equals("black")) {
        deleteFixup(x);
      }

    this.height();
  }

  private void transplant(NodeInterface u, NodeInterface v) {
    if (u.getParent().isNil()) {
      this.rootNode = v;
    }
    else if (u.getValue() == u.getParent().getLeftChild().getValue()) {
      u.getParent().setLeftChild(v);
    }
    else {
      u.getParent().setRightChild(v);
    }
      v.setParent(u.getParent());
  }

  private void deleteFixup(NodeInterface x) {
    while (x != this.rootNode && x.getColor().equals("black")) {
      if (x.getValue() == x.getParent().getLeftChild().getValue()) {
        NodeInterface w = x.getParent().getRightChild();
        if (w.getColor().equals("red")) {
          w.setColor("black");
          x.getParent().setColor("red");
          leftRotate(x.getParent());
          w = x.getParent().getRightChild();
        }
        if (w.getLeftChild().getColor().equals("black") && w.getRightChild().getColor().equals(
                "black")) {
          w.setColor("red");
          x = x.getParent();
        }
        else if (w.getRightChild().getColor().equals("black")) {
          w.getLeftChild().setColor("black");
          w.setColor("red");
          rightRotate(w);
          w = x.getParent().getRightChild();
        }
        else {
          w.setColor(x.getParent().getColor());
          x.getParent().setColor("black");
          w.getRightChild().setColor("black");
          leftRotate(x.getParent());
          x = this.rootNode;
        }
      }
      else {
        NodeInterface w = x.getParent().getLeftChild();
        if (w.getColor().equals("red")) {
          w.setColor("black");
          x.getParent().setColor("red");
          rightRotate(x.getParent());
          w = x.getParent().getLeftChild();
        }
        if (w.getRightChild().getColor().equals("black") && w.getLeftChild().getColor().equals(
                "black")) {
          w.setColor("red");
          x = x.getParent();
        }
        else if (w.getLeftChild().getColor().equals("black")) {
          w.getRightChild().setColor("black");
          w.setColor("red");
          leftRotate(w);
          w = x.getParent().getLeftChild();
        }
        else {
          w.setColor(x.getParent().getColor());
          x.getParent().setColor("black");
          w.getLeftChild().setColor("black");
          rightRotate(x.getParent());
          x = this.rootNode;
        }
      }
    }
    x.setColor("black");
  }

  public void insert(NodeInterface z) {
    NodeInterface y = new NilNode();
    NodeInterface x = this.rootNode;
    while(!x.isNil()) {
      y = x;
      if (z.getValue() < x.getValue()) {
        x = x.getLeftChild();
      } else {
        x = x.getRightChild();
      }
    }
    z.setParent(y);

    if (y.isNil()) {
      this.rootNode = z;
    } else if (z.getValue() < y.getValue()) {
      y.setLeftChild(z);
    } else {
      y.setRightChild(z);
    }
    z.setLeftChild(new NilNode());
    z.setRightChild(new NilNode());
    z.setColor("red");
    insertFixup(z);

    this.height();
  }

  private void insertFixup(NodeInterface z) {
      while (z.getParent().getColor().equals("red")) {
        if (z.getParent() == z.getParent().getParent().getLeftChild()) {
          NodeInterface y = z.getParent().getParent().getRightChild();
          if (y.getColor().equals("red")) {
            z.getParent().setColor("black");
            y.setColor("black");
            z.getParent().getParent().setColor("red");
            z = z.getParent().getParent();
          } else if (z == z.getParent().getRightChild()) {
            z = z.getParent();
            leftRotate(z);
          } else {
            z.getParent().setColor("black");
            z.getParent().getParent().setColor("red");
            rightRotate(z.getParent().getParent());
          }
        } else {
          NodeInterface y = z.getParent().getParent().getLeftChild();
          if (y.getColor().equals("red")) {
            z.getParent().setColor("black");
            y.setColor("black");
            z.getParent().getParent().setColor("red");
            z = z.getParent().getParent();
          } else if (z == z.getParent().getLeftChild()) {
            z = z.getParent();
            rightRotate(z);
          } else {
            z.getParent().setColor("black");
            z.getParent().getParent().setColor("red");
            leftRotate(z.getParent().getParent());
          }
        }
      }
      this.rootNode.setColor("black");
  }


  public NodeInterface search(NodeInterface root, int key) {
    if (root.isNil() || key == root.getValue()) {
      return root;
    }
    if (key < root.getValue()) {
      return search(root.getLeftChild(), key);
    } else {
      return search(root.getRightChild(), key);
    }
  }

  public NodeInterface minimum(NodeInterface root) {
    while(!root.getLeftChild().isNil()) {
      root = root.getLeftChild();
    }
    return root;
  }

  public NodeInterface maximum(NodeInterface root) {
    while(!root.getRightChild().isNil()) {
      root = root.getRightChild();
    }
    return root;
  }

  public void sort(NodeInterface root) {
    if (!root.isNil()) {
      sort(root.getLeftChild());
      System.out.print(root.getValue() + " " + "Color: " + root.getColor() + " ");
      sort(root.getRightChild());
    }
  }

  public NodeInterface successor(NodeInterface root) {
    if (!root.getRightChild().isNil()) {
      return minimum(root.getRightChild());
    }
    NodeInterface y = root.getParent();
    while (!y.isNil() && root.getValue() == y.getRightChild().getValue()) {
      root = y;
      y = y.getParent();
    }
    return y;
  }

  public NodeInterface predecessor(NodeInterface root) {
    if (!root.getLeftChild().isNil()) {
      return maximum(root.getLeftChild());
    }
    NodeInterface y = root.getParent();
    while (!y.isNil() && root.getValue() == y.getLeftChild().getValue()) {
      root = y;
      y = y.getParent();
    }

    return y;
  }

  public void leftRotate(NodeInterface root) {
    NodeInterface y = root.getRightChild();
    root.setRightChild(y.getLeftChild());
    if (!y.getLeftChild().isNil()) {
      y.getLeftChild().setParent(root);
    }
    y.setParent(root.getParent());
    if (root.getParent().isNil()) {
      this.rootNode = y;
    }
    else if (root.getValue() == root.getParent().getLeftChild().getValue()) {
      root.getParent().setLeftChild(y);
    }
    else {
      root.getParent().setRightChild(y);
    }
    y.setLeftChild(root);
    root.setParent(y);

    this.height();
  }

  public void rightRotate(NodeInterface root) {
    NodeInterface y = root.getLeftChild();
    root.setLeftChild(y.getRightChild());
    if (!y.getRightChild().isNil()) {
      y.getRightChild().setParent(root);
    }
    y.setParent(root.getParent());
    if (root.getParent().isNil()) {
      this.rootNode = y;
    }
    else if (root.getValue() == root.getParent().getRightChild().getValue()) {
      root.getParent().setRightChild(y);
    }
    else {
      root.getParent().setLeftChild(y);
    }
    y.setRightChild(root);
    root.setParent(y);

    this.height();
  }

  public void height() {
    System.out.println(this.rootNode.height());
  }

}
