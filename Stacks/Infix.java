/**Name: Alexis Mendez
 * Class: CS240 - Lab 3
 * Date: 3/5/2012
 * Description: This class implements a stack data structure to translate
 * in-fix strings to pre-fix and post-fix per the project guidelines.
 */

import java.util.Scanner;

public class Infix {
//---------------------------------PREFIX--------------------------------------    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void prefix(String cmd) {
        Stack operands = new Stack();
        Stack operators = new Stack();
        String curIn = "";
        String op = "";
        String leftOp = "";
        String rightOp = "";
        String theExpression = "";
        String addToOutput = "";
        String output = "";
        char a[] = cmd.toCharArray();
        boolean error = false;
        
        for (int count = 0; count < a.length; count++) {
            if (a[count] != ' ') {
                curIn = "" + a[count];
                
                //Guidelines for prefix
                
                //Left parenthesis are always pushed onto the operators stack
                if (curIn.equals("(")) {
                    operators.push(curIn);
                }
                //Right parenthesis encountered
                else if (curIn.equals(")")) {
                    //Check for matching left-parenthesis
                    if (operators.contains("(")){
                        while (!operators.top().equals("(")) {
                            if (!operators.isEmpty()) {
                                addToOutput = (String)operators.pop();
                                op = op + "" + addToOutput;
                                if (!operands.isEmpty()) {
                                    rightOp = (String)operands.pop();
                                    if (!operands.isEmpty()) {
                                        leftOp = (String)operands.pop();
                                    }
                                }
                                theExpression = op + " " + leftOp + " " + rightOp;
                                op = "";
                                leftOp = "";
                                rightOp = "";
                                operands.push(theExpression);
                            }
                        }
                        //Discard parenthesis
                        operators.pop();
                    }
                    //No matching parenthesis
                    else {
                        System.out.println ("Error: \"(\" expected");
                        error = true;
                    }
                }
                else if (curIn.equals("\\") || curIn.equals("*") || curIn.equals("%")) {
                    //Check if the stack is not empty
                    if (!operators.isEmpty()) {
                        String atTop = (String)operators.top();
                        //Scanned element has equal or lower precedence to top of stack
                        if ((atTop.equals("\\") || atTop.equals("*") || atTop.equals("%"))) {
                            while ((atTop.equals("\\") || atTop.equals("*") || atTop.equals("%"))) {
                                addToOutput = (String)operators.pop();
                                op = op + "" + addToOutput;
                                if (!operands.isEmpty()) {
                                    rightOp = (String)operands.pop();
                                    if (!operands.isEmpty()) {
                                        leftOp = (String)operands.pop();
                                    }
                                }
                                theExpression = op + " " + leftOp + " " + rightOp;
                                op = "";
                                leftOp = "";
                                rightOp = "";
                                operands.push(theExpression);
                                addToOutput = (String)operators.pop();
                            }
                        }
                        //Scanned element has higher precedence to top of stack
                        else {
                            operators.push(curIn);
                        }
                    }
                    //The stack is empty, push to the stack
                    else {
                        operators.push(curIn);
                    }
                }
                else if (curIn.equals("+") || curIn.equals("-")) {
                    //Check if the stack is not empty
                    if (!operators.isEmpty()) {
                        String atTop = (String)operators.top();
                        //Scanned element has higher precedence to top of stack
                        if (atTop.equals("(")) {
                            operators.push(curIn);
                        }
                        //Scanned element has equal or lower precedence to top of stack
                        else {
                            while (!operators.isEmpty()){
                                if (!atTop.equals("(")){
                                    addToOutput = (String)operators.pop();
                                    op = op + "" + addToOutput;
                                    if (!operands.isEmpty()) {
                                        rightOp = (String)operands.pop();
                                        if (!operands.isEmpty()) {
                                            leftOp = (String)operands.pop();
                                        }
                                    }
                                    theExpression = op + " " + leftOp + " " + rightOp;
                                    op = "";
                                    leftOp = "";
                                    rightOp = "";
                                    operands.push(theExpression);
                                }
                            }
                            operators.push(curIn);
                        }
                    }
                    //The stack is empty, push to the stack
                    else {
                        operators.push(curIn);
                    }
                }
                //Variables are always pushed onto the operands stack
                else {
                    operands.push(curIn);
                }
            }
        }
        addToOutput = "";
        while (!operators.isEmpty()) {
            if (operators.top().equals("(")) {
                //Disregard Parenthesis
                operators.pop();
            }
            else {
                if (!operands.isEmpty()) {
                    if (output.equals("")) {
                        output = (String)operands.pop();
                    }
                    else {
                        output = (String)operands.pop() + " " + output;
                    }
                    if (!operands.isEmpty()) {
                        output = (String)operands.pop() + " " + output;
                    }
                }
                output = (String)operators.pop() + " " + output;
            }
        }
        if (!error) {
            System.out.println("Prefix = " + output);
        }
        else {
            System.out.println("Invalid Expression");
        }
    }
