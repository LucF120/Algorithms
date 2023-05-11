package Skiplist;

import org.junit.Test;

import static org.junit.Assert.*;

public class SkipListTest {

  @Test
  public void constructorTest() {
    LinkedList linkList = new LinkedList();
    linkList.insert(20);
    linkList.insert(19);
    linkList.insert(18);
    linkList.insert(17);
    linkList.insert(16);
    linkList.insert(15);
    linkList.insert(14);
    linkList.insert(13);
    linkList.insert(12);
    linkList.insert(11);
    linkList.insert(10);
    linkList.insert(9);
    linkList.insert(8);
    linkList.insert(7);
    linkList.insert(6);
    linkList.insert(5);
    linkList.insert(4);
    linkList.insert(3);
    linkList.insert(2);
    linkList.insert(1);


    SkipList list = new SkipList(linkList);

  }

  @Test
  public void lookupTest() {
    LinkedList linkList = new LinkedList();
    linkList.insert(20);
    linkList.insert(19);
    linkList.insert(18);
    linkList.insert(17);
    linkList.insert(16);
    linkList.insert(15);
    linkList.insert(14);
    linkList.insert(13);
    linkList.insert(11);
    linkList.insert(10);
    linkList.insert(9);
    linkList.insert(8);
    linkList.insert(7);
    linkList.insert(6);
    linkList.insert(5);
    linkList.insert(3);
    linkList.insert(2);


    SkipList list = new SkipList(linkList);

    assertEquals(2, list.lookup(2).value());
    assertEquals(3, list.lookup(3).value());
    assertEquals(5, list.lookup(5).value());
    assertEquals(6, list.lookup(6).value());
    assertEquals(7, list.lookup(7).value());
    assertEquals(8, list.lookup(8).value());
    assertEquals(9, list.lookup(9).value());
    assertEquals(10, list.lookup(10).value());
    assertEquals(11, list.lookup(11).value());
    assertEquals(17, list.lookup(17).value());

    assertThrows(IllegalArgumentException.class, () -> list.lookup(1));





  }

  @Test
  public void insertTest() {
    LinkedList linkList = new LinkedList();
    linkList.insert(20);
    linkList.insert(19);
    linkList.insert(18);
    linkList.insert(17);
    linkList.insert(16);
    linkList.insert(15);
    linkList.insert(14);
    linkList.insert(13);
    linkList.insert(11);
    linkList.insert(10);
    linkList.insert(9);
    linkList.insert(8);
    linkList.insert(7);
    linkList.insert(6);
    linkList.insert(5);
    linkList.insert(2);


    SkipList list = new SkipList(linkList);

    list.insert(3);
    list.insert(4);
    list.insert(1);
    list.insert(21);
    list.insert(20);
    list.insert(40);
    list.insert(41);
    list.insert(0);


    assertEquals(41, list.lookup(41).value());
    assertEquals(40, list.lookup(40).value());

    System.out.println();
    list.printSkipList();

  }

  @Test
  public void deleteTest() {
    LinkedList linkList = new LinkedList();
    linkList.insert(20);
    linkList.insert(19);
    linkList.insert(18);
    linkList.insert(17);
    linkList.insert(16);
    linkList.insert(15);
    linkList.insert(14);
    linkList.insert(13);
    linkList.insert(11);
    linkList.insert(10);
    linkList.insert(9);
    linkList.insert(8);
    linkList.insert(7);
    linkList.insert(6);
    linkList.insert(5);
    linkList.insert(2);


    SkipList list = new SkipList(linkList);
    list.printSkipList();

    System.out.println();
    list.delete(17);
    list.delete(2);
    list.delete(20);
    list.printSkipList();
  }

  @Test
  public void chainMethodTest() {
    LinkedList linkList = new LinkedList();
    linkList.insert(20);
    linkList.insert(19);
    linkList.insert(18);
    linkList.insert(17);
    linkList.insert(16);
    linkList.insert(15);
    linkList.insert(14);
    linkList.insert(13);
    linkList.insert(11);
    linkList.insert(10);
    linkList.insert(9);
    linkList.insert(8);
    linkList.insert(7);
    linkList.insert(6);
    linkList.insert(5);
    linkList.insert(2);


    SkipList list = new SkipList(linkList);
    list.insert(1);
    list.insert(41);
    assertEquals(1, list.lookup(1).value());
    assertEquals(41, list.lookup(41).value());
    list.delete(2);
    assertEquals(1, list.lookup(1).value());
    list.delete(1);
    assertEquals(41, list.lookup(41).value());
    list.insert(42);
    assertEquals(41, list.lookup(41).value());
    assertEquals(42, list.lookup(42).value());
    list.delete(42);
    assertEquals(41, list.lookup(41).value());
    list.delete(41);
    assertEquals(20, list.lookup(20).value());

  }


}