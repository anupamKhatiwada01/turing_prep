import java.util.HashMap;

class LinkedListRunner {

  public static void main(String[] input) {

    LinkedList newList = new LinkedList();
    Node headNode = new Node();
    headNode.setValue(15);
    newList.setHead(headNode);

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
    // newList = removeDuplicates(newList);
    // currentNode = newList.getHead();

    // while(currentNode.getNext()!=null){
    // System.out.println(currentNode.getValue());
    // currentNode = currentNode.getNext();
    // }
    // System.out.println(currentNode.getValue());

    System.out.println(kFromLast(newList, 3).getValue());

  }

  static Node kFromLast(LinkedList input, Integer k) {
    Node foundNode = null;
    Node current = input.getHead();

    while (current.getNext() != null) {
      
      current = current.getNext();
      if (k==0) foundNode = input.getHead();
      if(foundNode!=null) foundNode = foundNode.getNext();
      k--;
    }

    return foundNode;
  }

  static LinkedList removeDuplicates(LinkedList input) {
    if (input.getHead() == null || input.getHead().getNext() == null)
      return input;
    HashMap<Integer, Boolean> refMap = new HashMap<>();

    Node previous = null;
    Node current = input.getHead();

    while (current.getNext() != null) {
      if (!refMap.containsKey(current.getValue())) {
        refMap.put(current.getValue(), true);
      } else {
        previous.setNext(current.getNext());
      }
      previous = current;
      current = current.getNext();
    }

    // System.out.println(current.getValue());
    // System.out.println(previous.getValue());

    return input;
  }

}

class Node {
  private Node next = null;
  // Make sure whatever object is return has an explicit hash code and equals
  // method defined
  private Integer value = null;

  void setNext(Node inputNode) {
    next = inputNode;
  }

  void setValue(Integer inputValue) {
    value = inputValue;
  }

  Node getNext() {
    return next;
  }

  Integer getValue() {
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