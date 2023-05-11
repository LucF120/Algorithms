package RBTree;

public class NilNode implements NodeInterface{

  private int value;
  private NodeInterface leftChild;
  private NodeInterface rightChild;
  private NodeInterface parent;
  private String color;

  public NilNode() {
    this.leftChild = null;
    this.rightChild = null;
    this.parent = null;
    this.color = "black";
  }

  @Override
  public int getValue() {
    return -1;
  }

  @Override
  public void setValue(int value) {
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
  public String getColor() {
    return this.color;
  }

  @Override
  public void setColor(String s) {
  }

  @Override
  public boolean isNil() {
    return true;
  }

  @Override
  public void setRightChild(NodeInterface n) {
  }

  @Override
  public void setLeftChild(NodeInterface n) {
  }

  @Override
  public void setParent(NodeInterface n) {
    this.parent = n;
  }

  @Override
  public int height() {
    return 0;
  }
}
