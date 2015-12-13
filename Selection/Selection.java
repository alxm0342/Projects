// Alexis Mendez
// CS331, Section 01
// Project 2: An implementation and comparison of selection algorithms
// 11/15/12

import java.util.*;

public class Selection {
	//---------------------------------------------------------------------------------
	public static int select1(int[] a, int k) {
		a = mergeSort(a, 0, a.length-1);
		return a[k];
	}
	//---------------------------------------------------------------------------------
	public static int select2(int[] a, int k, int p, int q) {
		int pivot = partition(a, p, q, a[p]);
		while (pivot != k) {
			if (k < pivot) {
				q = pivot-1;
			}
			else {
				p = pivot+1;
			}
			pivot = partition(a, p, q, a[p]);
		}
		return a[k];
	}
	//---------------------------------------------------------------------------------
	public static int select3(int[] a, int k, int p, int q) {
		int pivot = partition(a, p, q, a[p]);
		int kth;
		//partition the array recursively until k=pivot
		if (k < pivot) {
			//partition the left sub-array recursively
			kth = select3(a, k, p, pivot-1);
		}
		else if (k > pivot) {
			//partition the right sub-array recursively
			kth = select3(a, k, pivot+1, q);
		}
		else {
			kth = a[pivot];
		}
		return kth;
	}
	//---------------------------------------------------------------------------------
	public static int select4(int[] a, int n, int k) {
		int numMedians = (n)/5, kth, pivot;
		int[][] subArrays = new int[numMedians][5];
		int[] medians = new int[numMedians];
		int[] sorted;
		//k<r, sort the array and return kth element
		if (n<5) {
			sorted = quickSort(a, 0, a.length-1);
			kth = sorted[k];
		}
		//otherwise, select a good pivot through recursion
		else {
			pivot = partition(a, 0, a.length-1, a[0]);
			if (k==pivot) {
				kth = a[pivot];
			}
			else {
				for (int i=0; i<numMedians; i++) {
					for (int j=0; j<5; j++) {
						subArrays[i][j] = a[i*j];
					}
					quickSort(subArrays[i], 0, 4);
					medians[i] = subArrays[i][2];
				}
				int v = select4(medians, numMedians, numMedians/2);
				pivot = partition(a, 0, a.length-1, v);
				if (k==pivot) {
					kth = a[pivot];
				}
				else if (k<pivot) {
					int[] S = new int[pivot];
					for (int z=0; z<pivot; z++) {
						S[z] = a[z];
					}
					kth = select4(S, S.length, k);
				}
				else {
					int[] R = new int[a.length-(pivot+1)];
					for (int z=0; z<R.length; z++) {
						R[z] = a[z+(pivot+1)];
					}
					kth = select4(R, R.length, k-(pivot+1));
				}
			}
		}
		return kth;
	}
	//---------------------------------------------------------------------------------
	public static int[] quickSort(int[] a, int p, int q) {
		int pivot;
		//quick sort the array recursively, base case do nothing
		if (p<q) {
			pivot = partition(a, p, q, a[p]);
			a = quickSort(a, p, pivot-1);
			a = quickSort(a, pivot+1, q);
		}
		return a;
	}
	//----------------------------------------------------------------
	public static int partition(int a[], int low, int high, int pivotElement) {
		int pivotPos=low;
		int[] left, right, pivot;
		int leftCursor = 0, rightCursor = 0, isEqual = 0;
		//pivotElement = a[low];
		//determine pivot placement
		for (int i=low; i<=high; i++) {
			if (a[i] < pivotElement) {
				pivotPos++;
			}
			else if (a[i]==pivotElement) {
				isEqual++;
			}
		}
		//establish sub-arrays equal to, left and right of pivot
		left = new int[pivotPos-low];
		right = new int[(high-(pivotPos+isEqual)) + 1];
		pivot = new int[isEqual];
		isEqual = 0;
		for (int i=low; i<=high; i++) {
			if (a[i] < pivotElement) {
				if (left.length > 0) {
					left[leftCursor] = a[i];
					leftCursor++;
				}
			}
			else if (a[i] > pivotElement){
				if (right.length > 0) {
					right[rightCursor] = a[i];
					rightCursor++;
				}
			}
			else {
				if (pivot.length > 0) {
					pivot[isEqual] = a[i];
					isEqual++;
				}
			}
		}
		//organize array by pivot element
		int i=low;
		for (int j=0; j<left.length; j++) {
			a[i] = left[j];
			i++;
		}
		for (int j=0; j<pivot.length; j++) {
			a[i] = pivot[j];
			i++;
		}
		for (int j=0; j<right.length; j++) {
			a[i] = right[j];
			i++;
		}
		//return pivot position
		return pivotPos;
	}
	//---------------------------------------------------------------------------------
	public static int[] mergeSort(int a[], int low, int high) {
		int mid;
		int[] left;
		int[] right;
		int[] sorted;
		//sort the array recursively
		if (low<high) {
	      mid = (low + high)/2;
	      left = mergeSort(a, low, mid);
	      right = mergeSort(a, mid+1, high);
	      sorted = merge(left, right);
	   }
		//base case
		else {
			sorted = new int[1];
			sorted[0] = a[low];
		}
		return sorted;
	}
	//----------------------------------------------------------------
	public static int[] merge(int left[], int right[]) {
		int[] merged = new int[left.length + right.length];
		int i=0, j=0, k=0;
		//Merge the arrays
		while ((j<left.length) && (k<right.length)) {
			if (left[j] <= right[k]) {
				merged[i] = left[j];
				j++;
				i++;
			}
			else {
				merged[i] = right[k];
				k++;
				i++;
			}
		}
		//left remains to be merged
		while (j<left.length) {
			merged[i]=left[j];
			j++;
			i++;
		}
		while (k<right.length) {
			merged[i]=right[k];
			k++;
			i++;
		}
		return merged;
	}
	//---------------------------------------------------------------------------------
	public static void main(String[] args) {
		//main function variable declarations
		Random random = new Random();
		int[] a;
		int n=10, k, k1th=-1, k2th=-1, k3th=-1, k4th=-1, z, iterations=100;
      Calendar cal;
      long startTime, endTime;
      double totalTimeOne = 0;
      double totalTimeTwo = 0;
      double totalTimeThree = 0;
      double totalTimeFour = 0;
      
      //run tests using list sizes from 10-16000
		while (n<=16000) {
			k=0;
			z=0;
			System.out.println("n = " + n + ", " + iterations + " iteration average:");
			//generate the list of numbers, using random integers from 0-9999
			a = new int[n];
			for (int i=0; i<n; i++){
				a[i] = random.nextInt(9999);
			}
			while (z < 5) {
				//instantiate time-keeping variables
		      totalTimeOne = 0;
				totalTimeTwo = 0;
				totalTimeThree = 0;
				totalTimeFour = 0;
				for (int i=0; i<iterations; i++) {
					//determine the kth smallest element using select 1
					cal = Calendar.getInstance();
					startTime = cal.getTimeInMillis();
					k1th = select1(a, k);
					cal = Calendar.getInstance();
					endTime = cal.getTimeInMillis();
					totalTimeOne += (double) (endTime-startTime);
					//determine the kth smallest element using select 2
					cal = Calendar.getInstance();
					startTime = cal.getTimeInMillis();
					k2th = select2(a, k, 0, a.length-1);
					cal = Calendar.getInstance();
					endTime = cal.getTimeInMillis();
					totalTimeTwo += (double) (endTime-startTime);
					//determine the kth smallest element using select 3
					cal = Calendar.getInstance();
					startTime = cal.getTimeInMillis();
					k3th = select3(a, k, 0, a.length-1);
					cal = Calendar.getInstance();
					endTime = cal.getTimeInMillis();
					totalTimeThree += (double) (endTime-startTime);
					//determine the kth smallest element using select 4
					cal = Calendar.getInstance();
					startTime = cal.getTimeInMillis();
					k4th = select4(a, a.length, k);
					cal = Calendar.getInstance();
					endTime = cal.getTimeInMillis();
					totalTimeFour += (double) (endTime-startTime);
				}
				//display result and average times with each "k" variation
				System.out.print("[S1=" + k1th + "] " + "k=" + (k+1) + ": " + (totalTimeOne)/iterations + " msec" + " | ");
				System.out.print("[S2=" + k2th + "] " + "k=" + (k+1) + ": " + (totalTimeTwo)/iterations + " msec" + " | ");
				System.out.print("[S3=" + k3th + "] " + "k=" + (k+1) + ": " + (totalTimeThree)/iterations + " msec" + " | ");
				System.out.print("[S4=" + k4th + "] " + "k=" + (k+1) + ": " + (totalTimeFour)/iterations + " msec");
				System.out.println();
				z++;
				switch (z) {
					case 1: k=(n/4)-1; break;
					case 2: k=(n/2)-1; break;
					case 3: k=((3*n)/4)-1; break;
					case 4: k=n-1; break;
				}
			}
			System.out.println();
			switch (n) {
				case 10: n=50; break;
				case 50: n=100; break;
				case 100: n=250; break;
				default: n = n*2; break;
			}
		}
	}
}
