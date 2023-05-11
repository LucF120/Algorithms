package Hash;

class HashNode {
  String key;
  int value;
  HashNode next;
  HashNode prev;
  final int hashCode;


  public HashNode(String key, int value, int hashCode)
  {
    this.key = key;
    this.value = value;
    this.hashCode = hashCode;
  }
}