/*
 * Alexis Mendez
 * A learning project impementing a depth-first and breadth-first search by interchanging stacks and queues
 */

public class GraphSearch {
	public static void main(String[] args) {
		//interfact searcher, concrete types can be SearchStack or SearchQueue
		Searcher<Integer> s;
		
		//initialize the graph
		Graph<String> g = new Graph<String>();
		g.addVertex("0");
		g.addVertex("1");
		g.addVertex("2");
		g.addVertex("3");
		g.addVertex("4");
		g.addVertex("5");
		g.addVertex("6");
		g.addEdge("0", "1");
		g.addEdge("0", "2");
		g.addEdge("0", "3");
		g.addEdge("1", "4");
		g.addEdge("2", "5");
		g.addEdge("3", "6");
		
		//breadth-first search
		s = new SearchQueue<Integer>();
		search(g, s);
		
		//depth-first search
		s = new SearchStack<Integer>();
		search(g, s);
	}
	
	//implement search with same algorithm, just switch the data type to provide different results
	private static void search (Graph g, Searcher<Integer> s) {
		//TODO: Alex
	}
}