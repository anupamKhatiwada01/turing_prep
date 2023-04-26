import java.util.ArrayList;

public class StackRunner {

  public static void main(String[] args) {
    // Stack newStack = null;
    // try {
    // Stack stack = new Stack(15, 3);
    // newStack = stack;
    // stack.push(15);
    // stack.push(12);
    // stack.push(30);
    // stack.push(25);

    // } catch (Exception e) {
    // System.out.println(e.getMessage());

    // try {
    // while (true) {
    // System.out.println(newStack.pop());
    // }
    // } catch (Exception ex) {
    // System.out.println(ex.getMessage());
    // }

    // }

    try {
      StackOfStacks stack_one = new StackOfStacks(45, 3);
      stack_one.push(34);
      stack_one.push(35);
      stack_one.push(36);
      stack_one.push(37);
      stack_one.pop(2);
      

      while (true) {
        System.out.println(stack_one.pop());
      }

      // stack_one.printAllElements();

    } catch (Exception e) {
      System.out.println(e.getMessage());
    }

    // ArrayStack arrStack = new ArrayStack(25,4);

    // try{
    // arrStack.push(34,1);
    // arrStack.push(23,2);
    // arrStack.push(35,1);
    // arrStack.push(36,1);
    // arrStack.push(356,1);
    // arrStack.push(342,3);
    // arrStack.push(348,4);
    // // arrStack.push(34,12);
    // }catch(Exception e){
    // System.out.println(e.getMessage());
    // }

    // int index=0;
    // while(index<arrStack.checkInputArray().length){
    // System.out.println(arrStack.checkInputArray()[index]);
    // index++;
    // }

    // while(true){
    // try{
    // System.out.println(arrStack.pop(1));
    // }catch(Exception e){
    // System.out.println(e.getMessage());
    // break;
    // }
    // }

  }

}

class StackOfStacks {
  // We will assume the capacity of each stack of stack of stacks is the same
  // The StackOfStacks object itself has no max capacity of stacks ie. it can have
  // infonote number of stacks
  // capacity is the capacity of each stack
  Integer capacity;
  ArrayList<Stack> internalList = new ArrayList<>();

  // The constructor adds the first element to StackOfStacks
  // Further push operations will be performed on the last element of the list
  // If the last stack is full we'll create a new stack and push elements there
  // There will be one pop method which takes index and another which doesn't
  // The one which doesnt will pop from the last stack
  public StackOfStacks(Integer firstElement, Integer capacity) throws Exception {
    this.capacity = capacity;
    // At this point we'll create a new Stack object of the provided capacity
    try {
      // This is the first stack. We also need to keep track of its index because
      // we'll need to do pop operations
      // on stacks of particular index and we might need to do them in O(1) time so
      // some type of array/arraylist
      // implementation would be good. Since a StackOfStacks does not have a max
      // number of stacks it can hold
      // using an internal ArrayList would be good
      // All the elements will be pushed to the last element of the list. If that
      // stack is full we will
      // create new stack and add that to the list
      internalList.add(new Stack(firstElement, capacity));
    } catch (Exception e) {
      throw e;
    }
  }

  // We need to start the implementation of the push, pop and pop(index) methods
  // There are some things we need to keep in mind.
  // push is rather simple, pop will be more complicated because it might change
  // the StackOfStacks
  // object in funny ways
  public Boolean push(Integer input) {
    // This needs to be pushed into the stack at the end of the list
    // If the stack is full it'll be we'll create a new Stack object with the input
    // and push that stack to the list
    try {
      Stack current = this.internalList.get(this.internalList.size() - 1);
      if (!current.isFull()) {
        current.push(input);
      } else {
        this.internalList.add(new Stack(input, this.capacity));
      }
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  // The pop function works pretty well
  public Integer pop() throws Exception {
    Integer index = this.internalList.size() - 1;
    if (index < 0)
      throw new Exception("The stack is empty.");

    while (index >= 0) {
      Stack stack = this.internalList.get(index);
      if (stack!=null && !stack.isEmpty()) {
        return stack.pop();
      } else {
        this.internalList.remove(index);
      }
      index -= 1;
    }
    throw new Exception("Nothing left to pop.");
  }

  // The below pop function pops elements from a paricular index
  // We need to do some checks for the index
  public Integer pop(Integer index) throws Exception {
    if (index >= internalList.size() || index < 0)
      throw new Exception("The index is invalid. Enter proper index.");
    Stack current = internalList.get(index);
    if (current != null && !current.isEmpty()) {
      Integer element = current.pop();
      if (current.isEmpty())
        internalList.set(index, null);
      return element;
    }
    throw new Exception("Nothing to pop at index " + index);
  }

  public void printAllElements() {
    for (Stack s : internalList) {
      if (s == null)
        continue;
      while (!s.isEmpty()) {
        try {
          System.out.println(s.pop());
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
      }
    }
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
  public ArrayStack(Integer size, Integer stackNumber) {
    this.inputArray = new Integer[size];
    this.stackNumber = stackNumber;
    this.stackArray = new int[stackNumber];// Stack array whose length will be equal to the stack number
  }

  // The below method will be used to inject data into the respective stack
  // input is the data to be injected and stack is the stack number to which we
  // need to inject data into
  public boolean push(Integer input, Integer stack) throws Exception {
    // The value of the counter for that particular stack
    // We use the stack array to keep track of the data in the input array
    if (stack > stackArray.length)
      throw new Exception("Please specify proper stack number!");
    Integer counter = stackArray[stack - 1];
    // Will the +1/-1 issue happening in the pop method not happen here? If not then
    // why. Analyse further.
    Integer index = (counter * stackNumber) + (stack - 1);
    if (index > inputArray.length)
      throw new Exception("The stack is full!");
    inputArray[index] = input;
    stackArray[stack - 1] = counter + 1;
    return true;
  }

  // The below pop method is used to pop elements from a particular stack
  // The parameter stack is the stack from which to pop the value from
  public Integer pop(Integer stack) throws Exception {
    if (stack > stackArray.length)
      throw new Exception("Please specify proper stack number!");
    Integer counter = stackArray[stack - 1];
    // There is +1/-1 logic issue here. This issue is not trivial. Do further
    // analysis.
    Integer index = (counter * stackNumber) + (stack - 1);
    System.out.println("counter:" + counter + " , index:" + index);
    if (index >= inputArray.length)
      throw new Exception("Please enter correct stack to pop data from.");
    if (inputArray[index] == null)
      throw new Exception("The stack is empty!");
    Integer value = inputArray[index];
    inputArray[index] = null;
    stackArray[stack - 1] = counter - 1;
    return value;
  }

  public Integer[] checkInputArray() {
    return inputArray;
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
  private Integer capacity;
  // capacityCounter is the used used to check if capacity has been reached.
  // It will increase with push and decrease with pop operations
  private Integer capacityCounter = 0;

  private void setHead(Node input) {
    this.head = input;
  }

  private Node getHead() {
    return this.head;
  }

  public Stack(Integer input, Integer capacity) throws Exception {
    try {
      this.capacity = capacity;
      this.push(input);
    } catch (Exception e) {
      throw new Exception(e.getMessage());
    }
  }

  public Boolean push(Integer input) throws Exception {
    if (this.capacityCounter >= this.capacity)
      throw new Exception("Stack size at max capacity. Cannot push more elements.");
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
      this.capacityCounter += 1;
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
    this.capacityCounter -= 1;
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

  public Boolean isFull() {
    return this.capacity >= this.capacityCounter ? true : false;
  }

}
