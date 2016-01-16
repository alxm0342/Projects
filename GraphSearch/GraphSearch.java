/*
 * Alexis Mendez
 * A learning project impementing a depth-first and breadth-first search by interchanging stacks and queues
 */

public class GraphSearch {
	public static void main(String[] args) {
		//interfact searcher, concrete types can be SearchStack or SearchQueue
		Searcher<Integer> s;
		
		//========================================================
		//---------------------	0  1  2  3  4  5  6  7  8 --------
		//========================================================
		int[][] adjacency = {{	0, 1, 0, 1, 0, 0, 0, 0, 1 },  // 0
									{ 	1, 0, 0, 0, 0, 0, 0, 1, 0 },  // 1
									{ 	0, 0, 0, 1, 0, 1, 0, 1, 0 },  // 2
									{ 	1, 0, 1, 0, 1, 0, 0, 0, 0 },  // 3
									{ 	0, 0, 0, 1, 0, 0, 0, 0, 1 },  // 4
									{ 	0, 0, 1, 0, 0, 0, 1, 0, 0 },  // 5
									{ 	0, 0, 0, 0, 0, 1, 0, 0, 0 },  // 6
									{ 	0, 1, 1, 0, 0, 0, 0, 0, 0 },  // 7
									{ 	1, 0, 0, 0, 1, 0, 0, 0, 0 }};	// 8
		
		
		Graph g = new Graph(adjacency);
		
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