class QueueRunner {
  public static void main(String[] args) {
      Queue queue = new Queue(15);

      // queue.enqueue(20);
      // queue.enqueue(25);
      // queue.enqueue(30);
      // queue.enqueue(45);
      // queue.enqueue(22);

      System.out.println(queue.isEmpty());
    

     try{
        System.out.println("head "+queue.peek());
      }catch(Exception e){
        System.out.println(e.getMessage());
      }
      

      while(true){
        try{
          System.out.println(queue.dequeue());
        }catch(Exception e){
          System.out.println(e.getMessage());
          break;
        }
      }

      // System.out.println(queue.isEmpty());
      // try{
      //   System.out.println(queue.peek());
      // }catch(Exception e){
      //   System.out.println(e.getMessage());
      // }
    
  }
}

class Queue {

    // No method should return Node object  since it is a private class and and is not accesible outside the Queue class definition
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

  

  private Node head = null;
  private Node tail = null;

  // Let's write a constructor for initial queue creation with an element
  public Queue(Integer input){
    this.enqueue(input);
  }

 
  // Do we really need a tail pointer?
  // We do because we need to do all operaions in O(1) time
  public boolean enqueue(Integer input) {
    Node inputNode = new Node(input);
    if (this.head == null) {
      this.head = inputNode;
      this.tail = inputNode;
    } else {
      this.tail.setNext(inputNode);
      this.tail = this.tail.getNext();
    }
    return true;
  }

  public Integer dequeue() throws Exception {
    if (this.head == null) {
      throw new Exception("The queue is empty");
    }
    Integer returnValue = this.head.getValue();
    // this.head = this.head.getNext()==null?null:this.head.getNext();
    if (this.head.getNext() == null) {
      this.head = null;
      this.tail = null;
    } else {
      this.head = this.head.getNext();
    }
    return returnValue;
  }

  public boolean isEmpty() {
    return this.head == null ? true : false;
  }

  public Integer peek() throws Exception {
    if (this.head == null) {
      throw new Exception("The queue is empty.");
    }
    return this.head.getValue();
  }

}