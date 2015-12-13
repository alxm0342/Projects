// Alexis Mendez
// CS241, section 01
// Project 2: Create and evaluate binary expression tree data
// 5/17/12

import java.io.*;
import java.util.StringTokenizer;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.lang.Character;

/////////////////////////////////////////////////////////////////////
//Binary Tree Node Class
class BTNode<E> 
{
	//Class Members
   private char data;
   private BTNode<E> left;
   private BTNode<E> right;
   
   //Contructor
   public BTNode(char initialData, BTNode<E> initialLeft, 
   		BTNode<E> initialRight) 
   {
      data = initialData;
      left = initialLeft;
      right = initialRight;
   }

   //Getter and Setter Methods
   public BTNode<E> getLeft(){
   	return this.left;
   }
   public BTNode<E> getRight(){
   	return this.right;
   }
   public char getData(){
   	return this.data;
   }
   public void setLeft(BTNode n){
   	this.left = n;
   }
   public void setRight(BTNode n){
   	this.right = n;
   }
   public void setData(char n){
   	this.data = n;
   }
}

/////////////////////////////////////////////////////////////////////
//Binary Tree Implementation Class
class BTree<E> {
	//Class Member
   private BTNode<E> root;

   //Constructor
   public BTree(BTNode<E> initialRoot) {
      root = initialRoot;
   }
   
   //Getter and Setter Methods
   public BTNode<E> getRoot() {
      return root;
   }
   public void setRoot(BTNode<E> newRoot) {
      root = newRoot;
   }
   
	//----------------------------------------------------------------
	//Traverse the tree Inorder and print results
	//----------------------------------------------------------------
	public void inOrder()
   {
      inOrder(root);
   }
   private void inOrder(BTNode<E> T)
   {
      if (T != null)
      {
         inOrder(T.getLeft());
         System.out.print(T.getData() + "  ");
         inOrder(T.getRight());
      }
   }
   
   //----------------------------------------------------------------
 	//Traverse the tree Preorder and print results
 	//----------------------------------------------------------------
   public void preOrder()
   {
      preOrder(root);
   }
   private void preOrder(BTNode<E> T)
   {
      if (T != null)
      {
         System.out.print(T.getData() + "  ");
         preOrder(T.getLeft());
         preOrder(T.getRight());
      }
   }
   
   //----------------------------------------------------------------
  	//Traverse the tree Postorder and print results
  	//----------------------------------------------------------------
   public void postOrder()
   {
      postOrder(root);
   }
   private void postOrder(BTNode<E> T)
   {
      if (T != null)
      {
         postOrder(T.getLeft());
         postOrder(T.getRight());
         System.out.print(T.getData() + "  ");
      }
   }
}

/////////////////////////////////////////////////////////////////////
//Single-link list stack implementation
class Stack<T> {
   
   //Nested Node class-----------------------------------------------
   private class Node {
       //Creates a node of generic type
       private T data;
       private Node next;
       
       //Creates a node with the given element and next node
       public Node(T newData, Node n) {
           data = newData;
           next = n;
       }
       
       //Return the element of this node
       public T getElement() {
           return data;
       }
       
       //Get methods
       //Returns the next node of this node.
       public Node getNext() {
           return next;
       }
       
       //Set methods
       //Sets the element of this node
       @SuppressWarnings("unused")
		public void setElement(T newData) {
           data = newData;
       }
       
       //Sets the next node of this node
       @SuppressWarnings("unused")
		public void setNext(Node newNext) {
           next = newNext;
       }
       
       public String toString() {
           return this.data.toString();
       }
   }
   //----------------------------------------------------------------
   
   //Members of Stack Class
   protected Node top;
   protected int size;
   
   //Default Constructor
   public Stack() {
       top = null;
       size = 0;
   }
   
   //Class Methods   
   //Push an element onto the top of the stack
   public void push(T elem) {
     Node v = new Node (elem, top);
     top = v;
     size++;
   }
   
   //Removes an element from the top of the stack
   public T pop() {
     if (isEmpty()) {
       System.out.println("The stack is empty.");
       return null;
     }
     T temp = top.getElement();
     top = top.getNext(); // link-out the former top node
     size--;
     return temp;
   }
   
   //Returns the element on top of the stack
   public T top() {
     if (this.isEmpty()) {
       System.out.println("The stack is empty.");
       return null;
     }
     return top.getElement();
   }
   
   //Returns true if the stack is empty, false otherwise
   public boolean isEmpty() {
     if (top == null){
         return true;
     }
     else {
       return false;
     }
   }    
           
   //Return the size of the stack
   public int size() {
       return size;
   }
   
   //Checks if an element is already in the set
   public boolean contains(T theData) {
       Node s =  new Node (theData, null);
       boolean isDuplicate = false;
       
       if (this.top != null) {
           Node tmp = this.top;
           
           //traverses list and checks each node for equality
           while (tmp != null) {
               if (s.data.equals(tmp.data)){
                   isDuplicate = true;
               }
               tmp = tmp.getNext();
           }
       }
       return isDuplicate;
   }
   
