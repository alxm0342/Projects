// Alexis Mendez
// CS241, section 01
// Project 1: Use backtracking to solve the n-queens problem
// 4/25/12


import java.util.Scanner;

public class nQueens {
	static int n = 0; // the number of queens, corresponds to an (n*n) board
	static int sols = 0;
	static long nodes = 0;
	static boolean noSolution = true;
	static boolean allSolutions = false;

	// determines if a queen can be placed on row k-1, column i without issue
	static boolean place(int k, int x[]) {
		nodes++;
		boolean sameColumn;
		boolean sameDiag;
		int row = k;
		int column = x[k - 1];
	   boolean canPlace = true;
		for (int i = 1; i <= k - 1; i++) {
			int testRow = i;
			int testColumn = x[i - 1];
			if (k == 1)
				return true;
			// same diagonal or same column
			sameColumn = testColumn == column;
			sameDiag = Math.abs(column - testColumn) == Math.abs(row - testRow);
			if (sameColumn || sameDiag)
				canPlace = false;
		}
		return canPlace;
	}

	// recursive-algorithm for nQueens
	static void rNqueens(int k, int x[]) {
		for (int i = 1; i <= n; i++) {
			if (noSolution || allSolutions) {
				x[k - 1] = i;
				if (place(k, x)) {
					if (k == n) {
						nodes++;
						noSolution = false;
						sols++;
						if ((n < 9) || ((n>8) && (allSolutions==false))) {
							System.out.print(sols + " = ( ");
							for (int j = 0; j < n; j++) {
								System.out.print(x[j] + " ");
							}
							System.out.print(")\n");
						}
					} else
						rNqueens(k + 1, x);
				}
			}
		}
	}

	// iterative-algorithm for nQueens
	static void iNqueens(int n, int x[]) {
		int k = 1;
		x[k - 1] = 0;
		while (k > 0) {
			if (noSolution || allSolutions) {
				x[k - 1]++;
				while ((x[k - 1] <= n) && (place(k, x) != true)) {
					x[k - 1]++;
				}
				if (x[k - 1] <= n) {
					if (k == n) {
						// print solution vector x and stop
						noSolution = false;
						sols++;
						if ((n < 9) || ((n>8) && (allSolutions==false))) {
							System.out.print(sols + " = ( ");
							for (int j = 0; j < n; j++) {
								System.out.print(x[j] + " ");
							}
							System.out.print(")\n");
						}
					} else {
						k++;
						x[k - 1] = 0;
					}
				} else
					k--; // backtrack
			} else {
				// solution was found, exit the loop
				k = 0;
			}
		}
	}

	public static void main(String args[]) {
		// ask user to input number of Queens
		Scanner keyboard = new Scanner(System.in);
		System.out.print("Welcome to N-Queens, enter n = ");
		while (n <= 0) {
			n = keyboard.nextInt();
			if (n <= 0) {
				System.out.println("Please enter a positive integer for n.");
			}
		}

		// ask user if they would like all the solutions
		System.out.println("Would you like all the solutions?");
		String y = keyboard.next();
		y.toLowerCase();
		if (y.equals("y") || y.equals("yes"))
			allSolutions = true;

		// initiate solution vector, solve iteratively
		int x[] = new int[n];
		System.out.println("\niteration...");
		iNqueens(n, x);
		System.out.println("There are " + sols + " solution vectors.");

		// reset the variables, solve recursively
		nodes = 0;
		sols = 0;
		noSolution = true;
		for (int i = 0; i < n; i++) {
			x[i] = 0;
		}
		System.out.println("\nrecursion...");
		rNqueens(1, x);
		System.out.println("There are " + sols + " solution vectors.");
		System.out.println(nodes + " nodes checked.");
	}
}
