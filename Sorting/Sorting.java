// Alexis Mendez
// CS241, Section 01
// Project 3: A comparison of different sorting algorithms
// 5/30/12

import java.util.*;

/////////////////////////////////////////////////////////////////////
public class Sorting {
	//Global arrays track total comparisons and data moves
	static int moves = 0;
	static int comparisons = 0;

	//----------------------------------------------------------------
	//Sort the array using Insertion sort
	//----------------------------------------------------------------
	public static int[] insertion(int a[]) {
		int current, j;
	   
	   for (int i=1; i<a.length; i++) 
	   {
	      current = a[i];
	      j = i-1;
	      while ((j>=0) && (a[j]>current)) {
	         a[j+1] = a[j];
	         j--;
	         moves ++;
	      }
	      a[j+1] = current;
	      comparisons ++;
	   }
	   return a;
	}
	
	//----------------------------------------------------------------
	//Sort the array using Selection sort
	//----------------------------------------------------------------
	public static int[] selection(int a[]) {
		int min, temp;
		
		for (int i=0; i<a.length-1; i++) {
			min = i;
			for (int j=i+1; j<a.length; j++) {
				if (a[j] < a[min]) {
					min = j;
				}
				comparisons ++;
			}
			if (i != min) {
				temp = a[i];
				a[i] = a[min];
				a[min] = temp;
				moves += 3;
			}
		}
		return a;
	}
	
	//----------------------------------------------------------------
	//Sort the array using Merge sort
	//----------------------------------------------------------------
	public static int[] mergeSort(int a[], int low, int high) {
		int mid;
		int left[];
		int right[];
		
		if (low < high) {
	      mid = (low + high)/2;
	      left = new int[mid + 1];
	      right = new int[high - mid];
	      left = mergeSort(a, low, mid);
	      right = mergeSort(a, mid+1, high);
	      a = merge(left, right);
	   }
		
		//Base case, only 1 item in the array
		else {
			int single[] = new int[1];
			single[0] = a[high];
			return single;
		}
		
		
		return a;
	}
	
	//----------------------------------------------------------------
	//Merge 2 smaller sorted arrays into one large sorted array
	//----------------------------------------------------------------
	public static int[] merge(int left[], int right[]) {
		int leftCurrent = 0;
		int rightCurrent = 0;
		int merged [] = new int[left.length + right.length];
		
		//Merge the arrays
		for (int i=0; i<merged.length; i++) {
			//Left array has no more elements
			if (leftCurrent == left.length) {
				while (rightCurrent < right.length) {
					merged[i] = right[rightCurrent];
					rightCurrent ++;
					moves ++;
					i++;
				}
			}
			//Right array has no more elements
			else if (rightCurrent == right.length) {
				while (leftCurrent < left.length) {
					merged[i] = left[leftCurrent];
					leftCurrent ++;
					moves ++;
					i++;
				}
			}
			//Elements remain in both arrays
			else {
				if (left[leftCurrent] <= right[rightCurrent]) {
					merged[i] = left[leftCurrent];
					leftCurrent ++;
					moves ++;
				}
				else {
					merged[i] = right[rightCurrent];
					rightCurrent ++;
					moves ++;
				}
			}
			comparisons ++;
		}
		
		return merged;
	}
	
	//----------------------------------------------------------------
	//Sort the array using Quick sort
	//----------------------------------------------------------------
	public static int[] quickSort(int a[], int low, int high) {
		int pivot;
		
		if (low < high) {
			pivot = partition(a, low, high);
			a = quickSort(a, low, pivot);
			a = quickSort(a, pivot+1, high);
		}
		
		return a;
	}
	
	//----------------------------------------------------------------
	//Partition the array for Quick sort
	//----------------------------------------------------------------
	public static int partition(int a[], int low, int high) {
		int i = low;
		int j = high + 1;
		int temp;
		int pivot = a[low];
		
		while (i < j) {
			do {
				i++;
				comparisons ++;
			} while ((a[i] <= pivot) && (i < high));
			do {
				j--;
				comparisons ++;
			} while (a[j] > pivot && (i > low));
			if (i < j) {
				temp = a[i];
				a[i] = a[j];
				a[j] = temp;
				moves += 3;
			}
		}
		
		temp = a[low];
		a[low] = a[j];
		a[j] = temp;
		moves += 3;
		
		return j;
	}
	
	//----------------------------------------------------------------
	//Sort the array using Heap sort
	//----------------------------------------------------------------
	public static int[] heapSort(int a[]) {
		int n = a.length;
		
		for (int i = (n/2); i>=0; i--) {
			maxHeapify(a, i, n);
		}
		
		for (int i = n; i>0; i--) {
			int temp = a[i-1];
			a[i-1] = a[0];
			a[0] = temp;
			moves += 3;
			maxHeapify(a, 0, i-1);
		}
		
		return a;
	}
	
	//----------------------------------------------------------------
	//Build a Max-Heap from a given array
	//----------------------------------------------------------------
	public static void maxHeapify(int a[], int i, int n) {
		int left = (2*i)+1;
		int right = (2*i)+2;
		int largest;
		
		if ((left < n) && (a[left] > a[i])) {
			largest = left;
		}
		else {
			largest = i;
		}
		if ((right < n) && (a[right] > a[largest])) {
			largest = right;
		}
		comparisons += 2;
		if (largest != i) {
			int temp = a[i];
			a[i] = a[largest];
			a[largest] = temp;
			moves += 3;
			maxHeapify(a, largest, n);
		}
	}
	
