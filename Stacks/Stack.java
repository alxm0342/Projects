/**Name: Alexis Mendez
 * Class: CS240 - Lab 3
 * Date: 3/5/2012
 * Description: This class is a Singly linked list-based stack implementation
 */

public class Stack<T> {
    
    //Nested Node class------------------------------------------------------------
    private class Node {
        //Creates a node of generic type
        private T data;
        private Node next;
        
        //Creates a node with the given element and next node
        public Node(T newData, Node n) {
            data = newData;
            next = n;
        }
        
        //Return the element of this node
        public T getElement() {
            return data;
        }
        
        //Get methods:---------------------------------------------------------
        //Returns the next node of this node.
        public Node getNext() {
            return next;
        }
        
        //Set methods:---------------------------------------------------------
        //Sets the element of this node
        @SuppressWarnings("unused")
		public void setElement(T newData) {
            data = newData;
        }
        
        //Sets the next node of this node
        @SuppressWarnings("unused")
		public void setNext(Node newNext) {
            next = newNext;
        }
        
        public String toString() {
            return this.data.toString();
        }
    }
    //-------------------------------------------------------------------------
    
    //Members of Stack Class:--------------------------------------------------
    protected Node top;
    protected int size;
    
    //Default Constructor:-----------------------------------------------------
    public Stack() {
        top = null;
        size = 0;
    }
    
    //Class Methods:-----------------------------------------------------------
    
    //Push an element onto the top of the stack
    public void push(T elem) {
      Node v = new Node (elem, top);
      top = v;
      size++;
    }
    
    //Removes an element from the top of the stack
    public T pop() {
      if (isEmpty()) {
        System.out.println("The stack is empty.");
        return null;
      }
      T temp = top.getElement();
      top = top.getNext(); // link-out the former top node
      size--;
      return temp;
    }
    
    //Returns the element on top of the stack
    public T top() {
      if (this.isEmpty()) {
        System.out.println("The stack is empty.");
        return null;
      }
      return top.getElement();
    }
    
    //Returns true if the stack is empty, false otherwise
    public boolean isEmpty() {
      if (top == null){
          return true;
      }
      else {
        return false;
      }
    }    
            
    //Return the size of the stack
    public int size() {
        return size;
    }
    
    //Checks if an element is already in the set
    public boolean contains(T theData) {
        Node s =  new Node (theData, null);
        boolean isDuplicate = false;
        
        if (this.top != null) {
            Node tmp = this.top;
            
            //traverses list and checks each node for equality
            while (tmp != null) {
                if (s.data.equals(tmp.data)){
                    isDuplicate = true;
                }
                tmp = tmp.getNext();
            }
        }
        return isDuplicate;
    }
    
    //Return the contents of the stack as a string
    public String toString() {
        //The return string
        String theString = "{";
        //Temporary node to traverse the list
        Node tmp;
        
        //Checks if the list is null
        if (top != null) {
            //Set the state to the top of the stack
            tmp = top;
            //Traverse the stack and print each element to the return string
            theString += tmp.data.toString();
            for (int i=this.size-1; i>0; i--) {
                tmp = tmp.getNext();
                theString = (theString + ", " + tmp.data.toString());
            }
        }
        theString += "}";
        //Return the return String
        return (theString);
    }
}
