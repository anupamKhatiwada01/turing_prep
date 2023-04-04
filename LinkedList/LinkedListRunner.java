
class LinkedListRunner {

  public static void main(String[] input) {

    LinkedList newList = new LinkedList();
    Node headNode = new Node();
    headNode.setValue(15);
    newList.setHead(headNode);

    // System.out.println(newList.getHead().getValue());

    // Let's add a list of integer values to the linked list
    Integer[] inputList = { 25, 14, 15, 17, 19, 25, 32, 87, 91 };

    // Adding all the array elements to the linked list
    Node currentNode = newList.getHead();
    int current = 0;
    while (current < inputList.length) {
      Node nodeToAdd = new Node();
      nodeToAdd.setValue(inputList[current]);
      currentNode.setNext(nodeToAdd);
      currentNode = currentNode.getNext();
      current++;
    }

    // Checking elements present in the linked list
    currentNode = newList.getHead();
    current = 0;
    while (current <= inputList.length) {
      System.out.println(currentNode.getValue());
      currentNode = currentNode.getNext();
      current++;
    }

  }

  static LinkedList removeDuplicates(LinkedList input) {


    

    return input;
  }

}

class Node {
  private Node next = null;
  // Make sure whatever object is return has an explicit hash code and equals
  // method defined
  private Object value = null;

  void setNext(Node inputNode) {
    next = inputNode;
  }

  void setValue(Object inputValue) {
    value = inputValue;
  }

  Node getNext() {
    return next;
  }

  Object getValue() {
    return value;
  }
}

class LinkedList {
  private Node head = null;

  void setHead(Node inputHead) {
    head = inputHead;
  }

  Node getHead() {
    return head;
  }

}