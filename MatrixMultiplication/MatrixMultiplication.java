// Alexis Mendez
// CS331, Section 01
// Project 1: A comparison of different matrix multiplication algorithms
// 10/08/12

import java.util.*;

public class MatrixMultiplication {
	//---------------------------------------------------------------------------------
	public static void classicalMultiplication(int A[][], int B[][], int C[][], int n) {
		//3 for-loop matrix multiplication
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				for (int k=0; k<n; k++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
	}
	//---------------------------------------------------------------------------------
	public static void divideConquer(int A[][], int B[][], int C[][], int n) {
		//base case: if n=1
		if (n==1) {
			C[0][0] = A[0][0]*B[0][0];
		}
		//base case: if n=2
		else if (n==2) {
			C[0][0] = A[0][0]*B[0][0] + A[0][1]*B[1][0];
			C[0][1] = A[0][0]*B[0][1] + A[0][1]*B[1][1];
			C[1][0] = A[1][0]*B[0][0] + A[1][1]*B[1][0];
			C[1][1] = A[1][0]*B[0][1] + A[1][1]*B[1][1];
		}
		//recursive case
		else {
			//save original matrix size for return
			int initialN = n;
			//check if incoming matrix has and odd number of columns or rows
			if (n%2!=0) {
				//create temporary even numbered matrices
				int tmpA[][] = new int[n+1][n+1];
				int tmpB[][] = new int[n+1][n+1];
				//copy contents of A and B to temporary matrices
				for (int i=0; i<n; i++) {
					for (int j=0; j<n; j++) {
						tmpA[i][j] = A[i][j];
						tmpB[i][j] = B[i][j];
					}
				}
				A = tmpA;
				B = tmpB;
				//add a row of 0's to A and B
				for (int i=n; i<n+1; i++) {
					for (int j=n; j<n+1; j++) {
						A[i][j] = 0;
						B[i][j] = 0;
					}
				}
				//increment n for to be even
				n++;
			}
			//divide Matrices for Recursion
			int A1[][] = new int[n/2][n/2];
			int A2[][] = new int[n/2][n/2];
			int A3[][] = new int[n/2][n/2];
			int A4[][] = new int[n/2][n/2];
			int B1[][] = new int[n/2][n/2];
			int B2[][] = new int[n/2][n/2];
			int B3[][] = new int[n/2][n/2];
			int B4[][] = new int[n/2][n/2];
			//copy upper-left matrices
			for (int i=0; i<n/2; i++) {
				for (int j=0; j<n/2; j++) {
					A1[i][j] = A[i][j];
					B1[i][j] = B[i][j];
				}
			}
			//copy upper-right matrices
			for (int i=0; i<n/2; i++) {
				for (int j=n/2; j<n; j++) {
					A2[i][j-(n/2)] = A[i][j];
					B2[i][j-(n/2)] = B[i][j];
				}
			}
			//copy lower-left matrices
			for (int i=n/2; i<n; i++) {
				for (int j=0; j<n/2; j++) {
					A3[i-(n/2)][j] = A[i][j];
					B3[i-(n/2)][j] = B[i][j];
				}
			}
			//copy lower-right matrices
			for (int i=n/2; i<n; i++) {
				for (int j=n/2; j<n; j++) {
					A4[i-(n/2)][j-(n/2)] = A[i][j];
					B4[i-(n/2)][j-(n/2)] = B[i][j];
				}
			}
			//instantiate solution matrices for recursive calls
			int C1[][] = new int[n/2][n/2];
			int C2[][] = new int[n/2][n/2];
			int C3[][] = new int[n/2][n/2];
			int C4[][] = new int[n/2][n/2];
			int C5[][] = new int[n/2][n/2];
			int C6[][] = new int[n/2][n/2];
			int C7[][] = new int[n/2][n/2];
			int C8[][] = new int[n/2][n/2];
			//recursive calls to multiply sub-matrices
			divideConquer(A1, B1, C1, n/2);
			divideConquer(A2, B3, C2, n/2);
			divideConquer(A1, B2, C3, n/2);
			divideConquer(A2, B4, C4, n/2);
			divideConquer(A3, B1, C5, n/2);
			divideConquer(A4, B3, C6, n/2);
			divideConquer(A3, B2, C7, n/2);
			divideConquer(A4, B4, C8, n/2);
			//copy upper-left solution matrix
			for (int i=0; i<n/2; i++) {
				for (int j=0; j<n/2; j++) {
					C[i][j] = C1[i][j] + C2[i][j];
				}
			}
			//copy upper-right solution matrix
			for (int i=0; i<n/2; i++) {
				for (int j=n/2; j<initialN; j++) {
					C[i][j] = C3[i][j-(n/2)] + C4[i][j-(n/2)];
				}
			}
			//copy lower-left solution matrix
			for (int i=n/2; i<initialN; i++) {
				for (int j=0; j<n/2; j++) {
					C[i][j] = C5[i-(n/2)][j] + C6[i-(n/2)][j];
				}
			}
			//copy lower-right solution matrix
			for (int i=n/2; i<initialN; i++) {
				for (int j=n/2; j<initialN; j++) {
					C[i][j] = C7[i-(n/2)][j-(n/2)] + C8[i-(n/2)][j-(n/2)];
				}
			}		
		}
	}
	//---------------------------------------------------------------------------------
	public static void strassenMultiplication(int A[][], int B[][], int C[][], int n) {
		//base case: if n=1
		if (n==1) {
			C[0][0] = A[0][0]*B[0][0];
		}
		//base case: if n=2
		else if (n==2) {
			C[0][0] = A[0][0]*B[0][0] + A[0][1]*B[1][0];
			C[0][1] = A[0][0]*B[0][1] + A[0][1]*B[1][1];
			C[1][0] = A[1][0]*B[0][0] + A[1][1]*B[1][0];
			C[1][1] = A[1][0]*B[0][1] + A[1][1]*B[1][1];
		}
		//recursive case
		else {
			//save original matrix size for return
			int initialN = n;
			//check if incoming matrix has and odd number of columns or rows
			if (n%2!=0) {
				//create temporary even numbered matrices
				int tmpA[][] = new int[n+1][n+1];
				int tmpB[][] = new int[n+1][n+1];
				//copy contents of A and B to temporary matrices
				for (int i=0; i<n; i++) {
					for (int j=0; j<n; j++) {
						tmpA[i][j] = A[i][j];
						tmpB[i][j] = B[i][j];
					}
				}
				A = tmpA;
				B = tmpB;
				//add a row of 0's to A and B
				for (int i=n; i<n+1; i++) {
					for (int j=n; j<n+1; j++) {
						A[i][j] = 0;
						B[i][j] = 0;
					}
				}
				//increment n for to be even
				n++;
			}
			//divide Matrices for Recursion
			int A1[][] = new int[n/2][n/2];
			int A2[][] = new int[n/2][n/2];
			int A3[][] = new int[n/2][n/2];
			int A4[][] = new int[n/2][n/2];
			int B1[][] = new int[n/2][n/2];
			int B2[][] = new int[n/2][n/2];
			int B3[][] = new int[n/2][n/2];
			int B4[][] = new int[n/2][n/2];
			int PA[][] = new int[n/2][n/2];
			int PB[][] = new int[n/2][n/2];
			int QA[][] = new int[n/2][n/2];
			int RB[][] = new int[n/2][n/2];
			int SB[][] = new int[n/2][n/2];
			int TA[][] = new int[n/2][n/2];
			int UA[][] = new int[n/2][n/2];
			int UB[][] = new int[n/2][n/2];
			int VA[][] = new int[n/2][n/2];
			int VB[][] = new int[n/2][n/2];
			int P[][] = new int[n/2][n/2];
			int Q[][] = new int[n/2][n/2];
			int R[][] = new int[n/2][n/2];
			int S[][] = new int[n/2][n/2];
			int T[][] = new int[n/2][n/2];
			int U[][] = new int[n/2][n/2];
			int V[][] = new int[n/2][n/2];
			//copy upper-left matrices
			for (int i=0; i<n/2; i++) {
				for (int j=0; j<n/2; j++) {
					A1[i][j] = A[i][j];
					B1[i][j] = B[i][j];
				}
			}
			//copy upper-right matrices
			for (int i=0; i<n/2; i++) {
				for (int j=n/2; j<n; j++) {
					A2[i][j-(n/2)] = A[i][j];
					B2[i][j-(n/2)] = B[i][j];
				}
			}
			//copy lower-left matrices
			for (int i=n/2; i<n; i++) {
				for (int j=0; j<n/2; j++) {
					A3[i-(n/2)][j] = A[i][j];
					B3[i-(n/2)][j] = B[i][j];
				}
			}
			//copy lower-right matrices
			for (int i=n/2; i<n; i++) {
				for (int j=n/2; j<n; j++) {
					A4[i-(n/2)][j-(n/2)] = A[i][j];
					B4[i-(n/2)][j-(n/2)] = B[i][j];
				}
			}
			//compute temporary matrices for recursive calls
			for (int i=0; i<n/2; i++) {
				for (int j=0; j<n/2; j++) {
					PA[i][j] = A1[i][j]+A4[i][j];
					PB[i][j] = B1[i][j]+B4[i][j];
					QA[i][j] = A3[i][j]+A4[i][j];
					RB[i][j] = B2[i][j]-B4[i][j];
					SB[i][j] = B3[i][j]-B1[i][j];
					TA[i][j] = A1[i][j]+A2[i][j];
					UA[i][j] = A3[i][j]-A1[i][j];
					UB[i][j] = B1[i][j]+B2[i][j];
					VA[i][j] = A2[i][j]-A4[i][j];
					VB[i][j] = B3[i][j]+B4[i][j];
				}
			}
			strassenMultiplication(PA, PB, P, n/2);
			strassenMultiplication(QA, B1, Q, n/2);
			strassenMultiplication(A1, RB, R, n/2);
			strassenMultiplication(A4, SB, S, n/2);
			strassenMultiplication(TA, B4, T, n/2);
			strassenMultiplication(UA, UB, U, n/2);
			strassenMultiplication(VA, VB, V, n/2);
			//instantiate solution matrices
			int C1[][] = new int[n/2][n/2];
			int C2[][] = new int[n/2][n/2];
			int C3[][] = new int[n/2][n/2];
			int C4[][] = new int[n/2][n/2];
			//compute solution matrices
			for (int i=0; i<n/2; i++) {
				for (int j=0; j<n/2; j++) {
					C1[i][j] = P[i][j] + S[i][j] - T[i][j] + V[i][j];
					C2[i][j] = R[i][j] + T[i][j];
					C3[i][j] = Q[i][j] + S[i][j];
					C4[i][j] = P[i][j] + R[i][j] - Q[i][j] + U[i][j];
				}
			}
			//copy upper-left solution matrix
			for (int i=0; i<n/2; i++) {
				for (int j=0; j<n/2; j++) {
					C[i][j] = C1[i][j];
				}
			}
			//copy upper-right solution matrix
			for (int i=0; i<n/2; i++) {
				for (int j=n/2; j<initialN; j++) {
					C[i][j] = C2[i][j-(n/2)];
				}
			}
			//copy lower-left solution matrix
			for (int i=n/2; i<initialN; i++) {
				for (int j=0; j<n/2; j++) {
					C[i][j] = C3[i-(n/2)][j];
				}
			}
			//copy lower-right solution matrix
			for (int i=n/2; i<initialN; i++) {
				for (int j=n/2; j<initialN; j++) {
					C[i][j] = C4[i-(n/2)][j-(n/2)];
				}
			}
		}
	}
	//---------------------------------------------------------------------------------
	public static void generateMatrix(int theMatrix[][], int n){
		Random random = new Random();
		for (int i=0; i<n; i++){
			for (int j=0; j<n; j++){
				theMatrix[i][j] = random.nextInt(9);
			}
		}
	}
	//---------------------------------------------------------------------------------
	public static void printTime(long startTime, long endTime) {
		System.out.println("Elapsed time: " + ((double) (endTime - startTime)/1000) + " sec.");
	}
	//---------------------------------------------------------------------------------
	public static void printMatrix(int C[][], int n) {
		for (int i=0; i<n; i++){
			for (int j=0; j<n; j++){
				System.out.print(C[i][j] + " ");
			}
			System.out.println();
		}
	}
	//---------------------------------------------------------------------------------
	public static void main(String[] args) {
		//instantiate time recording variables
      Calendar cal;
      long startTime;
      long endTime;
      //instantiate matrix input variables
		Scanner keyboard = new Scanner (System.in);
		int n;
		//ask user to input size of matrices
		System.out.println("Matrices are n x n, size of n? ");
		n = keyboard.nextInt();
		//instantiate the matrices
		int A[][] = new int[n][n];
		int B[][] = new int[n][n];
		int C[][] = new int[n][n];
		//populate the matrices with random integers
		generateMatrix(A, n);
		generateMatrix(B, n);
			//display the Matrix A
			//System.out.println("\nMatrix A:");
			//printMatrix(A, n);
			//display the Matrix B
			//System.out.println("\nMatrix B:");
			//printMatrix(B, n);
		//perform A*B using the Classical Multiplication
		cal = Calendar.getInstance();
		startTime = cal.getTimeInMillis();
		classicalMultiplication(A, B, C, n);
		cal = Calendar.getInstance();
		endTime = cal.getTimeInMillis();
		System.out.println("\nClassical Multiplication Result:");
			//printMatrix(C, n);
		printTime(startTime, endTime);
		//perform A*B using the Divide-And-Conquer Multiplication
		cal = Calendar.getInstance();
		startTime = cal.getTimeInMillis();
		divideConquer(A, B, C, n);
		cal = Calendar.getInstance();
		endTime = cal.getTimeInMillis();
		System.out.println("\nDivide-And-Conquer Result:");
			//printMatrix(C, n);
		printTime(startTime, endTime);
		//perform A*B using the Strassen's Multiplication
		cal = Calendar.getInstance();
		startTime = cal.getTimeInMillis();
		strassenMultiplication(A, B, C, n);
		cal = Calendar.getInstance();
		endTime = cal.getTimeInMillis();
		System.out.println("\nStrassen's Multiplication Result:");
			//printMatrix(C, n);
		printTime(startTime, endTime);
	}
}
