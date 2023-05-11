package Skiplist;

import java.util.Objects;

public class Element {
  private int value;
  private Element previous;
  private Element next;
  private Element lowerElement;
  private Element upperElement;

  public Element(int value, Element previous, Element next) {
    this.value = value;
    this.previous = previous;
    this.next = next;
    this.lowerElement = null;
    this.upperElement = null;
  }

  public Element prev() {
    if (this.previous == null) {
      return null;
    }
    return this.previous;
  }

  public Element next() {
    if (this.next == null) {
      return null;
    }
    return this.next;
  }

  public void setPrev(Element e) {
    this.previous = e;
  }

  public void setNext(Element e) {
    this.next = e;
  }

  public int value() {
    return this.value;
  }

  public Element getLowerElement() {
    return this.lowerElement;
  }
  public Element getUpperElement() {
    return this.upperElement;
  }

  public void setLowerElement(Element e) {
    this.lowerElement = e;
  }
  public void setUpperElement(Element e) {
    this.lowerElement = e;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Element)) {
      return false;
    }
    Element that = (Element) other;

    if (this.previous != that.previous || this.next != that.next) {
      return false;
    }

    if (this.previous == null && this.next == null) {
      return this.value == that.value;
    }

    if (this.previous == null) {
      return that.value == this.value && this.next.value == that.next.value;
    }

    if (this.next == null) {
      return that.value == this.value && this.previous.value == that.previous.value;
    }

    return that.value == this.value && this.previous.value == that.previous.value && this.next.value == that.next.value;
  }

  @Override
  public int hashCode() {
    if (this.previous == null && this.next == null) {
      return Objects.hash(this.value);
    }
    if (this.previous == null) {
      return Objects.hash(this.value, this.next.value());
    }
    if (this.next == null) {
      return Objects.hash(this.value, this.previous.value());
    }
    return Objects.hash(this.value, this.previous.value(), this.next.value());
  }
}
