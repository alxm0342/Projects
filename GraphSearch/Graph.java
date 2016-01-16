/*
 * Source: http://www.mathcs.emory.edu/~cheung/Courses/323/Syllabus/Graph/Progs/bfs/Graph1.java
 */

public class Graph {
	//member attributes
	int[][]  adjMatrix;
   int      rootNode = 0;
   int      NNodes;
   boolean[] visited; 
	
   //empty graph constructor
	public Graph(int N)
   {
      NNodes = N;
      adjMatrix = new int[N][N];
      visited = new boolean[N];
   }
	
	//adjacency matrix constructor
   public Graph(int[][] mat)
   {
      int i, j;

      NNodes = mat.length;

      adjMatrix = new int[NNodes][NNodes];
      visited = new boolean[NNodes];


      for ( i=0; i < NNodes; i++)
         for ( j=0; j < NNodes; j++)
            adjMatrix[i][j] = mat[i][j];
   }
}
