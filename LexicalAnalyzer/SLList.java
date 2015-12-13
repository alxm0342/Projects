public class SLList <T> {
	private Node<T> head;
	private int size;
	
	//Default constructor
	SLList () {
		//Creates the dummy head
		setHead(new Node<T>(null, null));
		setSize(0);
	}
	
	//GET METHODS
	public Node<T> getHead() {
		return this.head;
	}

	public int getSize() {
		return size;
	}

	//SET METHODS
	public void setHead(Node<T> head) {
		this.head = head;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	//FUNCTIONAL METHODS
	//Add a new Node to the list
	public void add(T newData) {
      Node<T> s = new Node<T>(newData, null);
      
      //Checks if list is empty, adds Node to the ends of the list
      if (this.head.getNext() != null) {
          Node<T> tmp = this.head.getNext();
          
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
	public void delete(Node<T> s) {       
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
                   Node<T> tmp = head;
                   Node<T> checkMe = head.getNext();
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
	public boolean contains(Node<T> s) {
      boolean isContained = false;
      
      if (this.head.getNext() != null) {
          Node<T> tmp = this.head.getNext();
          
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
	
	//Checks if the list is empty
	public boolean isEmpty() {
		return (!this.head.hasNext());
	}
	
	//Return the contents of the string
   public String toString() {
       //The return string
       String theString = "{";
       //Temporary node to traverse the list
       Node<T> tmp;
       
       //Checks if the list is null
       if (head.getNext() != null) {
           //Set the state to the first node after the dummy head
           tmp = head.getNext();
           //Traverse the list and print each element to the return string
           theString += tmp.getValue().toString();
           for (int i=this.size-1; i>0; i--) {
               tmp = tmp.getNext();
               theString = (theString + ", " + tmp.getValue().toString());
           }
       }
       theString += "}";
       //Return the return String
       return (theString);
   }
}
