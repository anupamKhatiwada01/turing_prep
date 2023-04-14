public class StackRunner {

  public static void main(String[] args) {

    Stack stack = new Stack(10);
    stack.add(15);
    stack.add(25);
    stack.add(30);

    try {
      stack.pop();
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    while (!stack.isEmpty()) {
      // System.out.println(stack.peek());
      try {
        System.out.println(stack.pop());
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }

    }
    // System.out.println(stack.add(15));
    // System.out.println(stack.peek());
    try {
      System.out.println(stack.pop());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
    // System.out.println(stack.isEmpty());

  }

}

class Stack {
  // Stack is a data structure that has for methods. Push, pop, peek and isEmpty
  // We will implement all four methods here
  // We'll implement it similar to LikedLists as we love them now
  // The units of addition or removal are the object Node which has a a value and
  // a next property
  // Value will be of type Integer and next will be of type Node
  // Our stack will only accpet Integer objects and thos will stack be converted
  // to Node with that value and then inserted
  private class Node {
    private Integer value = null;
    private Node next = null;

    public Node(Integer input) {
      this.value = input;
    }

    // public void setValue(Integer input){
    // this.value = input;
    // }

    public void setNext(Node input) {
      this.next = input;
    }

    public Integer getValue() {
      return this.value;
    }

    // Making the Node class private has this problem that we cannot effectively get
    // the next object.
    // As its private we can only return its value which is fine until we are doing
    // reference checks
    public Node getNext() {
      return this.next;
    }

  }

  private Node head;

  private void setHead(Node input) {
    this.head = input;
  }

  private Node getHead() {
    return this.head;
  }

  public Stack(Integer input) {
    this.head = new Node(input);
  }

  public Boolean add(Integer input) {
    try {
      Node toAdd = new Node(input);
      toAdd.setNext(this.getHead());
      this.setHead(toAdd);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public Integer pop() throws Exception {
    if (this.getHead() == null)
      throw new Exception("The stack is empty");
    Integer value = this.getHead().getValue();
    if (this.getHead().getNext() != null) {
      this.setHead(this.getHead().getNext());
    } else
      this.setHead(null);
    return value;
  }

  public Integer peek() {
    return this.getHead().getValue();
  }

  public boolean isEmpty() {
    if (this.getHead() == null)
      return true;
    return false;
  }

}