//---------------------------------POSTFIX-------------------------------------
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static void postfix(String cmd) {
        Stack theStack = new Stack();
        String curIn = "";
        String output = "";
        String addToOutput = "";
        char a[] = cmd.toCharArray();
        boolean error = false;
        
        for (int count = 0; count < a.length; count++) {
            if (a[count] != ' ') {
                curIn = "" + a[count];

                //Guidelines for postfix
                if (curIn.equals("(")) {
                    theStack.push(curIn);
                }
                else if (curIn.equals(")")) {
                    //Check for matching left-parenthesis
                    if (theStack.contains("(")){
                        addToOutput = (String)theStack.pop();
                        while (!addToOutput.equals("(")) {
                            output = output + " " + addToOutput;
                            addToOutput = (String)theStack.pop();
                        }
                    }
                    //No matching parenthesis
                    else {
                        System.out.println ("Error: \"(\" expected");
                        error = true;
                    }
                }
                else if (curIn.equals("\\") || curIn.equals("*") || curIn.equals("%")) {
                    //Check if the stack is not empty
                    if (!theStack.isEmpty()) {
                        String atTop = (String)theStack.top();
                        //Scanned element has equal or lower precedence to top of stack
                        if ((atTop.equals("\\") || atTop.equals("*") || atTop.equals("%"))) {
                            while ((atTop.equals("\\") || atTop.equals("*") || atTop.equals("%"))) {
                                addToOutput = (String)theStack.pop();
                                output = output + " " + addToOutput;
                                atTop = (String)theStack.top();
                            }
                        }
                        //Scanned element has higher precedence to top of stack
                        else {
                            theStack.push(curIn);
                        }
                    }
                    //The stack is empty, push to the stack
                    else {
                        theStack.push(curIn);
                    }
                }
                else if (curIn.equals("+") || curIn.equals("-")) {
                    //Check if the stack is not empty
                    if (!theStack.isEmpty()) {
                        String atTop = (String)theStack.top();
                        //Scanned element has higher precedence to top of stack
                        if (atTop.equals("(")) {
                            theStack.push(curIn);
                        }
                        //Scanned element has equal or lower precedence to top of stack
                        else {
                            while (!theStack.isEmpty()){
                                if (!atTop.equals("(")){
                                    addToOutput = (String)theStack.pop();
                                    output = output + " " + addToOutput;
                                    if (!theStack.isEmpty()) {
                                        atTop = (String)theStack.top();
                                    }
                                }
                            }
                            theStack.push(curIn);
                        }
                    }
                    //Stack is empty, push element
                    else {
                        theStack.push(curIn);
                    }
                }
                else {
                    output = output + " " + curIn;
                }
            }
        }
        while (!theStack.isEmpty()) {
            if (theStack.top().equals("(")) {
                //Disregard Parenthesis
                theStack.pop();
            }
            else {
                addToOutput = (String)theStack.pop();
                output = output + " " + addToOutput;
            }
        }
        if (!error) {
            System.out.println("Postfix =" + output);
        }
        else {
            System.out.println("Invalid Expression");
        }
    }

//----------------------------------MAIN---------------------------------------
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String cmd = "";
        
        do {
        //Get user input
        System.out.println("Enter infix expression (type \"Quit\" to exit): ");
        cmd = keyboard.nextLine();  
        //If user does NOT want to quit
        if  (!cmd.equals("Quit")) {
            prefix(cmd);
            postfix(cmd);
            System.out.println();
        }
        } while (!cmd.equals("Quit"));
    }
}
