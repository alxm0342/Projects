/*
 * Alexis Mendez
 * Searcher Class leveraging a queue (should result in breadth-first search
 */

import java.util.PriorityQueue;
import java.util.Queue;

public class SearchQueue<T> implements Searcher<T> {
	//implemented with a queue
	private Queue<T> wrappedQueue = new PriorityQueue<T>();
	
	//methods
	public T peek() {
		return wrappedQueue.peek();
	}
	
	public void add(T node) {
		wrappedQueue.add(node);
	}

	public T remove() {
		return wrappedQueue.poll();
	}	
}
