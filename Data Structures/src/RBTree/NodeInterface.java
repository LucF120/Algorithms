package RBTree;

public interface NodeInterface {
  int getValue();

  void setValue(int value);


  NodeInterface getParent();


  NodeInterface getLeftChild();

  NodeInterface getRightChild();

  void setParent(NodeInterface n);

  void setLeftChild(NodeInterface n);

  void setRightChild(NodeInterface n);

  String getColor();

  void setColor(String s);

  boolean isNil();

  int height();
}
