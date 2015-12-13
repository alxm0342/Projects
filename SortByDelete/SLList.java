public class SLList <T> {
	private Node head;
	private int size;
	
	//Nested Node Class------------------------------------
	private class Node {
		private T data;
		private Node next;
    
		public Node (T newData, Node n) {
			data = newData;
			next = n;
		}
		
	   //GET METHODS
      //Get next Node
     	public Node getNext() {
     		return next;
     	}
     	//Get value of element in Node
     	public T getValue() {
     		return this.data;
     	}
     	
     	//SET METHODS
     	//Set element of Node
     	@SuppressWarnings("unused")
		public void setElement (T newData) {
     		data = newData;
     	}
     	//Set next pointer of Node
     	public void setNext (Node newNext) {
     		next = newNext;
     	}
     	//Return a string of Node's contents
     	public String toString() {
     		return this.data.toString();
     	}
     	
     	//FUNCTIONAL METHODS
     	//Check if Node has followed by another Node
     	public boolean hasNext() {
     		if (this.next != null) {
     			return true;
     		}
     		else {
     			return false;
     		}
     	}
	}
	//End Nested Node Class---------------------------------
	
	//Default constructor
	SLList () {
		//Creates the dummy head
		setHead(new Node(null, null));
		setSize(0);
	}
	
	//GET METHODS
	public Node getHead() {
		return head;
	}

	public int getSize() {
		return size;
	}

	//SET METHODS
	public void setHead(Node head) {
		this.head = head;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	//FUNCTIONAL METHODS
	//Add a new Node to the list
	public void add(T newData) {
      Node s = new Node(newData, null);
      
      //Checks if list is empty, adds Node to the ends of the list
      if (this.head.getNext() != null) {
          Node tmp = this.head.getNext();
          
          while (tmp.getNext() != null) {
                  tmp = tmp.getNext();
              }
          tmp.setNext(s);
          this.size ++;
      }
      
      //Adds the Node directly after the dummy head of an empty list
      else {
          head.setNext(s);
          this.size ++;
      }
	}
	
	//Delete a Node from the list
	public void delete(Node s) {       
       //Checks for an empty list
       if (head.getNext() != null) {
           //Checks if the Node is in the list
           if (this.contains(s)) {
               //The size of the list is one
               if (this.size == 1){
                   head.setNext(null);
                   size --;
               }
               else {
                   Node tmp = head;
                   Node checkMe = head.getNext();
                   while (checkMe != null) {
                       if (checkMe.equals(s)) {
                           tmp.setNext(checkMe.getNext());
                           size --;
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
	}
	
	//Checks if a Node is in the list
	public boolean contains(Node s) {
      boolean isContained = false;
      
      if (this.head.getNext() != null) {
          Node tmp = this.head.getNext();
          
          //Traverses list and checks each Node for equality
          //Stops check at end of list or if Node is found
          while ((tmp != null) && (isContained == false)) {
              if (s.equals(tmp)){
                  isContained = true;
              }
              tmp = tmp.getNext();
          }
      }
      return isContained;
	}
	
	//Sort the list by deleting Nodes with values
	//smaller than any of the following nodes
   public void sortByDeletion() {
   	//Checks if the list is empty or of size 1
   	if ((this.head.getNext() != null) && (this.getSize() > 1)) {
   		Node target = this.head.getNext();
   		Node checkedNode = target.getNext();
		      while (target.hasNext() && (checkedNode != null)) {
		      	int targetValue = (Integer) target.getValue();
		      	int checkedValue = (Integer) checkedNode.getValue();
		      	//Delete Target if smaller than any following nodes
	         	if (checkedValue > targetValue) {
	         		Node tmp = target.getNext();
	         		this.delete(target);
	         		target = tmp;
	         		checkedNode = target.getNext();
	         	}
	         	else {
	         		//Continue traversing through the list
	         		if (checkedNode.hasNext()) {
	         			checkedNode = checkedNode.getNext();
	         		}
	         		//Stop traversal, change target node
	         		else {
	         			target = target.getNext();
	         			checkedNode = target.getNext();
	         		}
	         	}
		      }
   	}
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
