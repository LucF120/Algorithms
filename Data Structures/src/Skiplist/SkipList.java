package Skiplist;

import java.util.ArrayList;
import java.util.Random;

public class SkipList {
  ArrayList<LinkedList> lists;

  public SkipList(LinkedList list) {
    this.lists = new ArrayList<>();

    makeSkipList(list);
  }

  public void insert(int value) {
    Element lower = insertIntoList(value, lists.get(0));

    Random randomNumber = new Random();
    int nextLevel = 1; //Represents the current level of the skiplist
    boolean finished = false;
    while (!finished) {
      int coinFlip = randomNumber.nextInt(2);
      if (nextLevel > lists.size() - 1) {
        finished = true;
      }
       else if (coinFlip == 0) {
         Element upper = insertIntoList(value, lists.get(nextLevel));
         upper.setLowerElement(lower);
         lower.setUpperElement(upper);
         lower = upper;
        nextLevel++;
      } else {
        finished = true;
      }
    }
  }

  private Element insertIntoList(int value, LinkedList list) {
    boolean inserted = false;
    Element insertElement = new Element(value, null, null);
    Element firstElement = list.head();
    while (!inserted) {
      if (firstElement == null) {
        list.tail().setNext(insertElement);
        insertElement.setPrev(list.tail());
        insertElement.setNext(null);
        list.setTail(insertElement);
        inserted = true;
      }
      else if (firstElement.value() >= value) { //A -> B -> C... Inserting B between A
        // and C
        if (firstElement.prev() == null) {
          insertElement.setPrev(firstElement.prev()); //Link B and A
          firstElement.setPrev(insertElement); //Link B and C
          insertElement.setNext(firstElement); // Link B and C
          list.setHead(insertElement);
          inserted = true;
        } else {
          insertElement.setPrev(firstElement.prev()); //Link B and A
          firstElement.prev().setNext(insertElement); //Link B and A
          firstElement.setPrev(insertElement); //Link B and C
          insertElement.setNext(firstElement); // Link B and C
          inserted = true;
        }
      } else {
        firstElement = firstElement.next();
      }
    }

    return insertElement;
  }

  public Element lookup(int value) {
    Element e = lists.get(lists.size() - 1).tail();
    int currentLevel = lists.size() - 1;
    while (currentLevel >= 0) {
      try {
        if (e == null) {
          e = lists.get(currentLevel - 1).tail();
          currentLevel = currentLevel - 1;
          continue;
        }

        if (value == e.value()) {
          return e;
        }

        if (value > e.value()) {
          e = lists.get(currentLevel - 1).tail();
          currentLevel = currentLevel - 1;
          continue;
        }

        if (value < e.value()) {
          e = e.prev();
        }
      } catch (IndexOutOfBoundsException exception) {
        throw new IllegalArgumentException("Item not found in the skiplist");
      }
    }

      if (value == e.value()) {
        return e;
      } else {
        throw new IllegalArgumentException("This element is not in the skip list");
      }

  }

  public void delete(int value) {
    for (int i=0 ; i<lists.size() ; i++) {
      try {
        lists.get(i).delete(value);
      } catch (NullPointerException e) {
        return;
      }
    }
  }




  private void makeSkipList(LinkedList list) {
    lists.add(list);
    Random randomNumber = new Random();
    int nextLevel = 1; //Represents the current level of the skiplist

    //Will keep adding to the list of linked lists until there are no values left
    while (lists.get(lists.size() - 1).head() != null && lists.get(lists.size() - 1).size() > 1) {
      Element e = lists.get(nextLevel-1).tail();
      LinkedList nextLevelList = new LinkedList();
      lists.add(nextLevelList);
      for (int i = 0; i < lists.get(nextLevel-1).size(); i++) {
        // If the coin is flipped heads
        if (randomNumber.nextInt(2) == 0) {
          nextLevelList.insert(e.value());
          linkElements(nextLevelList.head(), e); //This links the elements on different levels.
          // Constant time since head just returns the head field of that linked list

        }
        e = e.prev();
      }
      if(lists.get(nextLevel).size() == lists.get(nextLevel-1).size() || lists.get(nextLevel).size() == 0) {
        lists.remove(nextLevel);
        return;
      }
      nextLevel++;
    }
  }

  private void linkElements(Element e1, Element e2) {
    e1.setLowerElement(e2);
    e2.setUpperElement(e1);
  }

  public void printList(LinkedList list) {
    Element e = list.head();
    for (int i=0 ; i<list.size() ; i++) {
      System.out.print(e.value());
      if (i<list.size()-1) {
        System.out.print(", ");
      }
      e = e.next();
    }
    System.out.print("\n");
  }

  public void printSkipList() {
    for (int i=lists.size() - 1 ; i>=0 ; i--) {
      printList(lists.get(i));
    }
  }
}
