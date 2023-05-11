package Skiplist;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LinkedListTest {

  LinkedList list;
  @Before
  public void setup() {
    list = new LinkedList();
  }

  @Test
  public void lookup() {
    list.insert(15);
    list.insert(10);
    list.insert(20);
    list.insert(30);

    assertEquals(null, list.lookup(30).prev());
    assertEquals(30, list.lookup(30).value());
    assertEquals(20, list.lookup(30).next().value());
    assertEquals(10, list.lookup(30).next().next().value());
    assertEquals(15, list.lookup(30).next().next().next().value());

    assertEquals(30, list.lookup(20).prev().value());
    assertEquals(20, list.lookup(20).value());
    assertEquals(10, list.lookup(20).next().value());
    assertEquals(15, list.lookup(20).next().next().value());

    assertEquals(30, list.lookup(10).prev().prev().value());
    assertEquals(20, list.lookup(10).prev().value());
    assertEquals(10, list.lookup(10).value());
    assertEquals(15, list.lookup(10).next().value());

    assertEquals(30, list.lookup(15).prev().prev().prev().value());
    assertEquals(20, list.lookup(15).prev().prev().value());
    assertEquals(10, list.lookup(15).prev().value());
    assertEquals(15, list.lookup(15).value());
    assertEquals(null, list.lookup(15).next());

  }

  @Test
  public void insert() {
    list.insert(10);
    assertEquals(new Element(10, null, null), list.lookup(10));
    list.insert(20);

    assertEquals(20, list.lookup(20).value());
    assertEquals(10, list.lookup(20).next().value());

    list.insert(30);
    assertEquals(30, list.lookup(30).value());
    assertEquals(20, list.lookup(30).next().value());
    assertEquals(10, list.lookup(30).next().next().value());




  }

  @Test
  public void delete() {
    list.insert(15);
    list.insert(10);
    list.insert(20);
    list.insert(30);

    list.delete(20);

    assertEquals(null, list.lookup(20));
    assertEquals(30, list.lookup(10).prev().value());
    assertEquals(15, list.lookup(10).next().value());

    list.delete(30);
    assertEquals(null, list.lookup(30));
    assertEquals(null, list.lookup(10).prev());
    assertEquals(15, list.lookup(10).next().value());
    assertEquals(10, list.head().value());
    assertEquals(15, list.tail().value());

    list.delete(15);
    assertEquals(10, list.tail().value());
    assertEquals(10, list.head().value());
  }

  @Test
  public void size() {
    assertEquals(0, list.size());
    list.insert(15);
    assertEquals(1, list.size());
    list.insert(10);
    list.insert(20);
    list.insert(30);
    assertEquals(4, list.size());
  }

  @Test

  public void headAndTailTest() {
    assertEquals(null, list.head());
    assertEquals(null, list.tail());
    list.insert(30);
    assertEquals(30, list.head().value());
    assertEquals(30, list.tail().value());
    list.insert(10);
    assertEquals(10, list.head().value());
    assertEquals(30, list.tail().value());
    list.delete(30);
    assertEquals(10, list.head().value());
    assertEquals(10, list.tail().value());
    list.delete(10);
    assertEquals(null, list.head());
    assertEquals(null, list.tail());

  }
}