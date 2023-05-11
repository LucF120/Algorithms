package BinomialHeap;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinomialHeapTest {

  @Test
  public void makeHeapTest() {
    BinomialHeap heap = new BinomialHeap();
    assertEquals(0, heap.getSize());
    assertTrue(heap.isEmpty());

    heap.insert(7);
    heap.insert(2);
    heap.insert(4);
    heap.insert(17);
    heap.insert(1);
    heap.insert(11);
    heap.insert(6);
    heap.insert(8);
    heap.insert(15);
    heap.insert(10);
    heap.insert(20);
    heap.insert(5);

    heap.displayHeap();

    System.out.println(heap.extractMin());

    heap.displayHeap();

    heap.decreaseKeyValue(15, 1);

    heap.displayHeap();


  }

  @Test
  public void insertTest() {
    BinomialHeap heap = new BinomialHeap();
    heap.insert(10);
    assertEquals(10, heap.extractMin());
  }

  @Test
  public void minTest() {
    BinomialHeap heap = new BinomialHeap();
    heap.insert(10);
    assertEquals(10, heap.findMinimum());
  }

  @Test
  public void unionTest() {
    BinomialHeap heap1 = new BinomialHeap();
    heap1.insert(10);
    heap1.insert(12);
    heap1.insert(3);

    heap1.displayHeap();

    HeapNode node = new HeapNode(1);
    node.child = new HeapNode(2);
    node.child.parent = node;
    heap1.union(node);

    heap1.displayHeap();

    assertEquals(10, heap1.nodes.child.key);



  }

  @Test
  public void extractMinTest() {
    BinomialHeap heap1 = new BinomialHeap();
    heap1.insert(10);
    heap1.insert(12);
    heap1.insert(2);

    heap1.displayHeap();

    heap1.extractMin();
    heap1.displayHeap();
  }

  @Test
  public void decreaseKeyTest() {
    BinomialHeap heap1 = new BinomialHeap();
    heap1.insert(10);
    heap1.insert(12);
    heap1.insert(2);

    heap1.displayHeap();

    heap1.decreaseKeyValue(10, 1);

    heap1.displayHeap();
    assertEquals(1, heap1.findMinimum());
  }

  @Test
  public void deleteTest() {
    BinomialHeap heap1 = new BinomialHeap();
    heap1.insert(10);
    heap1.insert(12);
    heap1.insert(2);

    heap1.delete(10);
    assertEquals(2, heap1.size);
    heap1.displayHeap();
  }
}