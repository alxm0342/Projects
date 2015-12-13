/**Name: Alexis Mendez
 * Class: CS 240 - Tang
 * Date: 2/7/11
 * Project 2: Singly-linked Set ADT
 */
public class SLSet <T> {
    private Node head;
    private int size;
    
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
        @SuppressWarnings("unused")
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
        public void setNext(Node newNext) {
            next = newNext;
        }
        
        public String toString() {
            return this.data.toString();
        }
    }
    
 //---------------------------------------------------------------------------- 
 //Default Constructor---------------------------------------------------------
    SLSet() {
        //Creates the dummy head
        head = new Node(null, null);
        size = 0;
        System.out.println("A new set has been created.");
    }
    
//Methods----------------------------------------------------------------------
    //Returns the size of the list as an integer
    public int size() {
        return this.size;
    }
    
    //Checks if an element is already in the set
    public boolean contains(T theData) {
        Node s =  new Node (theData, null);
        boolean isDuplicate = false;
        
        if (this.head.getNext() != null) {
            Node tmp = this.head.getNext();
            
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
    
    //Removes an element from the set
    public boolean remove (T theData) {
        boolean wasRemoved = false;
        
        //Checks for an empty list
        if (head.getNext() != null) {
            //The item is an element in the list
            if (this.contains(theData)) {
                //The size of the list is one
                if (this.size == 1){
                    head.setNext(null);
                    size --;
                    wasRemoved = true;
                    System.out.println("An item has been removed");
                }
                else {
                    Node tmp = head;
                    Node checkMe = head.getNext();
                    while (checkMe != null) {
                        if (checkMe.data.equals(theData)) {
                            tmp.setNext(checkMe.getNext());
                            size --;
                            System.out.println("An item has been removed");
                        }
                        tmp = checkMe;
                        checkMe = checkMe.getNext();                        
                    }
                }
            }
        }
        else {
            System.out.println("The list is empty.");
        }
        return wasRemoved;
    }
    
    //Adds an element to the list, returns true if successful, false otherwise
    public boolean addElement (T theData) {
        boolean wasAdded = false;
        Node s = new Node(theData, null);
        
        //checks the list for content
        if (this.head.getNext() != null) {
            boolean contains = this.contains(s.data);
            Node tmp = this.head.getNext();
            
            if (contains) {
                System.out.println("This item is already part of the set.");
            }
            else {
                while (tmp.getNext() != null) {
                    tmp = tmp.getNext();
                }
                tmp.setNext(s);
                wasAdded = true;
                System.out.println("An item has been added");
                this.size ++;
            }
        }
        
        //adds the node directly after the dummy head of an empty list
        else {
            head.setNext(s);
            wasAdded = true;
            System.out.println("An item has been added");
            this.size ++;
        }
        return wasAdded;
    }
    
    //Check if the set is a subset of another set, given as a paramenter
    @SuppressWarnings("unchecked")
	public boolean subsetOf(@SuppressWarnings("rawtypes") SLSet s) {
       boolean isSubset = true;
       
       //check that the comparing list is not empty
       if (head.next != null) {
          //check that the sze of the parameter set is large enough 
          if (this.size <= s.size) {
             Node tmp = this.head.getNext();
             //traverse the list and check each element
             while (tmp != null) {
                if  (s.contains(tmp.data) != true) {
                   isSubset = false;
                }
                tmp = tmp.getNext();
             }
          }
          //Size of lists are invalid
          else {
             System.out.println("The size of the lists are invalid");
             isSubset = false;
          }
       }
       //case of an empty comparing set, do nothing
       else {
          System.out.println("The set is empty");
       }    
       
       return isSubset;
    }
    
    //Checks if 2 sets have identical elements
    @SuppressWarnings("unchecked")
	public boolean isEqual(@SuppressWarnings("rawtypes") SLSet s) {
       boolean isEqual = true;
       //Checks that both sets are of equal size
       if (this.size() == s.size()) {
          //Check for an empty set
          if (this.head.getNext() != null) {
            Node tmp = this.head.getNext();
            //traverse the list
            while (tmp != null) {
               //if an element is not found, sets are not equal
               if (s.contains(tmp.data) != true) {
                  isEqual = false;
               }
               tmp = tmp.getNext();
            }
          }          
       }
       //The sets are not of equal size
       else {
          System.out.println("The sets are not the same size");
          isEqual = false;
       }
       return isEqual;
    }
    
    //Returns the unions of 2 sets as a new set
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public SLSet union(SLSet s) {
       SLSet c = new SLSet();
       //Create a copy of the first set
       if (this.head.getNext() != null) {
          Node tmp = this.head.getNext();
          while (tmp != null) {
             c.addElement(tmp.data);
             tmp = tmp.getNext();
          }
       }
       //checks that s is not empty
       if (s.head.getNext() != null) {
         Node tmp = (Node) s.head.getNext();
         //traverse the set to be unioned, add each element to set C
         //no duplicates can be added to a set using 'addElement' method
         while (tmp != null) {
               c.addElement(tmp.data);
               tmp = tmp.getNext();
         }
       }
       return c;
    }
    
    //Returns the intersection of 2 sets as a new set
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public SLSet intersection(SLSet s) {
       SLSet c = new SLSet();
       //Checks for an empty set
       if (this.head.getNext() != null) {
          Node tmp = this.head.getNext();
          //traverse the passed set, adding only elements found in both sets
          while (tmp != null) {
             if (s.contains(tmp.data)) {
                c.addElement(tmp.data);
             }
             tmp = tmp.getNext();
          }
       }   
       return c;
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public SLSet complement(SLSet s) {
       SLSet c = new SLSet();
       
       //Check for an empty set
       if (this.head.getNext() != null) {
          Node tmp = this.head.getNext();
          //Create a duplicate set of 'this'
          while (tmp != null) {
             c.addElement(tmp.data);
             tmp = tmp.getNext();
          }
          //check that s is not empty
          if (s.head.getNext() != null) {
             //remove elements common to both sets
             tmp = (Node) s.head.getNext();
             while (tmp != null) {
                if (c.contains(tmp.data)) {
                   c.remove(tmp.data);
                }
                tmp = tmp.getNext();
             }
          }
          
       }
       
       return c;
    }
    
    //Return the contents of the string
    public String toString() {
        //The return string
        String theString = "{";
        //Temporary node to traverse the list
        Node tmp;
        
        //Checks if the list is null
        if (head.getNext() != null) {
            //Set the state to the first node after the dummy head
            tmp = head.getNext();
            //Traverse the list and print each element to the return string
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
