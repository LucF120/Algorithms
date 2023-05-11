package Hash;

import org.junit.Test;

import static org.junit.Assert.*;

public class HashTest {

  @Test
  public void insert() {
    Hash h = new Hash(20);

    assertEquals(0, h.size());

    assertEquals(9, h.insert("hi", 1));
    assertEquals(1, h.size());


    assertEquals(1, h.bucketArray[9].size());

   assertEquals(9, h.insert("gj", 2));

    assertEquals(2, h.bucketArray[9].size());
    h.insert("hello", 4);


    h.listAllKeys();
  }

  @Test
  public void find() {
    Hash h = new Hash(20);

    assertEquals(0, h.size());

    assertEquals(9, h.insert("hi", 1));
    assertEquals(1, h.size());


    assertEquals(1, h.bucketArray[9].size());

    assertEquals(9, h.insert("gj", 2));

    assertEquals(2, h.bucketArray[9].size());

    assertEquals(1, h.find("hi"));
    assertEquals(2, h.find("gj"));

  }

  @Test
  public void delete() {
    Hash h = new Hash(20);

    assertEquals(0, h.size());

    assertEquals(9, h.insert("hi", 1));
    assertEquals(1, h.size());


    assertEquals(1, h.bucketArray[9].size());

    assertEquals(9, h.insert("gj", 2));

    assertEquals(2, h.bucketArray[9].size());

    h.listAllKeys();

    assertEquals(1, h.delete("hi"));
    assertEquals(1, h.bucketArray[9].size());
    assertEquals(1, h.size());
  }

  @Test
  public void test() {
    Hash h = new Hash(2);

    h.insert("a", 1);
    h.insert("b", 1);
    h.insert("c", 1);
    h.insert("d", 1);

    h.listAllKeys();
    System.out.println();

    h.delete("b");

    h.listAllKeys();






  }
}