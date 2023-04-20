public class StackRunner {

  public static void main(String[] args) {

    // Stack stack = new Stack(13);
    // stack.push(15);
    // stack.push(12);
    // stack.push(30);
    // stack.push(25);

    // while (!stack.isEmpty()) {
    // try {

    // // System.out.println(stack.peek());

    // System.out.println("minimum: "+stack.getMin());
    // // System.out.println(stack.pop());
    // stack.pop();
    // } catch (Exception e) {
    // System.out.println(e.getMessage());
    // }

    // }

  }

}

// inputArr is the array to be used to implement the stacks.
// x is the number of stacks that are to be implemented.
// The question specifically asks for 3 but makes sense to implement it more
// generically.
// To do this we will pass in the array which will be passed by reference so we
// can use the stored data
// I feel it doesn't make sense to do it in a method. Seems like I should
// implement a new class ArrayStack that implements multiple
// stacks and intenally uses an array to do stuff.
class ArrayStack {
  // inputArray is the Array used to implement the stack
  // StackArray is the array used to maintain the counters.
  // It's length will be equal to the stackNumber ie. the number of stacks to be
  // implemented.
  private Integer[] inputArray;
  private Integer stackNumber;
  private int[] stackArray;

  // The constructor will take an Integer for stack implementation
  // We need to set up as many counters as there are stack numbers
  // How will the addition/deletion to a particular stack work?
  public ArrayStack(Integer[] arr, Integer stackNumber) {
    this.inputArray = arr;
    this.stackNumber = stackNumber;
    this.stackArray = new int[stackNumber];// Stack array whose length will be equal to the stack number
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
    private Integer min = null;

    public Node(Integer input) {
      this.value = input;
    }

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

    // For minimum
    public void setMin(Integer input) {
      this.min = input;
    }

    public Integer getMin() {
      return this.min;
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
    // this.head = new Node(input);
    this.push(input);
  }

  public Boolean push(Integer input) {
    try {
      Node toAdd = new Node(input);
      // If the head is null the below line will automatically set the next of the
      // node being added as null
      toAdd.setNext(this.getHead());
      // Set the minimum till that point on the node when its pushed
      // Done in this manner we don't need to touch pop method for setting minimum
      if (this.isEmpty() == true)
        toAdd.setMin(input);
      else {
        if (this.getHead().getMin() >= input)
          toAdd.setMin(input);
        else
          toAdd.setMin(this.getHead().getMin());
      }
      this.setHead(toAdd);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  public Integer pop() throws Exception {
    if (this.getHead() == null) {
      throw new Exception("The stack is empty");
    }
    Integer value = this.getHead().getValue();
    if (this.getHead().getNext() != null) {
      this.setHead(this.getHead().getNext());
    } else
      this.setHead(null);
    return value;
  }

  public Integer peek() throws Exception {
    if (this.isEmpty())
      throw new Exception("The stack is empty.");
    return this.getHead().getValue();
  }

  public Integer getMin() throws Exception {
    if (this.isEmpty())
      throw new Exception("The stack is empty.");
    return this.getHead().getMin();
  }

  public boolean isEmpty() {
    if (this.getHead() == null)
      return true;
    return false;
  }

}
