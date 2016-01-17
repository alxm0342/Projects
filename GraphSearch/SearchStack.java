/*
 * Alexis Mendez
 * Searcher Class leveraging a stack (should result in depth-first search
 */

import java.util.Stack;

public class SearchStack<T> implements Searcher<T> {
	//implemented with a stack
	private Stack<T> wrappedStack;
	
	SearchStack() {
		wrappedStack = new Stack<T>();
	}
	
	//methods
	public T peek() {
		return wrappedStack.peek();
	}
	
	@Override
	public void add(T node) {
		wrappedStack.push(node);
	}

	public T remove() {
		return wrappedStack.pop();
	}	
}