	//----------------------------------------------------------------
	//Main Method
	//----------------------------------------------------------------
	public static void main(String[] args) {
		Scanner keyboard = new Scanner (System.in);
		String input = "";
		int arraySize;
		int algorithm;
		int unsorted[];
		int sorted[];
		boolean quit = false;
		
		while (!quit) {
			//Initialize variables
			algorithm = 0;
			moves = 0;
			comparisons = 0;
			
			//Ask the user for the array size and instantiate arrray
	     	System.out.println("How many numbers would you like to sort?");
	     	arraySize = keyboard.nextInt();
	     	//Instantiate the unsorted array
	     	unsorted = new int[arraySize];
	     	//Instantiate the sorted array
	     	sorted = new int[arraySize];
	     	
	     	//Clear input buffer
	     	keyboard.nextLine();
	     	
	     	//Ask the user if they want random numbers
	     	System.out.println();
	     	System.out.println("Randomly generate numbers (Y/N)?");
	     	input = keyboard.nextLine();
	     	input = input.toLowerCase();
	     	
	     	if (input.equals("yes") || input.equals("y")) {
	     		//Generate the array randomly
	     		Random generator = new Random();
	     		for (int i = 0; i < arraySize; i++) {
	     			unsorted[i] = generator.nextInt();
	     		}
	     	}
	     	else {
	     		//Generate each number individually
	     		System.out.println();
	     		System.out.println("Please enter " + arraySize + " numbers:");
	     		for (int i = 0; i < arraySize; i++) {
	     			unsorted[i] = keyboard.nextInt();
	     		}
	     	}
	     	
	     	//Copy the unsorted array for sorting
	     	for (int i = 0; i < arraySize; i++) {
	  			sorted[i] = unsorted[i];
	  		}
	     	
	     	//Ask the user which sorting algorithm to use
	     	while (!(algorithm > 0 && algorithm < 6)) {
	     		System.out.println();
	     		System.out.println("Which sorting algorithm will you use?");
		     	System.out.println("1: Insertion sort");
		     	System.out.println("2: Selection sort");
		     	System.out.println("3: Merge sort");
		     	System.out.println("4: Quick sort");
		     	System.out.println("5: Heap sort");
		     	algorithm = keyboard.nextInt();
		     	if (!(algorithm > 0 && algorithm < 6)) {
		     		System.out.println("Invalid entry, try again");
		     	}
	     	}
	     	
	     	//Call the selected sorting algorithm
	  		System.out.println();
	     	switch (algorithm) {
	     		case 1:
	     			System.out.println("Insertion sort selected");
	     			sorted = insertion(sorted);
	     			break;
	     		case 2:
	     			System.out.println("Selection sort selected");
	     			sorted = selection(sorted);
	     			break;
	     		case 3:
	     			System.out.println("Merge sort selected");
	     			sorted = mergeSort(sorted, 0, arraySize-1);
	     			break;
	     		case 4:
	     			System.out.println("Quick sort selected");
	     			sorted = quickSort(sorted, 0, arraySize-1);
	     			break;
	     		case 5:
	     			System.out.println("Heap sort selected");
	     			sorted = heapSort(sorted);
	     			break;
	     	}
	     	
	     	//Clear input buffer
	     	keyboard.nextLine();
	     	
	     	//Ask the user if they to print the arrays
	     	System.out.println();
	     	System.out.println("Display original and sorted lists (Y/N)?");
	     	input = keyboard.nextLine();
	     	input = input.toLowerCase();
	     	
	   	if (input.equals("yes") || input.equals("y")) {
		     	//Print the unsorted list
		  		System.out.println();
		  		System.out.println("Unsorted array:");
		     	for (int i = 0; i < arraySize; i++) {
		     		System.out.print(unsorted[i] + " ");
		     	}
		     	
		     	//Print the sorted list
		  		System.out.println();
		  		System.out.println();
		  		System.out.println("Sorted array:");
		     	for (int i = 0; i < arraySize; i++) {
		     		System.out.print(sorted[i] + " ");
		     	}
		     	System.out.println();
	   	}
	     	
	     	//Print number of items sorted, comparisons and moves
	     	System.out.println();
	     	System.out.println("Items sorted: " + arraySize);
	     	System.out.println("Comparisons: " + comparisons);
	     	System.out.println("Total data moves: " + moves);
	     	
	     	//Ask the user if they to print the arrays
	     	System.out.println();
	     	System.out.println("Sort another list? (Y/N)?");
	     	input = keyboard.nextLine();
	     	input = input.toLowerCase();
	     	
	   	if (input.equals("no") || input.equals("n")) {
	   		System.out.println();
	   		System.out.println("Goodbye.");
	   		quit = true;
	   	}
	   	else if (input.equals("yes") || input.equals("y"))  {
	   		quit = false;
	   		System.out.println();
	   	}
	   	else {
	   		System.out.println();
	   		System.out.println("Invalid input, quitting.");
	   		quit = true;
	   	}
		}
	}
}
