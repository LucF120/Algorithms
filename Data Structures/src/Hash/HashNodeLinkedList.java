package Hash;

import Skiplist.Element;

public class HashNodeLinkedList {
  private HashNode head;
  private HashNode tail;

  public HashNodeLinkedList() {
    this.head = null;
    this.tail = null;
  }

  public HashNode head() {
    return this.head;
  }

  public HashNode tail() {
    return this.tail;
  }

  public void setHead(HashNode n) {
    this.head = n;
  }

  public void setTail(HashNode n) {
    this.tail = n;
  }

  public HashNode lookup(HashNode n) {
    HashNode node = this.head;
    while(node != null && !node.key.equals(n.key)) {
      node = node.next;
    }
    return node;
  }

  public void insert(HashNode n) {
    n.next = this.head;
    if(this.tail == null) {
      this.tail = n;
    }
    if (this.head != null) {
      this.head.prev = n;
    }
    this.head = n;

  }

  public void delete(HashNode n) {
    HashNode node = lookup(n);
    if (node.prev != null) {
      node.prev.next = node.next;
    }
    else {
      this.head = node.next;
    }
    if (node.next == null) {
      this.tail = node.prev;
    }
    if (node.next != null) {
      node.next.prev = node.prev;
    }
  }

  public int size() {
    HashNode n = this.head;
    int count = 0;
    while (n != null) {
      count = count + 1;
      n = n.next;
    }
    return count;
  }
}
