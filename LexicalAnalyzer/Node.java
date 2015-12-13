public class Node<T> {
	private T data;
	private Node<T> next;
 
	public Node (T newData, Node<T> n) {
		data = newData;
		next = n;
	}
	
   //GET METHODS
   //Get next Node
  	public Node<T> getNext() {
  		return next;
  	}
  	//Get value of element in Node
	public T getValue() {
  		return this.data;
  	}
  	
  	//SET METHODS
  	//Set element of Node
	public void setElement (T newData) {
  		data = newData;
  	}
  	//Set next pointer of Node
  	public void setNext (Node<T> newNext) {
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
