import java.util.*;

public class QueueStack<T> {
	Stack main = new Stack();
	Stack temp = new Stack();
	
	public void addElement(T theData) {
		if (main.isEmpty()) {
			main.push(theData);	//O-1
		}
		else {
			while (!main.isEmpty()) {
				temp.push(main.pop());		//O-N
			}
			main.push(theData);
			while (!temp.isEmpty()) {
				main.push(temp.pop());		//O-N
			}
		}
	}
	public T removeElement() {
		return (T) (main.pop());
	}
}                                  
