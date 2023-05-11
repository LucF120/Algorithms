package Hash;

class Hash {
  HashNodeLinkedList[] bucketArray;

  int numBuckets;

  int size;


  public Hash(int numBuckets) {
    bucketArray = new HashNodeLinkedList[numBuckets];
    this.numBuckets = numBuckets;
    size = 0;

    for (int i = 0; i < numBuckets; i++)
      bucketArray[i] = new HashNodeLinkedList();
  }

  public int size() {
    return size;
  }

  public boolean isEmpty() {
    return size() == 0;
  }

  private final int hashCode(String key) {
    int total = 0;
    for (int i = 0; i < key.length(); i++) {
      total = total + (int) key.charAt(i);
    }
    return total;
  }

  private int getBucketIndex(String key) {
    int hashCode = hashCode(key);
    int index = hashCode % numBuckets;
    // key.hashCode() could be negative.
    index = index < 0 ? index * -1 : index;
    return index;
  }

  public int delete(String key) {
    int bucketIndex = getBucketIndex(key);
    int hashCode = hashCode(key);

    HashNode node = bucketArray[bucketIndex].lookup(new HashNode(key, 1, 1));
    bucketArray[bucketIndex].delete(node);

    size--;
    return node.value;
  }

  public int find (String key) {
    int bucketIndex = getBucketIndex(key);
    int hashCode = hashCode(key);

    HashNode head = bucketArray[bucketIndex].head();

    while (head != null) {
      if (head.key.equals(key))
        return head.value;
      head = head.next;
    }

    throw new IllegalArgumentException("Key not found");
  }

  public int insert(String key, int value) {

    int j = this.getBucketIndex(key);

    HashNode node = this.bucketArray[j].lookup(new HashNode(key, value, hashCode(key)));
    if (node == null) {
      this.bucketArray[j].insert(new HashNode(key, value, j));
      size++;
    } else {
      throw new IllegalArgumentException("Cannot insert the same key twice");
    }
    return j;
  }

  public void listAllKeys() {
    for (int i=0 ; i<this.bucketArray.length ; i++) {
      if (bucketArray[i].size() > 0) {
        HashNode node = bucketArray[i].head();
        while(node != null) {
          System.out.println("Key: " + node.key + ", Index: " + i + ", Value: " + node.value);
          node = node.next;
        }
      }
    }
  }
}