   //Return the contents of the stack as a string
   public String toString() {
       //The return string
       String theString = "{";
       //Temporary node to traverse the list
       Node tmp;
       
       //Checks if the list is null
       if (top != null) {
           //Set the state to the top of the stack
           tmp = top;
           //Traverse the stack and print each element to the return string
           theString += tmp.data.toString();
           for (int i=this.size-1; i>0; i--) {
               tmp = tmp.getNext();
               theString = (theString + ", " + tmp.data.toString());
           }
       }
       theString += "}";
       //Return the return String
       return (theString);
   }
}


/////////////////////////////////////////////////////////////////////
//Custom file filter only approves "Pj2_input.txt"
class JavaFileFilter extends FileFilter{
   //----------------------------------------------------------------
	//Returns true only if input file is valid
   //----------------------------------------------------------------
  public boolean accept(File file){
     if (file.getName().equals("Pj2_input.txt")){
        return true;
     }
     if (file.isDirectory()){
        return true;
     }
     //Return false and filter out file extensions not stated above
     else{
     return false;
     }
  }
  //-----------------------------------------------------------------
  //Description of what the file filter accepts
  //-----------------------------------------------------------------
  public String getDescription(){
     return "Pj2_input.txt";
  }
}
/////////////////////////////////////////////////////////////////////
public class ExpressTree {
	
	//Global array holds variable values, 27th value is invalid index
	static int vars[] = new int[27];
	
	//----------------------------------------------------------------
	//Return the index in var[] of a given variable
	//----------------------------------------------------------------
	public static int getAlphaIndex(char alpha) {
		int index = 0;
		
		switch (alpha) {
			case 'A':  index = 0; break;
			case 'B':  index = 1; break;
			case 'C':  index = 2; break;
			case 'D':  index = 3; break;
			case 'E':  index = 4; break;
			case 'F':  index = 5; break;
			case 'G':  index = 6; break;
			case 'H':  index = 7; break;
			case 'I':  index = 8; break;
			case 'J':  index = 9; break;
			case 'K':  index = 10; break;
			case 'L':  index = 11; break;
			case 'M':  index = 12; break;
			case 'N':  index = 13; break;
			case 'O':  index = 14; break;
			case 'P':  index = 15; break;
			case 'Q':  index = 16; break;
			case 'R':  index = 17; break;
			case 'S':  index = 18; break;
			case 'T':  index = 19; break;
			case 'U':  index = 20; break;
			case 'V':  index = 21; break;
			case 'W':  index = 22; break;
			case 'X':  index = 23; break;
			case 'Y':  index = 24; break;
			case 'Z':  index = 25; break;
			default: index= 27;
						System.out.println("Invalid variable name");
						break;
		}
		
		return index;
	}
	
	
	//----------------------------------------------------------------
	//Read input from Pj2_input.txt, file path passed via GUI
	//----------------------------------------------------------------
	public static void readInput(String filePath) {
		//Variables to store the expression
		char theExpression[];
		Stack<BTree<Character>> expTree;
		int expCount = 0;
		double result;
      
      try{
         FileReader filereader = new FileReader(filePath);
         BufferedReader bufferedreader = new BufferedReader(filereader);
         String inputLine = "";
         String temp;
         int index = 27;
         
         //Initialize variables from input file
         try{inputLine = bufferedreader.readLine();}
         catch (IOException e){}
         
         //Read all variables and Postfix expressions until EOF
         while (inputLine != null) {
         	expCount ++;
         	System.out.println("*********EXPRESSION " + expCount
         			+ "*********");
	         //Continue loop until first line break
	         while (!inputLine.equals("")) {
	         	StringTokenizer varTokens = new StringTokenizer(inputLine);
	            //Extract variables into alphabetical integer array
	            while (varTokens.hasMoreTokens())
	            {
	            	temp = varTokens.nextToken().toUpperCase();
	            	
	            	if (Character.isLetter(temp.charAt(0))) {
	            		index = (int) temp.charAt(0) - 65;
	            		vars[index] = Integer.parseInt(varTokens.nextToken());
	            	}
	            }
	            //Read next line of variables
	            try{inputLine = bufferedreader.readLine();}
	            catch (IOException e){}
	         }
	         	         
	         //Retrieve Postfix input
	         while (inputLine.equals("")) {
	         	try{inputLine = bufferedreader.readLine();}
	         	catch (IOException e){}
	         }
	         
	         if (inputLine != null) {
		         //Store Postfix input
		         while (!inputLine.equals("")) {
		         	char tempArray[] = inputLine.toCharArray();
		         	
		         	//Error-check for valid characters to store
		         	int expIndex = 0;
		         	for (int i=0; i <= (tempArray.length - 1); i++) {
		         		if (tempArray[i] != ' ')
		         			expIndex ++;
		         	}
		         	
		         	//Instantiate global expression array
		         	theExpression = new char[expIndex + 1];
		         	
		         	//Fill binary tree Postfix array
		         	expIndex = 0;
		         	for (int i=0; i <= (tempArray.length - 1); i++) {
		         		if (tempArray[i] != ' ') {
		         			theExpression[expIndex] = tempArray[i];
		         			expIndex ++;
		         		}
		         	}
		         	
		         	//Print input
		         	System.out.print("Input: ");
		         	for (int i=0; i<(theExpression.length - 1); i++) {
		         		System.out.print(theExpression[i] + "  ");
		         	}
		         	System.out.println();
		         		
		         	//Traverse inorder and print results:
		         	expTree = buildExpressionTree(theExpression);
		         	System.out.print("In Order: ");
		         	while (expTree.isEmpty() == false) {
		         		BTree stackPop = (BTree) expTree.pop();
		         		stackPop.inOrder();
		         	}
		         	System.out.println();
	
		         	//Traverse preorder and print results:
		         	expTree = buildExpressionTree(theExpression);
		         	System.out.print("Pre Order: ");
		         	while (expTree.isEmpty() == false) {
		         		BTree stackPop = (BTree) expTree.pop();
		         		stackPop.preOrder();
		         	}
		         	System.out.println();
	
		         	//Traverse postorder and print results:
		         	expTree = buildExpressionTree(theExpression);
		         	System.out.print("Post Order: ");
		         	while (expTree.isEmpty() == false) {
		         		BTree stackPop = (BTree) expTree.pop();
		         		stackPop.postOrder();
		         	}
		         	System.out.println();
	
		         	//Evaluate the expression
		         	expTree = buildExpressionTree(theExpression);
		         	result = 0;
		         	System.out.print("Evaluation: ");
		         	while (expTree.isEmpty() == false) {
		         		BTree stackPop = (BTree) expTree.pop();
		         		result += evaluate(stackPop);
		         	}
		         	System.out.print(result);
		         	System.out.println();
		         	
		         	try{inputLine = bufferedreader.readLine();}
		            catch (IOException e){}
		         }
	         }
	         
		      //Move to next expression of input file
		      try{inputLine = bufferedreader.readLine();}
		      catch (IOException e){}
	      }
      }
      
      //Handles file not found exception
      catch(FileNotFoundException e){
          JOptionPane.showMessageDialog(null, "Error: File Not Found");
      }
	}
	
