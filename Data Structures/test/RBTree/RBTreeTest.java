package RBTree;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RBTreeTest {

  @Test
  public void insertTest() {
    ArrayList<Integer> values = new ArrayList<>();
    RBTree tree = new RBTree(values);
    tree.insert(new Node(7, null, null));
    tree.insert(new Node(4, null, null));
    tree.insert(new Node(2, null, null));
    tree.insert(new Node(5, null, null));
    tree.insert(new Node(6, null, null));
    tree.insert(new Node(1, null, null));
    tree.insert(new Node(3, null, null));
    tree.delete(tree.search(tree.rootNode, 4));
    tree.sort(tree.rootNode);


    assertEquals(3, tree.predecessor(tree.search(tree.rootNode, 5)).getValue());
    assertEquals(5, tree.successor(tree.search(tree.rootNode, 3)).getValue());
  }

  @Test
  public void searchTest() {
    ArrayList<Integer> values = new ArrayList<>();
    values.add(9);
    values.add(5);
    values.add(6);
    values.add(3);
    values.add(12);
    values.add(15);
    values.add(14);
    RBTree tree = new RBTree(values);

    assertEquals(12, tree.search(tree.rootNode, 12).getValue());
    assertEquals(9, tree.search(tree.rootNode, 9).getValue());
    assertEquals(3, tree.search(tree.rootNode, 3).getValue());
  }

  @Test
  public void minMaxTest() {
    ArrayList<Integer> values = new ArrayList<>();
    values.add(9);
    values.add(5);
    values.add(6);
    values.add(3);
    values.add(12);
    values.add(15);
    values.add(14);
    RBTree tree = new RBTree(values);

    assertEquals(15, tree.maximum(tree.rootNode).getValue());
    assertEquals(3, tree.minimum(tree.rootNode).getValue());

    tree.sort(tree.rootNode);
  }

  @Test
  public void successorAndPredecessorTest() {
    ArrayList<Integer> values = new ArrayList<>();
    values.add(15);
    values.add(6);
    values.add(18);
    values.add(3);
    values.add(7);
    values.add(17);
    values.add(20);
    values.add(2);
    values.add(4);
    values.add(13);
    values.add(9);
    RBTree tree = new RBTree(values);

    assertEquals(17, tree.successor(tree.search(tree.rootNode, 15)).getValue());
    assertEquals(6, tree.successor(tree.search(tree.rootNode, 4)).getValue());
    assertEquals(15, tree.predecessor(tree.search(tree.rootNode, 17)).getValue());
    assertEquals(6, tree.predecessor(tree.search(tree.rootNode, 7)).getValue());
  }

  @Test
  public void testColors() {
    ArrayList<Integer> values = new ArrayList<>();
    values.add(15);
    values.add(6);
    values.add(18);
    values.add(3);
    values.add(7);
    values.add(17);
    values.add(20);
    values.add(2);
    values.add(4);
    values.add(13);
    values.add(9);
    RBTree tree = new RBTree(values);

    assertEquals("black", tree.rootNode.getColor());
    assertEquals("red", tree.rootNode.getLeftChild().getColor());
    assertEquals("black", tree.rootNode.getRightChild().getColor());
    assertEquals("black", tree.rootNode.getLeftChild().getRightChild().getColor());
    assertEquals("black", tree.rootNode.getLeftChild().getLeftChild().getColor());
    assertEquals("red", tree.rootNode.getRightChild().getRightChild().getColor());
    assertEquals("red", tree.rootNode.getRightChild().getLeftChild().getColor());
    assertEquals("red", tree.rootNode.getLeftChild().getLeftChild().getRightChild().getColor());
    assertEquals("red", tree.rootNode.getLeftChild().getLeftChild().getLeftChild().getColor());
    assertEquals("red", tree.rootNode.getLeftChild().getRightChild().getRightChild().getColor());

    assertEquals("black",
            tree.rootNode.getLeftChild().getLeftChild().getLeftChild().getLeftChild().getColor());
    assertEquals("black",
            tree.rootNode.getLeftChild().getLeftChild().getRightChild().getLeftChild().getColor());

  }

  @Test
  public void rotateTest() {
    ArrayList<Integer> values = new ArrayList<>();
    values.add(15);
    values.add(6);
    values.add(18);
    values.add(3);
    values.add(7);
    values.add(17);
    values.add(20);
    values.add(2);
    values.add(4);
    values.add(13);
    values.add(9);
    RBTree tree = new RBTree(values);


    tree.leftRotate(tree.rootNode);

    assertEquals(18, tree.rootNode.getValue());
    assertEquals(20, tree.rootNode.getRightChild().getValue());
    assertEquals(15, tree.rootNode.getLeftChild().getValue());

    tree.rightRotate(tree.rootNode);

    assertEquals(15, tree.rootNode.getValue());
    assertEquals(6, tree.rootNode.getLeftChild().getValue());
    assertEquals(18, tree.rootNode.getRightChild().getValue());

    tree.rightRotate(tree.rootNode);

    assertEquals(6, tree.rootNode.getValue());
    assertEquals(3, tree.rootNode.getLeftChild().getValue());
    assertEquals(15, tree.rootNode.getRightChild().getValue());
  }

  @Test
  public void deleteTest() {
    ArrayList<Integer> values = new ArrayList<>();
    RBTree tree = new RBTree(values);
    tree.insert(new Node(10, null, null));
    tree.insert(new Node(9, null, null));
    tree.insert(new Node(11, null, null));
    tree.insert(new Node(8, null, null));

    tree.delete(tree.search(tree.rootNode, 10));
    assertEquals(-1, tree.search(tree.rootNode, 10).getValue());
    assertEquals(9, tree.rootNode.getValue());
    tree.delete(tree.search(tree.rootNode, 9));
    assertEquals(-1, tree.search(tree.rootNode, 9).getValue());
    assertEquals(11, tree.rootNode.getValue());
    tree.delete(tree.search(tree.rootNode, 11));
    assertEquals(-1, tree.search(tree.rootNode, 11).getValue());
    assertEquals(8, tree.rootNode.getValue());
    tree.delete(tree.search(tree.rootNode, 8));
    assertEquals(-1, tree.search(tree.rootNode, 8).getValue());
    assertEquals(-1, tree.rootNode.getValue());
  }

  @Test
  public void heightTest() {
    ArrayList<Integer> values = new ArrayList<>();
    values.add(15);
    values.add(6);
    values.add(18);
    values.add(3);
    values.add(7);
    values.add(17);
    values.add(20);
    values.add(2);
    values.add(4);
    values.add(13);
    values.add(9);
    RBTree tree = new RBTree(values);

    assertEquals(4, tree.rootNode.height());

    tree.leftRotate(tree.rootNode);
    assertEquals(5, tree.rootNode.height());

    tree.rightRotate(tree.rootNode);
    assertEquals(4, tree.rootNode.height());

    tree.rightRotate(tree.rootNode);
    assertEquals(4, tree.rootNode.height());

    tree.leftRotate(tree.rootNode);
    assertEquals(4, tree.rootNode.height());

    tree.delete(tree.search(tree.rootNode, 15));
    tree.delete(tree.search(tree.rootNode, 6));
    tree.delete(tree.search(tree.rootNode, 18));
    tree.delete(tree.search(tree.rootNode, 3));
    tree.delete(tree.search(tree.rootNode, 7));
    tree.delete(tree.search(tree.rootNode, 17));
    tree.delete(tree.search(tree.rootNode, 20));
    tree.delete(tree.search(tree.rootNode, 2));
    tree.delete(tree.search(tree.rootNode, 4));
    tree.delete(tree.search(tree.rootNode, 13));
    tree.delete(tree.search(tree.rootNode, 9));
    assertEquals(0, tree.rootNode.height());

    tree.insert(new Node(1, null, null));
    assertEquals(1, tree.rootNode.height());
    tree.insert(new Node(1, null, null));
    assertEquals(2, tree.rootNode.height());


  }

}