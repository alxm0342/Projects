/*
 * Alexis Mendez
 * Simple, undirected graph class for traversal exercises implemented with HashMaps
 * Parallel edges and duplicate vertices are not allow (will overwrite previous entry)
 * Hanging edges are allowed (currently; auto-removal is planned)
 */

import java.util.HashMap;

//Simple graph G = {V,E}
public class Graph<T> {
	private HashMap<T, Object> vertices;
	private HashMap<String, Object> edges;
	
	//Graph constructor
	public Graph() {
		vertices = new HashMap<T, Object>();
		edges = new HashMap<String, Object>();
	}
	
	//getters/setters
	public HashMap<T, Object> getVertices() {
		return vertices;
	}
	
	public HashMap<String, Object> getEdges() {
		return edges;
	}
	
	//member functions
	public boolean addVertex(T value) {
		vertices.put(value, null);
		return true;
	}
	
	
	//TODO (improvement): auto-eliminate hanging edges containing this vertex
	public boolean removeVertex(T vertex) {
		if (vertices.containsKey(vertex)) {
			vertices.remove(vertex);			
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean addEdge(T start, T end) {
		if (vertices.containsKey(start) && vertices.containsKey(end)) {
			Edge<T> e = new Edge<T>(start, end);
			edges.put(e.getKey(), null);
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean removeEdge(T start, T end) {
		Edge<T> e = new Edge<T>(start, end);
		
		if (edges.containsKey(e.getKey())) {
			edges.remove(e.getKey());
			return true;
		}
		else {
			return false;
		}
	}
	
	//Edge class
	private class Edge<T> {
		private T start;
		private T end;
		
		//Node constructor
		public Edge (T one, T two) {
			start = one;
			end = two;
		}

		public String getKey() {
			return "(" + start.toString() + ", " +  end.toString() + ")";
		}
	}
}
