import java.util.HashMap;

class LinkedListRunner {

  public static void main(String[] input) {

    LinkedList newList = new LinkedList();
    Node headNode = new Node();
    headNode.setValue(15);
    newList.setHead(headNode);

    // Let's add a list of integer values to the linked list
    Integer[] inputList = { 25, 14, 35, 87, 91 ,17, 19, 25, 32};

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

    // deleteMiddleNode(newList, newList.getHead().getNext().getNext().getNext());

    newList = partition(newList,36);
    currentNode = newList.getHead();

    while(currentNode.getNext()!=null){
    System.out.println(currentNode.getValue());
    currentNode = currentNode.getNext();
    }
    System.out.println(currentNode.getValue());

    // System.out.println(kFromLast(newList, 3).getValue());
  }


  static LinkedList partition(LinkedList input,Integer mid){
    
    if(input.getHead()==null || input.getHead().getNext()==null) return input;

    LinkedList leftList = new LinkedList();
    LinkedList rightList = new LinkedList();

    // Node leftStart = null;
    // Node rightStart = null;

    Node current = null;
    
    Node leftEnd = null;
    Node rightEnd = null;
    
    Node start = input.getHead();
 
    //  while(start!=null){
      
      
    //   if(start.getValue()<mid){
    //     // Extend leftList 
    //     if(leftList.getHead()==null) {
    //       leftList.setHead(start);
    //       leftEnd = start;
    //     }else{
    //       leftEnd.setNext(start);
    //       leftEnd = leftEnd.getNext();
    //     }
        
    //   }else{
    //     // Extend rightList
    //     if(rightList.getHead()==null) {
    //       rightList.setHead(start);
    //       rightEnd = start;
    //     }else{
    //       rightEnd.setNext(start);
    //       rightEnd = rightEnd.getNext();
    //     }

    //   }
    //   start = start.getNext();
    // }
    while(start!=null){
      
      current = new Node(start.getValue());
      
      if(current.getValue()<mid){
        // Extend leftList 
        if(leftList.getHead()==null) {
          leftList.setHead(current);
          leftEnd = current;
        }else{
          leftEnd.setNext(current);
          leftEnd = leftEnd.getNext();
        }
        
      }else{
        // Extend rightList
        if(rightList.getHead()==null) {
          rightList.setHead(current);
          rightEnd = current;
        }else{
          rightEnd.setNext(current);
          rightEnd = rightEnd.getNext();
        }

      }
      current=null;      
      start = start.getNext();
    }

    if(rightList.getHead()!=null) {
      // System.out.println(rightList.getHead().getValue());
      leftEnd.setNext(rightList.getHead());
    }

    return leftList;
    
  }

  static Boolean deleteMiddleNode(LinkedList input,Node c){
    if(c.getNext()==null || c.getNext().getNext()==null) return false;

    c.setValue(c.getNext().getValue());
    c.setNext(c.getNext().getNext());
    return true;
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

  public Node(){};

  public Node(Integer intValue){
    value = intValue;
  }

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

  public LinkedList(Integer input){
    this.setHead(new Node(input));
  }

  public LinkedList(){};
  private Node head = null;

  void setHead(Node inputHead) {
    head = inputHead;
  }

  Node getHead() {
    return head;
  }

}