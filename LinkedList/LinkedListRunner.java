import java.util.ArrayList;
import java.util.HashMap;

class LinkedListRunner {

  public static void main(String[] input) {
    
    // Let's add a list of integer values to the linked list
    // Integer[] inputList = {15,25, 14, 35, 87, 91 ,17, 19, 25, 32};
    
    // LinkedList newList = addElements(inputList);
    // showElements(newList);


    Integer[] newList1 = {3,5,9,8,7,8,5};
    Integer[] newList2 = {7,5,8,2,6,7,9};
    Integer[] newList3 = {1,2,2,1,3};
    // LinkedList sumList = addition(addElements(newList1), addElements(newList2));
    // LinkedList sumList = reverseList(addElements(newList2));
    
    // showElements(reverseAddition(addElements(newList1), addElements(newList2)));
    System.out.println(palindrome(addElements(newList3)));
  }

  static Node loopDetection(LinkedList input){
    Node fast = null,slow = null;
    Integer count = 0;
    while(true){
      count++;
      if(fast==null) fast = input.getHead();
      else fast = fast.getNext();
      if(count%2==0){
        if(slow==null) slow = input.getHead();
        else slow = slow.getNext();        
      }
      if(fast==slow) break;
    }
    return fast;
  }

  

  static Node intersectingNode(LinkedList input_one, LinkedList input_two){
    // Traverse both nodes and find each node's tail
    Node tail_one = input_one.getHead(),tail_two = input_two.getHead();
    Integer count_one = 0,count_two = 0;
    while(tail_one.getNext()!=null){
      count_one++;
      tail_one = tail_one.getNext();
    }
    while(tail_two.getNext()!=null){
      count_two++;
      tail_two = tail_two.getNext();
    }
    // As the tails are not same means there is no intersecting node
    if(tail_one!=tail_two) return null;

    LinkedList longer_list = count_one>=count_two?input_one:input_two;
    LinkedList shorter_list = longer_list==input_one?input_two:input_one;
    
    Integer ref = Math.abs(count_one-count_two);

    // Let's reuse tail_one and tail_two again
    tail_one = shorter_list.getHead();
    tail_two = longer_list.getHead();
    while(tail_two.getNext()!=null){
      if(ref>0){
        tail_two = tail_two.getNext();
        ref--;
      }else{
        if(tail_one==tail_two) break;
        tail_one = tail_one.getNext();
        tail_two = tail_two.getNext();
      }
    }

    return tail_one;

    
  }
  
  static Boolean palindrome(LinkedList input){
    if(input.getHead().getNext()==null) return true;
    LinkedList reverse = reverseList(input);
    Node current_one = input.getHead(),current_two = reverse.getHead();
    while(true){
      if(current_one==null || current_two==null) break;
      if(current_one.getValue()!=current_two.getValue()) return false;
      current_one = current_one.getNext();
      current_two = current_two.getNext();
    }
    return true;
  } 

  static LinkedList reverseList(LinkedList input){
    // Seems reversing a linked list is not as straight forward as I thought it would be
    // One way is to add elements to array and then convert back to linked list
    // Can't seem to think of another way
    // There is a way. We'll explore that....
    // This seems to be a very important linked list concept so we will learn this first before moving forward. 
    // Reversing a linked list also seems like something that might come in handy in the palindrome question
    if(input.getHead()==null || input.getHead().getNext()==null) return input;
 
    Node ref = null, prev=null,last = input.getHead(),current = last.getNext();
    while (current.getNext()!=null){
      ref = current.getNext();
      current.setNext(last);
      last.setNext(prev);
      prev = last;
      last = current;
      current = ref;
    }

    // Need to link current one last time to last
    current.setNext(last);
    return new LinkedList(current);
       
  }


  static void showElements(LinkedList newList){
    if(newList.getHead()==null){
      System.out.println("No elements to show");
      return;
    }
    Node currentNode = newList.getHead();

    while(currentNode.getNext()!=null){
    System.out.println(currentNode.getValue());
    currentNode = currentNode.getNext();
    }
    System.out.println(currentNode.getValue());
  }


  static LinkedList addElements(Integer[] arr){
    LinkedList newList = new LinkedList();
    if(arr.length==0 || arr==null) return newList;
    Node currentNode = null;
    int current = 0;
    while (current < arr.length) {
      Node nodeToAdd = new Node(arr[current]);
      if(currentNode==null) {newList.setHead(nodeToAdd);currentNode=nodeToAdd;}
      else {currentNode.setNext(nodeToAdd);currentNode = currentNode.getNext();}
      current++;
    }
    return newList;
  }


  static LinkedList reverseAddition(LinkedList inputOne, LinkedList inputTwo){
    return addition(reverseList(inputOne),reverseList(inputTwo));
  }


  static LinkedList addition(LinkedList inputOne, LinkedList inputTwo){

    // We can solve this problem in place using the larger linked list as the return list
    // But for that we would have to check which is the larger linked list before hand that would mean the time complexity would change to O(max(M,N)+M+N). Ask the interviewer if this is an acceptable tradeof

    // First step is the carry variable which will be an integer between 0 and 9
    Integer carry=0,sum=0,value=0;
    // Now we need a run a loop that will terminate when the larger linked list is over
    Node starterOne = inputOne.getHead();
    Node starterTwo = inputTwo.getHead();

    LinkedList returnList = new LinkedList();
    Node current = null;
    
    // The loop will end when both the linked lists are exausted
    while(starterOne!=null || starterTwo!=null){
      Integer a=0,b=0;
      if(starterOne!=null) a=starterOne.getValue();
      if(starterTwo!=null) b=starterTwo.getValue();
      sum = a+b+carry;
      value = sum%10;
      carry = sum/10;
      if(current==null){
        current = new Node(value);
        returnList.setHead(current);
      }else{
        current.setNext(new Node(value));
        current = current.getNext();
      }
      starterOne = starterOne!=null && starterOne.getNext()!=null?starterOne.getNext():null;
      starterTwo = starterTwo!=null && starterTwo.getNext()!=null?starterTwo.getNext():null;
    }

    if(carry!=0) current.setNext(new Node(carry));

    return returnList;
   
  }


  static LinkedList partition(LinkedList input,Integer mid){
    
    if(input.getHead()==null || input.getHead().getNext()==null) return input;

    LinkedList leftList = new LinkedList();
    LinkedList rightList = new LinkedList();

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

    if(rightList.getHead()!=null) leftEnd.setNext(rightList.getHead());
    
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
  public LinkedList(Node input){
    this.setHead(input);
  }

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