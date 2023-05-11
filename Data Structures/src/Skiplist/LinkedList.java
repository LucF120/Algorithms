package Skiplist;

public class LinkedList {
  private Element head;
  private Element tail;

  public LinkedList() {
    this.head = null;
    this.tail = null;
  }

  public Element head() {
    return this.head;
  }

  public Element tail() {
    return this.tail;
  }

  public void setHead(Element e) {
    this.head = e;
  }

  public void setTail(Element e) {
    this.tail = e;
  }

  public Element lookup(int value) {
    Element x = this.head;
      while(x != null && x.value() != value) {
        x = x.next();
      }
    return x;
  }

  public void insert(int value) {
    Element e = new Element(value, null, null);
    e.setNext(this.head);
    if(this.tail == null) {
      this.tail = e;
    }
    if (this.head != null) {
      this.head.setPrev(e);
    }
    this.head = e;

  }

  public void delete(int value) {
    Element e = lookup(value);
    if (e.prev() != null) {
      e.prev().setNext(e.next());
    }
    else {
      this.head = e.next();
    }
    if (e.next() == null) {
      this.tail = e.prev();
    }
    if (e.next() != null) {
      e.next().setPrev(e.prev());
    }
  }

  public int size() {
    Element e = this.head;
    int count = 0;
    while (e != null) {
      count = count + 1;
      e = e.next();
    }
    return count;
  }

}
