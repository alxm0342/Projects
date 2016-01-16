/*
 * Interface for the three simple functions needed to traverse
 * (1) peek()
 * (2) add()
 * (3) remove()
 */

public interface Searcher<T> {
	public T peek();
	public void add(T node);
	public T remove();
}