	//----------------------------------------------------------------
	//Build an expression tree as a stack from the input array
	//----------------------------------------------------------------
	public static double evaluate(BTree expression) {
		double result = 0;
		
		if (expression.getRoot() != null) {
			char root = expression.getRoot().getData();
			String rootString = root + "";
			
			if (Character.isDigit(root)) {
				result = Double.parseDouble(rootString);
			}
			else if (Character.isLetter(root)) {
				int index = (int) rootString.charAt(0) - 65;
	   		result = vars[index];
			}
			else {
				BTNode leftRoot = expression.getRoot().getLeft();
				BTNode rightRoot = expression.getRoot().getRight();
				BTree leftTree = new BTree(leftRoot);
				BTree rightTree = new BTree(rightRoot);
				
				switch(root)
		      {
		         case '+': result = (evaluate(leftTree) +
		         		evaluate(rightTree)); break;
		         case '-': result = (evaluate(leftTree) -
		         		evaluate(rightTree)); break;
		         case '*': result = (evaluate(leftTree) *
		         		evaluate(rightTree)); break;
		         case '/': result = (evaluate(leftTree) /
		         		evaluate(rightTree)); break;
		      }
			}
		}
		
		return result;
	}
	
	//----------------------------------------------------------------
	//Build an expression tree as a stack from the input array
	//----------------------------------------------------------------
	public static Stack buildExpressionTree(char expArray[]) {
		Stack expTree = new Stack();
		
		//If character is a letter, add it to the stack,
		//Otherwise, it is an operator, pop 2 trees from
		//stack as child nodes of the new root operand.
		for (int i=0; i < expArray.length - 1; i++) {
			if (Character.isLetterOrDigit(expArray[i])) {
				BTNode n = new BTNode(expArray[i], null, null);
				BTree t = new BTree(n);
				expTree.push(t);
			}
			else {
				BTree first = (BTree) expTree.pop();
				BTree second = (BTree) expTree.pop();
				BTNode newRoot = new BTNode(expArray[i],
						second.getRoot(), first.getRoot());
				BTree operator = new BTree(newRoot);
				expTree.push(operator);
			}
		}
		
		return expTree;
	}
	
	//----------------------------------------------------------------
	//Main Method
	//----------------------------------------------------------------
	public static void main(String[] args) {
		        	readInput("Pj2_input.txt");
               //divideIt();
               //outputTable();
               //outputUnsimplified();
               //outputSimplified();
	}
}
