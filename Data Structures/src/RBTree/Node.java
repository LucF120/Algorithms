package RBTree;

public class Node implements NodeInterface{
  private int value;
  private NodeInterface leftChild;
  private NodeInterface rightChild;
  private NodeInterface parent;
  private String color;

  public Node(int value, NodeInterface leftChild, NodeInterface rightChild) {
    this.value = value;
    this.leftChild = leftChild;
    this.rightChild = rightChild;
    this.parent = null;
    this.color = null;
  }

  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public void setValue(int value) {
    this.value = value;
  }

  @Override
  public NodeInterface getParent() {
    return this.parent;
  }

  @Override
  public NodeInterface getLeftChild() {
    return this.leftChild;
  }

  @Override
  public NodeInterface getRightChild() {
    return this.rightChild;
  }

  @Override
  public void setParent(NodeInterface n) {
    this.parent = n;
  }

  @Override
  public void setLeftChild(NodeInterface n) {
    this.leftChild = n;
  }

  @Override
  public void setRightChild(NodeInterface n) {
    this.rightChild = n;
  }

  @Override
  public String getColor() {
    return this.color;
  }

  @Override
  public void setColor(String s) {
    this.color = s;
  }

  @Override
  public boolean isNil() {
    return false;
  }

  @Override
  public int height() {
    return 1 + Math.max(this.getRightChild().height(), this.getLeftChild().height());
  }


}
